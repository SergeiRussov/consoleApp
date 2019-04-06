package org.russow.util.fileManager.impl;

import org.russow.util.fileManager.FileManager;

public class ExitProgram implements FileManager {

    private static boolean isWork = true;

    public static boolean isIsWork() {
        return isWork;
    }

    public static void setIsWork(boolean isWork) {
        ExitProgram.isWork = isWork;
    }

    @Override
    public void run(String[] strings) {
        System.out.println(getExit());
    }

    private String getExit() {
        isWork = false;
        return "Завершение программы.";
    }
}
