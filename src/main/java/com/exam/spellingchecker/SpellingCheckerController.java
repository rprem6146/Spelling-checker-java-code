package com.exam.spellingchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpellingCheckerController {

    @Autowired
    private SpellingCheckerService service;

    @GetMapping("/check/{word}")
    public ResponseModel check(@PathVariable String word) {
        String dictFound = service.checkSpelling(word) ? "Yes" : "No";
        List<String> suggestions = service.suggestSimilarWords(word);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setDictFound(dictFound);
        responseModel.setSuggestions(suggestions);
        return responseModel;
    }
}
