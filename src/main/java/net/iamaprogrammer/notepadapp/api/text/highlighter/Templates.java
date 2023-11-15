package net.iamaprogrammer.notepadapp.api.text.highlighter;

import net.iamaprogrammer.notepadapp.HelloApplication;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;

public class Templates {
    public static String get(String name) {
        try {
            URL url = HelloApplication.class.getResource("templates/"+name+".txt");
            System.out.println("templates"+File.separator+name+".txt");
            File file = new File(url.getPath());
            return Files.readString(file.toPath());
        } catch (Exception ignored) {
            return "";
        }
    }
}
