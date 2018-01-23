package com.exam.spellingchecker;

import java.util.List;

public class ResponseModel {
    private String dictFound;
    private List<String> suggestions;

    public String getDictFound() {
        return dictFound;
    }

    public void setDictFound(String dictFound) {
        this.dictFound = dictFound;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
