package diversos.livraria.negocio;

import java.util.Optional;

import diversos.livraria.basedados.DataBase;
import diversos.livraria.entidade.Cliente;

/**
 * Classe responsável pelas regras de negócio relacionadas aos clientes.
 * <p>
 * Permite consultar clientes cadastrados no sistema com base no CPF,
 * além de possibilitar futuras operações como cadastro e exclusão.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 15:39:40
 */

public class ClienteNegocio {
	
	/** Instância do banco de dados em memória */
    private DataBase bancoDados;

    /**
     * Construtor da classe.
     *
     * @param banco banco de dados contendo os clientes
     */
    public ClienteNegocio(DataBase banco) {
        this.bancoDados = banco;
    }

    /**
     * Consulta o cliente pelo seu CPF.
     * @param cpf CPF de um cliente
     * 
     * @return {@link Optional} contendo o cliente, se encontrado
     */
    public Optional<Cliente> consultar(String cpf) {

    	return bancoDados.getClientes()
                .stream()
                .filter(c -> c.getCpf().equalsIgnoreCase(cpf))
                .findFirst();
    }

    /**
     * Cadastra um novo cliente no sistema.
     * <p>
     * (Funcionalidade ainda não implementada)
     * </p>
     *
     * @param cliente cliente a ser cadastrado
     */
    // TODO Implementar cadastro de cliente
    /*public void cadastrar(Cliente cliente) {
    bancoDados.getClientes().add(cliente); // ⚠️ só funciona se remover unmodifiable
	}
	*/
    
    /**
     * Exclui um cliente com base no CPF informado.
     * <p>
     * (Funcionalidade ainda não implementada)
     * </p>
     *
     * @param cpf CPF do cliente a ser removido
     */
    // TODO Implementar exclusão de cliente
    /*
     * public void excluir(String cpf) {
    bancoDados.getClientes()
        .removeIf(c -> c.getCpf().equals(cpf));
	}
     */

}