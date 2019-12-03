package io.farkle.dignifiedfarkleclient.model.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Game {

  private long id;

  private Date created;

  private int preferredNumPlayers;

  private List<GamePlayer> gamePlayers = new LinkedList<>();

  private List<Action> actions = new LinkedList<>();

  private Player winner;

  private State state;

  private int numberOfRounds;

  public long getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }

  public int getPreferredNumPlayers() {
    return preferredNumPlayers;
  }

  public void setPreferredNumPlayers(int preferredNumPlayers) {
    this.preferredNumPlayers = preferredNumPlayers;
  }

  public List<GamePlayer> getGamePlayers() {
    return gamePlayers;
  }

  public List<Action> getActions() {
    return actions;
  }

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }

  public int getNumberOfRounds() {
    return numberOfRounds;
  }

  public void setNumberOfRounds(int numberOfRounds) {
    this.numberOfRounds = numberOfRounds;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public enum State {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    ABANDONED;
  }


}
