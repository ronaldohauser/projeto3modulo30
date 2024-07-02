package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.generic.jdbc.dao.ClienteDAO;
import dao.generic.jdbc.dao.iClienteDAO;
import domain.Cliente;

public class ClienteTest {
    
    private iClienteDAO clienteDAO;
    
    @Before
    public void setUp() throws Exception {
        clienteDAO = new ClienteDAO();
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void cadastrarBuscarAtualizarExcluirClienteComEmailTest() throws Exception {
        // Teste de cadastro com email
        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Ronaldo Dias");
        cliente.setEmail("ronaldo@example.com");

        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteDB = clienteDAO.buscar("10");
        assertNotNull(clienteDB);
        assertEquals(cliente.getCodigo(), clienteDB.getCodigo());
        assertEquals(cliente.getNome(), clienteDB.getNome());
        assertEquals(cliente.getEmail(), clienteDB.getEmail());

        // Teste de atualização de email
        clienteDB.setEmail("novoronaldo@exemplo.com");
        Integer countUpdate = clienteDAO.atualizar(clienteDB);
        assertTrue(countUpdate == 1);

        Cliente clienteAtualizado = clienteDAO.buscar("10");
        assertNotNull(clienteAtualizado);
        assertEquals(clienteDB.getCodigo(), clienteAtualizado.getCodigo());
        assertEquals(clienteDB.getNome(), clienteAtualizado.getNome());
        assertEquals(clienteDB.getEmail(), clienteAtualizado.getEmail());

        // Teste de exclusão
        Integer countDel = clienteDAO.excluir(clienteAtualizado);
        assertTrue(countDel == 1);

        Cliente clienteExcluido = clienteDAO.buscar("10");
        assertNull(clienteExcluido);
    }

    @Test
    public void buscarTodosClientesTest() throws Exception {
        // Teste de busca de todos os clientes
        Cliente c1 = new Cliente();
        c1.setCodigo("10");
        c1.setNome("Ronaldo Dias");
        c1.setEmail("ronaldo@exemplo.com");

        Cliente c2 = new Cliente();
        c2.setCodigo("20");
        c2.setNome("Aline Dias");
        c2.setEmail("aline@teste.com");

        Integer count1 = clienteDAO.cadastrar(c1);
        Integer count2 = clienteDAO.cadastrar(c2);
        assertTrue(count1 == 1 && count2 == 1);

        List<Cliente> list = clienteDAO.buscarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        for (Cliente cliente : list) {
            clienteDAO.excluir(cliente);
        }

        list = clienteDAO.buscarTodos();
        assertTrue(list.size() == 0);
    }

    @Test
    public void validarCamposObrigatoriosTest() throws Exception {
        // Teste de validação de campos obrigatórios
        Cliente cliente = new Cliente();
        cliente.setCodigo("30");
        cliente.setNome("José Alves");

        // Deve falhar ao cadastrar sem email
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 0);
    }

    @Test
    public void atualizacaoParcialClienteTest() throws Exception {
        // Teste de atualização parcial de cliente
        Cliente cliente = new Cliente();
        cliente.setCodigo("40");
        cliente.setNome("Maria das Montanhas");
        cliente.setEmail("maria@teste.com");

        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteDB = clienteDAO.buscar("40");
        assertNotNull(clienteDB);
        assertEquals(cliente.getCodigo(), clienteDB.getCodigo());
        assertEquals(cliente.getNome(), clienteDB.getNome());
        assertEquals(cliente.getEmail(), clienteDB.getEmail());

        // Atualização parcial do nome
        clienteDB.setNome("Maria de Jesus");
        Integer countUpdate = clienteDAO.atualizar(clienteDB);
        assertTrue(countUpdate == 1);

        Cliente clienteAtualizado = clienteDAO.buscar("40");
        assertNotNull(clienteAtualizado);
        assertEquals(clienteDB.getCodigo(), clienteAtualizado.getCodigo());
        assertEquals(clienteDB.getNome(), clienteAtualizado.getNome());
        assertEquals(clienteDB.getEmail(), clienteAtualizado.getEmail());

        // Limpeza do ambiente de teste
        Integer countDel = clienteDAO.excluir(clienteAtualizado);
        assertTrue(countDel == 1);

        Cliente clienteExcluido = clienteDAO.buscar("40");
        assertNull(clienteExcluido);
    }

    @Test
    public void buscarPorCPFTest() throws Exception {
        // Teste de busca por CPF
        Cliente cliente = new Cliente();
        cliente.setCodigo("50");
        cliente.setNome("João de Almeida");
        cliente.setEmail("joao@teste.com");
        cliente.setCpf("12345678900");

        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteDB = clienteDAO.buscarPorCPF("12345678900");
        assertNotNull(clienteDB);
        assertEquals(cliente.getCodigo(), clienteDB.getCodigo());
        assertEquals(cliente.getNome(), clienteDB.getNome());
        assertEquals(cliente.getEmail(), clienteDB.getEmail());

        Integer countDel = clienteDAO.excluir(clienteDB);
        assertTrue(countDel == 1);

        Cliente clienteExcluido = clienteDAO.buscar("50");
        assertNull(clienteExcluido);
    }
}
