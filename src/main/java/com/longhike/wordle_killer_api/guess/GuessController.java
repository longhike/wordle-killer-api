package com.longhike.wordle_killer_api.guess;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GuessController {

  @Autowired
  GuessService guessService;

  @PostMapping("/guess")
  public List<String> postMethodName(@RequestBody GuessRequestBody guessRequest) {
    if (!this.guessService.isValid(guessRequest)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Request");
    }

    Guess normalized = this.guessService.getNormalizedGuess(guessRequest);

    List<String> result = this.guessService.guess(normalized.getLetters(), normalized.getAlreadyExcluded());
    if (result == null) {
      return new ArrayList<>();
    }
    return result;
  }

}
