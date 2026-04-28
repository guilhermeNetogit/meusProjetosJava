package diversos.enumeradores.enum4;

public enum TipoDocumento {// Eclipse -> Github @guilhermeNetogit 23/03/2026 16:29:23

	CPF {
		@Override
		public String geraNumeroTeste() {
			return GeraCpfCnpj.cpf();
		}
	},
	CNPJ {
		@Override
		public String geraNumeroTeste() {
			return GeraCpfCnpj.cnpj();
		}
	};

	public abstract String geraNumeroTeste();
}
