package io.farkle.dignifiedfarkleclient.model;

import com.google.gson.annotations.Expose;

public class GamePreferences {

  @Expose
  private int numPlayers;

  public GamePreferences() {
  }

  public GamePreferences(int numPlayers) {
    this.numPlayers = numPlayers;
  }

  public int getNumPlayers() {
    return numPlayers;
  }

  public void setNumPlayers(int numPlayers) {
    this.numPlayers = numPlayers;
  }
}
