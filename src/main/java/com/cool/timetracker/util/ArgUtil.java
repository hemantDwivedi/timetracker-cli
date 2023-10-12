package com.cool.timetracker.util;

import com.cool.timetracker.Logger;
import com.cool.timetracker.data.Category;

public class ArgUtil {
    public Args parseArgs(String[] args) {
        if (!validate(args)) {
            throw new RuntimeException("Invalid arguments");
        }
        Args argsObj = new Args();
        String cmdString = args[0];
        Commands command = switch (cmdString) {
            case "start" -> Commands.TASK_START;
            case "stop" -> Commands.TASK_STOP;
            case "report" ->
                    "tasks".equals(args[1]) ? Commands.REPORT_TASK : "category".equals(args[1]) ? Commands.REPORT_CATEGORY : null;
            default -> throw new IllegalStateException("Invalid input arguments");
        };
        argsObj.setCommand(command);
        if (Commands.TASK_START.equals(command) || Commands.TASK_STOP.equals(command)) {
            argsObj.setTaskName(args[1]);
            argsObj.setCategoryName(args.length == 3 ? args[2] : Category.NONE);
        }
        return argsObj;
    }

    public boolean validate(String[] args) {
        if (args.length < 2) {
            Logger.log("Error! Not enough arguments!");
            return false;
        }
        return true;
    }
}
