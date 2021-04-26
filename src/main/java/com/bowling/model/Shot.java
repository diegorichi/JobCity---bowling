package com.bowling.model;

import org.apache.commons.lang3.math.NumberUtils;

public class Shot{

	private Game game;

	private String originalLine;

	private String shot = "";

	private Integer shotScore = 0;

	private Boolean foul = Boolean.FALSE;

	private String playerName;

	public Shot(Game bowling, String line) {
		game = bowling;
		originalLine = line;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getOriginalLine() {
		return originalLine;
	}

	public void setOriginalLine(String originalLine) {
		this.originalLine = originalLine;
	}

	public void setShot(String shot) {
		if (NumberUtils.isDigits(shot)) {
			this.shotScore = Integer.parseInt(shot);
		} else {
			setFoul("F".equalsIgnoreCase(shot));
		}
		this.shot = shot;
	}

	public Integer getShotScore() {
		return shotScore;
	}

	public void setShotScore(Integer shotScore) {
		this.shotScore = shotScore;
	}

	public String getShot() {
		return shot;
	}
	
	public Boolean getFoul() {
		return foul;
	}

	public void setFoul(Boolean foul) {
		this.foul = foul;
	}

	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}


	@Override
	public String toString() {
		return "Shot [shot=" + shot + ", foul=" + foul + ", playerName=" + playerName + "]";
	}

	//always in the model objects we need to implement
	// the equals and the hascode.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((foul == null) ? 0 : foul.hashCode());
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((originalLine == null) ? 0 : originalLine.hashCode());
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		result = prime * result + ((shot == null) ? 0 : shot.hashCode());
		result = prime * result + ((shotScore == null) ? 0 : shotScore.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shot other = (Shot) obj;
		if (foul == null) {
			if (other.foul != null)
				return false;
		} else if (!foul.equals(other.foul))
			return false;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (originalLine == null) {
			if (other.originalLine != null)
				return false;
		} else if (!originalLine.equals(other.originalLine))
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		if (shot == null) {
			if (other.shot != null)
				return false;
		} else if (!shot.equals(other.shot))
			return false;
		if (shotScore == null) {
			if (other.shotScore != null)
				return false;
		} else if (!shotScore.equals(other.shotScore))
			return false;
		return true;
	}


	

}
