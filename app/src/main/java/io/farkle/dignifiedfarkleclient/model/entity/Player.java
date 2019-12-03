package io.farkle.dignifiedfarkleclient.model.entity;

import io.farkle.dignifiedfarkleclient.model.entity.Game;
import io.farkle.dignifiedfarkleclient.model.entity.GamePlayer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Player {

  private long id;

  private Date created;

  private String oauthKey;

  private String displayName;

  private List<GamePlayer> gamePlayers = new LinkedList<>();

  private List<Game> gamesWon = new LinkedList<>();

  private String diceUpgrade;

  private double winRate;

  private int victoryPoints;

  private int highestScore;

  public long getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }

  public List<GamePlayer> getGamePlayers() {
    return gamePlayers;
  }

  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey( String oauthKey) {
    this.oauthKey = oauthKey;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public List<Game> getGamesWon() {
    return gamesWon;
  }

  public int getWinCount() {
    return gamesWon.size();
  }

  public String getDiceUpgrade() {
    return diceUpgrade;
  }

  public void setDiceUpgrade(String diceUpgrade) {
    this.diceUpgrade = diceUpgrade;
  }

  public double getWinRate() {
    return winRate;
  }

  public void setWinRate(double winRate) {
    this.winRate = winRate;
  }

  public int getVictoryPoints() {
    return victoryPoints;
  }

  public void setVictoryPoints(int victoryPoints) {
    this.victoryPoints = victoryPoints;
  }

  public int getHighestScore() {
    return highestScore;
  }

  public void setHighestScore(int highestScore) {
    this.highestScore = highestScore;
  }
}