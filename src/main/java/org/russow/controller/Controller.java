package org.russow.controller;

import org.russow.util.fileManager.FileManager;
import org.russow.util.fileManager.impl.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    private Map<String, FileManager> commands = new HashMap<>();

    {
        commands.put("help", new Ref());
        commands.put("createFile", new CreateFile());
        commands.put("writeFile", new WriteFile());
        commands.put("cp", new CopyFile());
        commands.put("deleteFile", new DeleteFile());
        commands.put("zip", new FilesToZip());
        commands.put("cat", new ShowOnConsole());
        commands.put("exit", new ExitProgram());
    }

    public void getCommand() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (ExitProgram.isIsWork()) {
                System.out.print("Введите команду: ");
                String[] command = reader.readLine().split(" ");
                if (commands.containsKey(command[0])) {
                    commands.get(command[0]).run(command);
                } else {
                    System.out.println("Такой команды не существует. Вызовите справку.");
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
