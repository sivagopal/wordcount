package com.rbs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class WordCountService {
    private List<String> words = new ArrayList<>();


    private TranslaterService translaterService;

    public boolean addWord(String word) {
        Pattern p = Pattern.compile("[^a-zA-Z]");
        boolean isAlphabetic = p.matcher(word).find();
        if (isAlphabetic) return false;
        words.add(word);
        return true;
    }

    public int getSize() {
        return words.size();
    }

    public int countWord(String word) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        String translatedWord = translate(word);
        Integer currentCount = wordCountMap.get(translatedWord);
        for (String wordFromList : words) {
            final String translatedWordFromList = translate(wordFromList);
            if (currentCount != null && wordCountMap.get(translatedWord) != null && translatedWordFromList.equals(translatedWord)) {
                currentCount++;

            } else {
                currentCount = 1;
            }
            wordCountMap.put(translatedWordFromList, currentCount);
        }

        return wordCountMap.get(translatedWord);
    }

    private String translate(String wordFromList) {
        return translaterService.translate(wordFromList);
    }

    public void setTranslaterService(TranslaterService translaterService) {
        this.translaterService = translaterService;
    }

    public List<String> getWords() {
        return words;
    }
}
