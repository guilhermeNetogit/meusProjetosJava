package meusprojetosjava.herancapolimorfismo;

public class ContaEspecial extends ContaBancaria {

    private boolean especial;
    private double limite;

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getValorEspecialUsado() {

        if (!especial || getSaldo() >= 0) {
            return 0;
        }

        return Math.abs(getSaldo());
    }

    public double getLimiteDisponivel() {

        if (!especial) {
            return 0;
        }

        return limite - getValorEspecialUsado();
    }

    @Override
    public boolean realizarSaque(double valorSaque) {

        double saldoDisponivel = especial 
                ? getSaldo() + limite 
                : getSaldo();

        if (saldoDisponivel >= valorSaque) {
            setSaldo(getSaldo() - valorSaque);
            return true;
        }

        return false;
    }

    @Override
    public void consultarSaldo() {

        super.consultarSaldo();

        if (verificarUsoChequeEspecial()) {
            System.out.println("A conta está utilizando "
                    + String.format("R$ %,.2f", getValorEspecialUsado())
                    + " do limite especial.");
        } else {
            System.out.println("A conta NÃO está utilizando limite especial.");
        }
    }

    public boolean verificarUsoChequeEspecial() {
        return getSaldo() < 0;
    }

    @Override
    public String toString() {

        return super.toString()
                + "\nLimite especial disponível: " + getLimiteDisponivel()
                + "\nLimite especial utilizado: " + getValorEspecialUsado()
                + "\n-------------------------------------";
    }
}