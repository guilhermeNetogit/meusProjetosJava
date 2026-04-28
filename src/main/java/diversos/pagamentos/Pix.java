package diversos.pagamentos;

public class Pix extends Pagamento {

    public Pix(double valor) {
        super(valor);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento via Pix: R$ " + valor);
    }
}