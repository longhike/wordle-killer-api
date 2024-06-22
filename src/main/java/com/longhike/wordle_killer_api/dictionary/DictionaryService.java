package com.longhike.wordle_killer_api.dictionary;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

  private List<String> dictionary;
  private ClassPathResource resource = new ClassPathResource("static/dictionary.txt");

  public DictionaryService() {
    this.dictionary = this.setDictionary(2);
  }

  public List<String> getDictionary() {
    return this.dictionary;
  }

  private List<String> setDictionary(int retries) {
    try (InputStream stream = resource.getInputStream()) {
      BufferedInputStream buffInStream = new BufferedInputStream(stream);
      ByteArrayOutputStream buffOutStream = new ByteArrayOutputStream();
      for (int result = buffInStream.read(); result != -1; result = buffInStream.read()) {
        buffOutStream.write(result);
      }
      String result = buffOutStream.toString(StandardCharsets.UTF_8.name());

      return Arrays.asList(result.split("\n"));
    } catch (IOException e) {
      if (retries > 0) {
        return this.setDictionary(retries-1);
      }
      return new ArrayList<>();
    }
  }
}
