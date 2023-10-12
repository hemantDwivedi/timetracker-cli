package com.cool.timetracker.processors;

import com.cool.timetracker.data.Task;

public interface Processor {
    public void process(Task task);
}
