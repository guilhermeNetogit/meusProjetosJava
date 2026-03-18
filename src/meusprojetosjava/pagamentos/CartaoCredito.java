package meusprojetosjava.pagamentos;

public class CartaoCredito extends Pagamento implements Autenticavel {

    private String senha;

    public CartaoCredito(double valor, String senha) {
        super(valor);
        this.senha = senha;
    }

    @Override
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento no cartão: R$ " + valor);
    }
}