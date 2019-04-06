package org.russow.util.fileManager.impl;

import org.russow.util.fileManager.FileManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FilesToZip implements FileManager {

    @Override
    public void run(String[] strings) {
        String[] files = new String[strings.length - 2];
        for (int i = 2; i < strings.length; i++) {
            files[i-2] = strings[i];
        }
        System.out.println(filesToZip(strings[1], files));
    }

    private String filesToZip(String distPath, String ... files) {
        String result;
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(distPath))) {
            for (String file : files) {
                if (Files.notExists(Paths.get(file))) {
                    result =  "Файл " + file + " не найден.";
                } else {
                    fileToZip(zos, file);
                }
            }
        } catch(IOException e) {
            result = "Архив не был создан.";
        }
        result = "Архив создан.";
        return result;
    }

    private void fileToZip(ZipOutputStream zos, String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            ZipEntry entry = new ZipEntry(fileName);
            zos.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zos.write(buffer);
            zos.closeEntry();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
