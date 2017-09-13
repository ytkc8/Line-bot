package com.example.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class DefaultWordChainServiceTest {
    private DefaultWordChainService defaultWordChainService;

    @Before
    public void setUp() throws Exception {
        defaultWordChainService = new DefaultWordChainService();
    }

    @Test
    public void test_getChainWord_returnsApple() throws Exception {
        String chainWord = defaultWordChainService.getChainWord("しりとり");

        assertThat(chainWord, equalTo("りんご"));
    }

    @Test
    public void test_getChainWord_returnsProvocation() throws Exception {
        String chainWord = defaultWordChainService.getChainWord("ゴリラ");

        assertThat(chainWord, equalTo("ラッパっていうと思った？"));
    }

    @Test
    public void test_getChainWord_returnsDefault() throws Exception {
        String chainWord = defaultWordChainService.getChainWord("anything");

        assertThat(chainWord, equalTo("anything"));
    }
}