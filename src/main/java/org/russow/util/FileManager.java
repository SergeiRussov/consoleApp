package org.russow.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileManager {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public String createFile(String path) {
        if (Files.exists(Paths.get(path))) {
            return "Файл " + Paths.get(path).toAbsolutePath().toString() + " уже существует.";
        } else {
            File file = new File(path);
            try {
                file.createNewFile();
                return "Файл создан.";
            } catch(IOException e) {
                return ("Файл + " + path + " не был создан.\n" + e.getMessage());
            }
        }
    }

    public String writeFile(Boolean append, String path, String content) {
        if (Files.exists(Paths.get(path))) {
            try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(path, append), CHARSET)) {
                osw.write(content + "\n");
                return "Запись в файл произведена.";
            } catch (IOException e) {
                return ("Не удалось произвести запись в файл.\n" + e.getMessage());
            }
        } else {
            return ("Файл " + path + " не существует существует. Создайте файл.");
        }
    }

    public String copyFile(Boolean append, String sourcePath, String distPath) {
        if (Files.notExists(Paths.get(sourcePath))) {
            return "Копируемый файл " + Paths.get(sourcePath) + " не найден.";
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourcePath), CHARSET));
                 OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(distPath, append), CHARSET)) {
                while (reader.ready()) {
                    osw.write(reader.readLine() + "\n");
                }
                return "Файл скопирован.";
            } catch (IOException e) {
                return "Файл + " + distPath + " не был скопирован.\n" + e.getMessage();
            }
        }
    }


    public String deleteFile(String path) {
        if (Files.notExists(Paths.get(path))) {
            return "Файл не найден " + path + ".";
        } else {
            File file = new File(path);
            file.delete();
            return "Файл " + path + " удален.";
        }
    }

    public String filesToZip(String distPath, String ... files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(distPath))) {
            for (String file : files) {
                if (Files.notExists(Paths.get(file))) {
                    return "Файл " + file + " не найден.";
                } else {
                    fileToZip(zos, file);
                }
            }
        } catch(IOException e) {
            return "Архив не был создан.";
        }
        return "Архив создан.";
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

    public String showOnConsole(String path) {
        if (Files.notExists(Paths.get(path))) {
            return "Файл не найден.";
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), CHARSET))) {
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
