package com.longhike.wordle_killer_api.guess.utils;

import java.util.List;

import com.longhike.wordle_killer_api.letter.Letter;
import com.longhike.wordle_killer_api.letter.enums.CharacterDisposition;

public class GuessProcessor {

  public static boolean collectPossibles(String comparator, List<Letter> letters) {
    for (int i = 0; i < letters.size(); i++) {
      Letter letter = letters.get(i);

      CharacterDisposition disposition = CharacterDisposition.values()[letter.getDisposition()];

      switch (disposition) {
        case KNOWN_POSITION:
          if (comparator.charAt(i) != letter.getCharacter()) {
            return false;
          }
          break;
        case UNKNOWN_POSITION:
          if (comparator.indexOf(letter.getCharacter()) < 0 || comparator.charAt(i) == letter.getCharacter()) {
            return false;
          }
          break;
        case NO_POSITION:
          if (comparator.charAt(i) == letter.getCharacter() ||
              (comparator.contains(String.valueOf(letter.getCharacter())) &&
                  !GuessProcessor.isCharacterValidSomewhereInWord(letter.character, letters))) {
            return false;
          }
          break;
        default:
          break;
      }
    }
    return true;
  }

  public static boolean excludeNotInWord(String comparator, List<Character> alreadyExcluded) {
    for (char c : alreadyExcluded) {
      if (comparator.indexOf(c) >= 0) {
        return false;
      }
    }
    return true;
  }

  private static boolean isCharacterValidSomewhereInWord(char c, List<Letter> letters) {
    return letters.stream().filter((letter) -> letter.character == c)
        .filter((letter) -> letter.disposition != CharacterDisposition.NO_POSITION.ordinal()).toList().size() > 0;
  }

}
