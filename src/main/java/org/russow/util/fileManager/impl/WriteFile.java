package org.russow.util.fileManager.impl;

import org.russow.util.fileManager.FileManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteFile implements FileManager {

    @Override
    public void run(String[] command) {
        if (command[1].equals("-a")) {
            System.out.println(writeFile(true, command[2], command[3]));
        } else {
            System.out.println(writeFile(false, command[1], command[2]));
        }
    }

    private String writeFile(Boolean append, String path, String content) {
        String result;
        if (Files.exists(Paths.get(path))) {
            try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(path, append), StandardCharsets.UTF_8)) {
                osw.write(content + "\n");
                result = "Запись в файл произведена.";
            } catch (IOException e) {
                result =  ("Не удалось произвести запись в файл.\n" + e.getMessage());
            }
        } else {
            result = ("Файл " + path + " не существует существует. Создайте файл.");
        }
        return result;
    }
}
