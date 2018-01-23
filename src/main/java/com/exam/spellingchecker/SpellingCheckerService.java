package com.exam.spellingchecker;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpellingCheckerService {
    private Set<String> words = new HashSet<>();

    public SpellingCheckerService() {
        ClassLoader cl = getClass().getClassLoader();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(cl.getResource("US.dic").getFile())));
            words = br.lines().collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkSpelling(String word) {
        if (word != null) {
            return words.contains(word.toLowerCase());
        } else {
            return false;
        }
    }

    public List<String> suggestSimilarWords(String word) {
        List<String> suggestedWords = Optional.ofNullable(
                words.stream().filter(w -> (w.indexOf(word) >= 0 && w.lastIndexOf(word) >= 0)).collect(Collectors.toList())
        ).orElse(Collections.emptyList());
        Collections.sort(suggestedWords);
        return suggestedWords;
    }

    public static void main(String[] args) {
        SpellingCheckerService spc = new SpellingCheckerService();

        System.out.printf("Finding word Respect in US.dic : %s", spc.checkSpelling("Respect") ? "Word Found" : "Word not Found");

        System.out.printf("\nFinding suggestions for ami : %s", spc.suggestSimilarWords("ami"));
    }
}
