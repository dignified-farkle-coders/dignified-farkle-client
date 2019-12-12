package io.farkle.dignifiedfarkleclient.model.entity;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class Action {

  @Expose
  private long actionId;

  @Expose
  private Date created;
  @Expose
  private Game game;

  @Expose
  private Player player;

  @Expose
  private Player nextPlayer;

  @Expose
  private int[] availableDice;

  @Expose
  private int[] frozenDice;

  @Expose
  private boolean stay;

  @Expose
  private boolean farkleOut;

  @Expose
  private int turn;
  public long getActionId() {
    return actionId;
  }

  public Date getCreated() {
    return created;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Player getNextPlayer() {
    return nextPlayer;
  }

  public void setNextPlayer(Player nextPlayer) {
    this.nextPlayer = nextPlayer;
  }

  public int[] getAvailableDice() {
    return availableDice;
  }

  public void setAvailableDice(int[] availableDice) {
    this.availableDice = availableDice;
  }

  public int[] getFrozenDice() {
    return frozenDice;
  }

  public void setFrozenDice(int[] frozenDice) {
    this.frozenDice = frozenDice;
  }

  public boolean getStay() {
    return stay;
  }

  public void setStay(boolean stay) {
    this.stay = stay;
  }

  public boolean getFarkleOut() {
    return farkleOut;
  }

  public void setFarkleOut(boolean farkleOut) {
    this.farkleOut = farkleOut;
  }

  public int getTurn() {
    return turn;
  }

  public void setTurn(int turn) {
    this.turn = turn;
  }
}
