package org.russow.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    private FileManager fm = new FileManager();
    private static boolean isWork = true;

    public void getCommand() {
        System.out.println(getHelp());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (isWork) {
            System.out.print("Введите команду: ");
            try {
                String[] command = reader.readLine().split(" ");
                switch (command[0]) {
                    case "help":
                        System.out.println(getHelp());
                        break;
                    case "createFile":
                        System.out.println(fm.createFile(command[1]));
                        break;
                    case "writeFile":
                        if (command[1].equals("-a")) {
                            System.out.println(fm.writeFile(true, command[2], command[3]));
                        } else {
                            System.out.println(fm.writeFile(false, command[1], command[2]));
                        }
                        break;
                    case "cp":
                        if (command[1].equals("-a")) {
                            System.out.println(fm.copyFile(true, command[2], command[3]));
                        } else {
                            System.out.println(fm.copyFile(false, command[1], command[2]));
                        }
                        break;
                    case "deleteFile":
                        System.out.println(fm.deleteFile(command[1]));
                        break;
                    case "zip":
                        String[] files = new String[command.length - 2];
                        for (int i = 2; i < command.length; i++) {
                            files[i-2] = command[i];
                        }
                        System.out.println(fm.filesToZip(command[1], files));
                        break;
                    case "cat":
                        System.out.println(fm.showOnConsole(command[1]));
                        break;
                    case "exit":
                        getExit();
                        break;
                    default:
                        System.out.println("Такой комманды не существует.");
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    private String getExit() {
        isWork = false;
        return "Завершение программы.";
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
