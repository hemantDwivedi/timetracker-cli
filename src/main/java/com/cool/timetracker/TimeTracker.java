package com.cool.timetracker;

import com.cool.timetracker.data.Category;
import com.cool.timetracker.data.CurrentTasks;
import com.cool.timetracker.data.Task;
import com.cool.timetracker.util.ArgUtil;
import com.cool.timetracker.util.Args;
import com.cool.timetracker.util.FileUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class TimeTracker {
    public static void main(String[] args) throws IOException {
        ArgUtil argUtil = new ArgUtil();
        Args arguments = argUtil.parseArgs(args);

        FileUtil fileUtil = new FileUtil();
        CurrentTasks currentTasks = fileUtil.getSavedTasks();
        switch (arguments.getCommand()) {
            case TASK_START -> {
                Task task = new Task(arguments.getTaskName(), new Category(arguments.getCategoryName()));
                currentTasks.startTask(task);
            }
            case TASK_STOP -> currentTasks.completeTask(arguments.getTaskName());
            case REPORT_TASK -> {
                Map<String, Duration> taskReport = currentTasks.getTaskReport();
                for (Map.Entry<String, Duration> entry : taskReport.entrySet()){
                    System.out.println("Task: " + entry.getKey());
                    System.out.println("Duration in minutes: " + entry.getValue().toMinutes());
                }
            }
            case REPORT_CATEGORY -> {
                Map<String, Duration> categoryReport = currentTasks.getCategoryReport();
                for (Map.Entry<String, Duration> entry : categoryReport.entrySet()){
                    System.out.println("Category: " + entry.getKey());
                    System.out.println("Duration in minutes: " + entry.getValue().toMinutes());
                }
            }
        };

        System.out.println(currentTasks);
        fileUtil.saveTasksToFile(currentTasks);
    }
}
