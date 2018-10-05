package com.javarush.task.task39.task3913;

import java.util.*;

public class Log {
    public String ip;
    public String name;
    public Date date;
    public Event event;
    public int taskNumber;
    public Status status;

    public Log(String ip, String name, Date date, Event event, int taskNumber, Status status) {
        this.ip = ip;
        this.name = name;
        this.date = date;
        this.event = event;
        this.taskNumber = taskNumber;
        this.status = status;
    }
}
