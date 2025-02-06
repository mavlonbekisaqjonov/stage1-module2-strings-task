package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        // Step 1: Build a regex pattern that matches any delimiter
        StringBuilder regex = new StringBuilder();
        for (String delimiter : delimiters) {
            if (regex.length() > 0) {
                regex.append("|");  // Add 'OR' condition between delimiters
            }
            // Escape regex special characters in delimiters
            regex.append(Pattern.quote(delimiter));
        }

        // Step 2: Use the regex pattern to split the string
        String[] substrings = source.split(regex.toString());

        // Step 3: Filter out any empty substrings and collect them into a List
        List<String> output = new ArrayList<>();
        for (String substring : substrings) {
            if (!substring.trim().isEmpty()) {  // Add only non-empty substrings
                output.add(substring.trim());
            }
        }

        return output;
    }
}
