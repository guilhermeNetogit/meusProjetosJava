package diversos.youtube;

import java.sql.Date;

public class MainYouTube {// Eclipse -> Github @guilhermeNetogit 30/03/2026 17:26:12

	public static void main(String[] args) {

		Video[] vid = new Video[5];
		vid[0] = new Video("Curso Java POO - Aula 14");
		vid[1] = new Video("Curso Java POO - Aula 15");
		vid[2] = new Video("Curso Banco de Dados - Aula 02");
		vid[3] = new Video("Curso HTML5 - Aula 03");
		vid[4] = new Video("Curso CSS - Aula 04");
		
		Aluno[] a = new Aluno[4];
		a[0] = new Aluno("Gabriel Pensador", "058.318.338-79", Date.valueOf("1972-02-27"), "M", "opensador.gabriel");
		a[1] = new Aluno("Luiz Gonzaga", "103.397.884-10", Date.valueOf("1931-12-13"), "M", "gonzagao");
		a[2] = new Aluno("Edson Arantes do Nascimento", "083.116.706-84", Date.valueOf("1941-04-02"), "M", "pele");
		a[3] = new Aluno("Fábio Silva","187.550.887-71", Date.valueOf("2008-03-31"), "M", "fabao.s");

		Visualizacao[] vis = new Visualizacao[5];
		vis[0] = new Visualizacao(a[1], vid[0]);
		vis[0].avaliar();
		vis[0] = new Visualizacao(a[1], vid[0]);
		vis[0].avaliar(40f);
		vis[0] = new Visualizacao(a[1], vid[0]);
		vis[0].avaliar(10);
		vis[1] = new Visualizacao(a[1], vid[1]);
		vis[1].avaliar(40f);;
		vis[2] = new Visualizacao(a[2], vid[2]);
		vis[2].avaliar(10);
		
		System.out.println(vis[0].toString());
		System.out.println(vis[1].toString());
		System.out.println(vis[2].toString());
		/*
		for (int i=0; i<v.length;i++) {
			System.out.println(v[i]);
		}
		*/
		System.out.println("Videos\n--------------------");
		for (Video video : vid) {
			System.out.println(video);
		}
		
		System.out.println("Alunos\n--------------------");
		for (Aluno aluno : a) {
			System.out.println(aluno);
		}
		
	}

}
