package com.longhike.wordle_killer_api.guess;

import java.util.List;

import com.longhike.wordle_killer_api.letter.Letter;

public class Guess {
  private List<Letter> letters;
  private List<Character> alreadyExcluded;

  public List<Letter> getLetters() {
    return letters;
  }

  public List<Character> getAlreadyExcluded() {
    return alreadyExcluded;
  }

  public void setLetters(List<Letter> letters) {
    this.letters = letters;
  }

  public void setAlreadyExcluded(List<Character> alreadyExcluded) {
    this.alreadyExcluded = alreadyExcluded;
  }
}
