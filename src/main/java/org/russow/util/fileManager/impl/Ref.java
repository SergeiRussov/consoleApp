package org.russow.util.fileManager.impl;

import org.russow.util.fileManager.FileManager;

public class Ref implements FileManager {

    public Ref() {
        System.out.println(getHelp());
    }

    @Override
    public void run(String[] strings) {
        System.out.println(getHelp());
    }

    private String getHelp() {
        return "help - список доступных комманд\n" +
                "createFile path_to_file - создать файл\n" +
                "writeFile (-a) path_to_file content - записать строку в файл\n" +
                "cp (-a) source_file dest_file - скопировать файл\n" +
                "deleteFile path_to_file - удалить файл\n" +
                "zip path_to_zip_file files - поместить файлы в Zip архив\n" +
                "cat path_to_file - вывести содержимое файла на экран\n" +
                "exit - выход из программы\n" +
                "\n-a - дополнительный параметр, если нужно писать в конец файла\n";
    }
}
