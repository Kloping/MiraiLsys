package io.github.kloping.Idiom.entity;

public class Response {
    private String[] pinyin;
    private Number state;
    private String word;

    public String[] getPinyin() {
        return this.pinyin;
    }

    public Response setPinyin(String[] pinyin) {
        this.pinyin = pinyin;
        return this;
    }

    public Number getState() {
        return this.state;
    }

    public Response setState(Number state) {
        this.state = state;
        return this;
    }

    public String getWord() {
        return this.word;
    }

    public Response setWord(String word) {
        this.word = word;
        return this;
    }
}