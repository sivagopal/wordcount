package com.rbs.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordCountServiceTest {

    private WordCountService wordCountService;

    @Mock
    private TranslaterService translaterService;

    @Before
    public void setUp() {
        wordCountService = new WordCountService();
        translaterService = mock(TranslaterService.class);
        when(translaterService.translate("flower")).thenReturn("flower");
        when(translaterService.translate("flor")).thenReturn("flower");
        when(translaterService.translate("bloom")).thenReturn("flower");
        when(translaterService.translate("test")).thenReturn("test");
        when(translaterService.translate("tester")).thenReturn("tester");

        when(translaterService.translate("hello")).thenReturn("hello");
        when(translaterService.translate("hola")).thenReturn("hello");


        wordCountService.setTranslaterService(translaterService);
    }

    @Test
    public void testAddWord() {
        assertEquals(true, wordCountService.addWord("test"));

        //Integrity test
        assertEquals(1, wordCountService.getSize());
    }

    @Test
    public void testAddWordDuplicate() {
        assertEquals(true, wordCountService.addWord("test"));
        assertEquals(true, wordCountService.addWord("test"));
        assertEquals(true, wordCountService.addWord("ehllo"));
        assertEquals(3, wordCountService.getSize());

        //Integrity test
        assertTrue(wordCountService.getWords().contains("test"));
        assertTrue(wordCountService.getWords().contains("ehllo"));
    }

    @Test
    public void testAddShouldAllowAlphabeticOnly() {
        assertEquals(true, wordCountService.addWord("test"));
        assertEquals(false, wordCountService.addWord("t2ea"));

        //Integrity test
        assertEquals(1, wordCountService.getSize());
        assertTrue(wordCountService.getWords().contains("test"));
        assertFalse(wordCountService.getWords().contains("ehllo"));
    }

    @Test
    public void testCountWord() {
        wordCountService.addWord("test");

        //Integrity test
        assertEquals(1, wordCountService.countWord("test"));
        assertTrue(wordCountService.getWords().contains("test"));
    }

    @Test
    public void testCountWords() {
        wordCountService.addWord("test");
        wordCountService.addWord("test");
        wordCountService.addWord("tester");
        assertEquals(2, wordCountService.countWord("test"));
        assertEquals(1, wordCountService.countWord("tester"));

        //Integrity test
        assertTrue(wordCountService.getWords().contains("test"));
        assertTrue(wordCountService.getWords().contains("tester"));
    }

    @Test
    public void testCountAllWordsExistsInDifferentLanguageForAGivenWord() {
        wordCountService.addWord("flower");
        wordCountService.addWord("flor");
        wordCountService.addWord("bloom");

        assertEquals(3, wordCountService.countWord("flower"));
        assertEquals(3, wordCountService.countWord("flor"));
        assertEquals(3, wordCountService.countWord("bloom"));

        wordCountService.addWord("test");
        assertEquals(3, wordCountService.countWord("bloom"));
        assertEquals(1, wordCountService.countWord("test"));

        //Integrity test to ensure the actual words are stored
        assertTrue(wordCountService.getWords().contains("test"));
        assertTrue(wordCountService.getWords().contains("bloom"));
        assertTrue(wordCountService.getWords().contains("flor"));
        assertTrue(wordCountService.getWords().contains("flower"));
    }

    @Test
    public void testNullOrBlankWordCountToReturnZero() {
        assertEquals(0, wordCountService.countWord(""));
        assertEquals(0, wordCountService.countWord(null));
        assertEquals(false, wordCountService.addWord(""));
        assertEquals(0, wordCountService.countWord(""));
        assertEquals(false, wordCountService.addWord(""));
        assertEquals(0, wordCountService.countWord(""));

        //Integrate test
        assertEquals(0, wordCountService.getSize());
    }
}