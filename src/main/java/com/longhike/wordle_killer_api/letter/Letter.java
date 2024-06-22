package com.longhike.wordle_killer_api.letter;

public class Letter {
  public char character;
  public int disposition;

  public char getCharacter() {
    return this.character;
  }

  public int getDisposition() {
    return disposition;
  }

  public void setCharacter(char character) {
    this.character = character;
  }

  public void setDisposition(int disposition) {
    this.disposition = disposition;
  }
}
