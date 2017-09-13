package com.example.service;

public class DefaultWordChainService implements WordChainService {
    private static final String SIRITORI = "しりとり";
    private static final String GORILA = "ゴリラ";

    @Override
    public String getChainWord(String originalWord) {
        String returnsString;

        switch (originalWord) {
            case SIRITORI:
                returnsString = "りんご";
                break;
            case GORILA:
                returnsString = "ラッパっていうと思った？";
                break;
            default:
                returnsString = getNextChain(originalWord);
        }

        return returnsString;
    }

    private String getNextChain(String originalWord) {
        return originalWord;
    }
}
