package com.longhike.wordle_killer_api.guess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhike.wordle_killer_api.dictionary.DictionaryService;
import com.longhike.wordle_killer_api.guess.utils.GuessProcessor;
import com.longhike.wordle_killer_api.letter.Letter;

@Service
public class GuessService {

  @Autowired
  DictionaryService dictionaryService;

  public boolean isValid(Guess guess) {
    for (Letter letter : guess.getLetters()) {
      if (!Character.isAlphabetic(letter.character)) {
        return false;
      }
    }

    for (char c : guess.getAlreadyExcluded()) {
      if (!Character.isAlphabetic(c)) {
        return false;
      }
    }
    
    return true;
  }

  public Guess getNormalizedGuess(Guess guess) {
    Guess normalized = new Guess();
    List<Letter> letters = new ArrayList<>();
    for (Letter letter : guess.getLetters()
    ) {
      Letter updated = new Letter();
      updated.setCharacter(Character.toLowerCase(letter.character));
      updated.setDisposition(letter.disposition);
      letters.add(letter);
    }

    List<Character> alreadyExcluded = guess.getAlreadyExcluded().stream().map(c -> Character.toLowerCase(c)).toList();
    normalized.setLetters(letters);
    normalized.setAlreadyExcluded(alreadyExcluded);

    return normalized;
  }

  public List<String> guess(List<Letter> letters, List<Character> alreadyExcluded) {
    Stream<String> stream = this.dictionaryService.getDictionary().stream();
    return stream
      .filter((String current) -> GuessProcessor.excludeNotInWord(current, alreadyExcluded))
      .filter((String current) -> GuessProcessor.collectPossibles(current, letters))
      .toList();
  }
}
