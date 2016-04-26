package com.storyteller.java;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public class Test {

    // NO CONSTRUCTOR HERE: An empty constructor is autogenerated by Java
    // and is not necessary to call the below method anyway

    // EXPLANATION: Since this is a utility method that doesn't depend on any fields in Stories, it can be made static.
    // Used as follows: String story = Stories.generateStory("...", "...");

    /**
     * BEGIN: Experimentaition section... dont expect anything to work from here on.
     */

    public static String generateStory(String substitutionText, String selectedItem) {
        URL url = Stories.class.getResource(selectedItem + ".txt");

        String text = null;
        try {
            text = new String(Files.readAllBytes(new File(url.toURI()).toPath()), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // NULL CHECK HERE: if text is null, return an empty string instead of crashing. Can also return an error message?
        return text != null ? text.replace("$char$", substitutionText) : "";
    }






}