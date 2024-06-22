package com.longhike.wordle_killer_api.guess;

import java.util.UUID;

import org.springframework.lang.Nullable;

public class GuessRequestBody extends Guess {
  @Nullable
  private UUID sessionId;

  public UUID getSessionId() {
    return this.sessionId;
  }
}
