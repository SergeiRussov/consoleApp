package org.russow.util.fileManager.impl;

import org.russow.util.fileManager.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateFile implements FileManager {

    @Override
    public void run(String[] command) {
        System.out.println(createFile(command[1]));
    }

    private String createFile(String path) {

        String result;
        if (Files.exists(Paths.get(path))) {
            result = "Файл " + Paths.get(path).toAbsolutePath().toString() + " уже существует.";
        } else {
            File file = new File(path);
            try {
                file.createNewFile();
                result =  "Файл создан.";
            } catch(IOException e) {
                result =  ("Файл + " + path + " не был создан.\n" + e.getMessage());
            }
        }
        return result;
    }
}
