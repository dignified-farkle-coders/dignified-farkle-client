package io.farkle.dignifiedfarkleclient.model.entity;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class GamePlayer {

  @Expose
  private Long gamePlayerId;

  @Expose
  private Date created;

  @Expose
  private Game game;

  @Expose
  private Player player;

  @Expose
  private int order;

  @Expose
  private int points;

  public Long getId() {
    return gamePlayerId;
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

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }
}
