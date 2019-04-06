package org.russow.util.fileManager.impl;

import org.russow.util.fileManager.FileManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeleteFile implements FileManager {

    @Override
    public void run(String[] command) {
        System.out.println(deleteFile(command[1]));
    }

    private String deleteFile(String path) {
        String result;
        if (Files.notExists(Paths.get(path))) {
            result = "Файл не найден " + path + ".";
        } else {
            File file = new File(path);
            file.delete();
            result =  "Файл " + path + " удален.";
        }
        return result;
    }
}
