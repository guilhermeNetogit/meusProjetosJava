package jogos.snakegame;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreManager {// Eclipse -> Github @guilhermeNetogit 17/03/2026 14:45:20
	private static final String SCORE_FILE = "snake_scores.dat";
	private List<Score> scores;

	public ScoreManager() {
		// Inicializa a lista antes de tentar carregar
		scores = new ArrayList<>();
		loadScores();
	}

	public void addScore(String playerName, int newScore) {
		// Verificar se o jogador já existe no ranking
		Score existingScore = findPlayerScore(playerName);

		if (existingScore != null) {
			// Jogador já existe - verificar se a nova pontuação é maior
			if (newScore > existingScore.getScore()) {
				// Atualizar a pontuação existente
				existingScore.setScore(newScore);
				existingScore.updateDate();
				System.out.println("Novo recorde pessoal para " + playerName + ": " + newScore);
			} else {
				System.out.println("Pontuação " + newScore + " não supera o recorde pessoal de " + playerName + " ("
						+ existingScore.getScore() + ")");
				return;
			}
		} else {
			// Novo jogador - adicionar ao ranking
			scores.add(new Score(playerName, newScore));
			System.out.println("Novo jogador adicionado ao ranking: " + playerName + " com " + newScore);
		}

		// Ordenar por pontuação (maior para menor)
		scores.sort(Comparator.comparingInt(Score::getScore).reversed());

		// Manter apenas top 10
		if (scores.size() > 10) {
			scores = new ArrayList<>(scores.subList(0, 10));
		}

		saveScores();
	}

	private Score findPlayerScore(String playerName) {
		for (Score s : scores) {
			if (s.getPlayerName().equalsIgnoreCase(playerName)) {
				return s;
			}
		}
		return null;
	}

	public List<Score> getTopScores() {
		return Collections.unmodifiableList(scores);
	}

	public boolean isNewRecord(String playerName, int score) {
		Score existingScore = findPlayerScore(playerName);

		if (existingScore == null) {
			if (scores.size() < 10)
				return true;
			return score > scores.get(scores.size() - 1).getScore();
		} else {
			return score > existingScore.getScore();
		}
	}

	public String getRecordMessage(String playerName, int score) {
		Score existingScore = findPlayerScore(playerName);

		if (existingScore == null) {
			if (scores.size() < 10) {
				return "NOVO JOGADOR NO RANKING!";
			} else {
				return "VOCÊ ENTROU NO TOP 10!";
			}
		} else {
			if (score > existingScore.getScore()) {
				return "NOVO RECORDE PESSOAL! 🏆";
			} else {
				int needed = existingScore.getScore() - score + 1;
				return "Faltam " + needed + " ponto(s) para seu recorde de " + existingScore.getScore();
			}
		}
	}

	public int getPlayerBestScore(String playerName) {
		Score s = findPlayerScore(playerName);
		return s != null ? s.getScore() : 0;
	}

	public int getMinimumScoreToEnter() {
		if (scores.size() < 10)
			return 0;
		return scores.get(scores.size() - 1).getScore();
	}

	public boolean hasScores() {
		return !scores.isEmpty();
	}

	public int getTotalPlayers() {
		return scores.size();
	}

	/**
	 * Reseta o ranking completamente (remove todas as pontuações)
	 * 
	 * @return true se o reset foi bem sucedido
	 */
	public boolean resetRanking() {
		try {
			scores.clear();
			saveScores();

			// Também tentar deletar o arquivo fisicamente
			File file = new File(SCORE_FILE);
			if (file.exists()) {
				file.delete();
			}

			System.out.println("Ranking resetado com sucesso!");
			return true;
		} catch (Exception e) {
			System.err.println("Erro ao resetar ranking: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private void loadScores() {
		File file = new File(SCORE_FILE);
		if (!file.exists())
			return;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			Object obj = ois.readObject();
			if (obj instanceof ArrayList) {
				scores = (ArrayList<Score>) obj;
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Erro ao carregar scores: " + e.getMessage());
			scores = new ArrayList<>();
		}
	}

	private void saveScores() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCORE_FILE))) {
			oos.writeObject(scores);
		} catch (IOException e) {
			System.err.println("Erro ao salvar scores: " + e.getMessage());
		}
	}

	// Classe interna para representar uma pontuação
	public static class Score implements Serializable {
		private static final long serialVersionUID = 1L;
		private final String playerName;
		private int score;
		private String date;

		public Score(String playerName, int score) {
			this.playerName = playerName;
			this.score = score;
			this.date = getCurrentDate();
		}

		public String getPlayerName() {
			return playerName;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public String getDate() {
			return date;
		}

		public void updateDate() {
			this.date = getCurrentDate();
		}

		private String getCurrentDate() {
			return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date());
		}
	}
}