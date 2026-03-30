package meusprojetosjava.youtube;

public class Video implements AcoesVideo {

	private String titulo;
	private double avaliacao;
	private int totAval = 0;
	private int views;
	private int likes;
	private boolean reproduzindo;

	public Video(String titulo) {
		this.titulo = titulo;
		this.avaliacao = 0;
		this.views = 0;
		this.likes = 0;
		this.reproduzindo = false;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double tot) {
		totAval++;
		double media = (this.avaliacao * (totAval - 1) + tot )/ totAval;
		this.avaliacao = Math.round(media * 10.0) / 10.0;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public boolean isReproduzindo() {
		return reproduzindo;
	}

	public void setReproduzindo(boolean reproduzindo) {
		this.reproduzindo = reproduzindo;
	}

	@Override
	public void play() {
		this.reproduzindo = true;
	}

	@Override
	public void pause() {
		this.reproduzindo = false;
	}

	@Override
	public void like() {
		this.likes ++;
	}

	@Override
	public String toString() {
		return "Video \n[titulo=" + titulo + "\n avaliacao=" + avaliacao 
				+ "\n views=" + views + "\n likes=" + likes
				+ "\n reproduzindo=" + reproduzindo + "]\n";
	}

}
