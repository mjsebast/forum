package com.linguo.translate.dto;


import java.util.ArrayList;
import java.util.List;

public class TranslationDTO {
    private List<String> texts = new ArrayList<String>();
    private String from;
    private String to;

    public TranslationDTO() {}

    public TranslationDTO(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public void addText(String text){
        texts.add(text);
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
