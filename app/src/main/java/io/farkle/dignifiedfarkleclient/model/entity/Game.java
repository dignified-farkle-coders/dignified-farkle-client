package io.farkle.dignifiedfarkleclient.model.entity;

import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Game {

  @Expose
  private long id;

  @Expose
  private Date created;

  @Expose
  private int preferredNumPlayers;

  @Expose
  private List<GamePlayer> gamePlayers = new LinkedList<>();

  @Expose
  private List<Action> actions = new LinkedList<>();

  @Expose
  private Action lastAction;

  @Expose
  private Player winner;

  @Expose
  private State state;

  @Expose
  private int numberOfRounds;

  @Expose
  private boolean yourTurn;

  public boolean isYourTurn() {
    return yourTurn;
  }

  public void setYourTurn(boolean yourTurn) {
    this.yourTurn = yourTurn;
  }

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

  public Action getLastAction() {
    return lastAction;
  }

  public void setLastAction(Action lastAction) {
    this.lastAction = lastAction;
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
