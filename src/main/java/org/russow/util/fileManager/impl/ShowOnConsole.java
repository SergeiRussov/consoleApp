package org.russow.util.fileManager.impl;

import org.russow.util.fileManager.FileManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShowOnConsole implements FileManager {

    @Override
    public void run(String[] command) {
        System.out.println(showOnConsole(command[1]));
    }

    public String showOnConsole(String path) {
        if (Files.notExists(Paths.get(path))) {
            return "Файл не найден.";
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                while (reader.ready()) {
                    sb.append(reader.readLine() + "\n");
                }
                return sb.toString();
            } catch (IOException e) {
                return "Ошибка при чтении файла.\n" + e.getMessage();
            }
        }
    }
}
