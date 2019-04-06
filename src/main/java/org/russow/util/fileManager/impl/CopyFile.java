package org.russow.util.fileManager.impl;

import org.russow.util.fileManager.FileManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CopyFile implements FileManager {

    @Override
    public void run(String[] command) {
        if (command[1].equals("-a")) {
            System.out.println(copyFile(true, command[2], command[3]));
        } else {
            System.out.println(copyFile(false, command[1], command[2]));
        }
    }

    private String copyFile(Boolean append, String sourcePath, String distPath) {

        String result;
        if (Files.notExists(Paths.get(sourcePath))) {
            result =  "Копируемый файл " + Paths.get(sourcePath) + " не найден.";
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourcePath), StandardCharsets.UTF_8));
                 OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(distPath, append), StandardCharsets.UTF_8)) {
                while (reader.ready()) {
                    osw.write(reader.readLine() + "\n");
                }
                result = "Файл скопирован.";
            } catch (IOException e) {
                result = "Файл + " + distPath + " не был скопирован.\n" + e.getMessage();
            }
        }
        return result;
    }
}
