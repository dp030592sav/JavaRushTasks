package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery {
    private Path logDir;
    private List<Log> AllLogs;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        this.AllLogs = parsLinesToObjects();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            set.add(log.ip);

        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.name.equals(user))
                set.add(log.ip);

        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == event)
                set.add(log.ip);

        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.status == status)
                set.add(log.ip);

        return set;
    }

    private List<File> getLogFilesFromFolder(final File folder) {
        List<File> files = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(getLogFilesFromFolder(fileEntry));
            } else if (fileEntry.getName().endsWith(".log")) {
                files.add(fileEntry);
            }
        }

        return files;
    }

    private List<String> readLinesFromFolder(Path path) {
        ArrayList<String> lines = new ArrayList<>();
        List<File> files = getLogFilesFromFolder(new File(path.toString()));

        for (File fileEntry : files) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileEntry.getPath()));
                while (reader.ready())
                    lines.add(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lines;
    }

    private List<Log> parsLinesToObjects() {
        List<Log> result = new ArrayList<>();
        List<String> lines = readLinesFromFolder(logDir);
        SimpleDateFormat formatter = new SimpleDateFormat("d.M.y HH:m:s");

        for (String line : lines) {
            String[] components = line.split("\\t");

            try {
                Date date = formatter.parse(components[2]);
                String[] strings = components[3].split(" ");
                Log log = new Log(
                        components[0], // id
                        components[1], // name
                        date, // date
                        Event.valueOf(strings[0]), // event
                        strings.length > 1 ? Integer.parseInt(strings[1]) : -1, // taskNumber
                        Status.valueOf(components[4])); // status
                result.add(log);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    private List<Log> getLogsForPeriod(List<Log> logs, Date after, Date before) {
        List<Log> result = new ArrayList<>();

        for (Log log : logs) {
            if ((after == null || log.date.getTime() >= after.getTime())
                    && (before == null || log.date.getTime() <= before.getTime()))
                result.add(log);
        }

        return result;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> result = new HashSet<>();

        for (Log log : AllLogs)
            result.add(log.name);

        return result;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            result.add(log.name);

        return result.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.name.equals(user))
                result.add(log.event);

        return result.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.ip.equals(ip))
                result.add(log.name);

        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.LOGIN)
                result.add(log.name);

        return result;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.DOWNLOAD_PLUGIN)
                result.add(log.name);

        return result;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.WRITE_MESSAGE)
                result.add(log.name);

        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.SOLVE_TASK)
                result.add(log.name);

        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.SOLVE_TASK && log.taskNumber == task)
                result.add(log.name);

        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.DONE_TASK)
                result.add(log.name);

        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.DONE_TASK && log.taskNumber == task)
                result.add(log.name);

        return result;
    }
}