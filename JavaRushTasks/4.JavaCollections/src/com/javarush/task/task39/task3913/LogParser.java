package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<Log> AllLogs;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        this.AllLogs = parsLinesToObjects();
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

    // IPQuery

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
            if (log.user.equals(user))
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

    // UserQuery

    @Override
    public Set<String> getAllUsers() {
        Set<String> result = new HashSet<>();

        for (Log log : AllLogs)
            result.add(log.user);

        return result;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            result.add(log.user);

        return result.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.user.equals(user))
                result.add(log.event);

        return result.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.ip.equals(ip))
                result.add(log.user);

        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.LOGIN)
                result.add(log.user);

        return result;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.DOWNLOAD_PLUGIN)
                result.add(log.user);

        return result;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.WRITE_MESSAGE)
                result.add(log.user);

        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.SOLVE_TASK)
                result.add(log.user);

        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.SOLVE_TASK && log.taskNumber == task)
                result.add(log.user);

        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.DONE_TASK)
                result.add(log.user);

        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.DONE_TASK && log.taskNumber == task)
                result.add(log.user);

        return result;
    }

    // DateQuery

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.user.equals(user) && log.event == event)
                result.add(log.date);

        return result;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.status == Status.FAILED)
                result.add(log.date);

        return result;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.status == Status.ERROR)
                result.add(log.date);

        return result;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        List<Date> list = new ArrayList<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.user.equals(user) && log.event == Event.LOGIN)
                list.add(log.date);

        Collections.sort(list);

        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        List<Date> list = new ArrayList<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.user.equals(user) && log.event == Event.SOLVE_TASK && log.taskNumber == task)
                list.add(log.date);

        Collections.sort(list);

        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        List<Date> list = new ArrayList<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.user.equals(user) && log.event == Event.DONE_TASK && log.taskNumber == task)
                list.add(log.date);

        Collections.sort(list);

        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> result = new TreeSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.user.equals(user) && log.event == Event.WRITE_MESSAGE)
                result.add(log.date);

        return result;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> result = new TreeSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.user.equals(user) && log.event == Event.DOWNLOAD_PLUGIN)
                result.add(log.date);

        return result;
    }

    // EventQuery

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            result.add(log.event);

        return result;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.ip.equals(ip))
                result.add(log.event);

        return result;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.user.equals(user))
                result.add(log.event);

        return result;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.status == Status.FAILED)
                result.add(log.event);

        return result;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.status == Status.ERROR)
                result.add(log.event);

        return result;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int result = 0;
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.SOLVE_TASK && log.taskNumber == task)
                result++;

        return result;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int result = 0;
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.DONE_TASK && log.taskNumber == task)
                result++;

        return result;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.SOLVE_TASK && !result.containsKey(log.taskNumber))
                result.put(log.taskNumber, getNumberOfAttemptToSolveTask(log.taskNumber, after, before));

        return result;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        List<Log> logs = getLogsForPeriod(AllLogs, after, before);

        for (Log log : logs)
            if (log.event == Event.DONE_TASK && !result.containsKey(log.taskNumber))
                result.put(log.taskNumber, getNumberOfSuccessfulAttemptToSolveTask(log.taskNumber, after, before));

        return result;
    }

    // QLQuery

    @Override
    public Set<Object> execute(String query) {
        Set<Object> result = new HashSet<>();

        if (!query.contains("=")) {
            String field = query.substring(3).trim();

            result = AllLogs.stream().map(i -> getValueFromObject(i, field))
                    .collect(Collectors.toSet());
        } else if (!query.contains("and")) {
            String field1 = query.substring(3, query.indexOf("for")).trim();
            String field2 = query.substring(query.indexOf("for") + 4, query.indexOf("=")).trim();
            String value = query.split("=")[1].trim().replaceAll("\"", "");

            result = AllLogs.stream().filter(log -> equalsFields(getValueFromObject(log, field2), value))
                    .map(i -> getValueFromObject(i, field1))
                    .collect(Collectors.toSet());
        } else {
//            get ip for user = "Eduard Petrovich Morozko" and date between "11.12.2013 0:00:00" and "03.01.2014 23:59:59"
            String field1 = query.substring(3, query.indexOf("for")).trim();
            String field2 = query.substring(query.indexOf("for") + 4, query.indexOf("=")).trim();
            String value = query.split("=")[1].trim().split(" and")[0].replaceAll("\"", "");
            String[] dates = query.split("=")[1].split(" and date between ")[1]
                    .replaceAll("\"", "").split(" and");
            SimpleDateFormat formatter = new SimpleDateFormat("d.M.y HH:m:s");
            Date dateAfter;
            Date dateBefore;

            try {
                dateAfter = formatter.parse(dates[0]);
                dateBefore = formatter.parse(dates[1]);

                result = AllLogs.stream().filter(log -> equalsFields(getValueFromObject(log, field2), value)
                        && log.date.getTime() > dateAfter.getTime() && log.date.getTime() < dateBefore.getTime())
                        .map(i -> getValueFromObject(i, field1))
                        .collect(Collectors.toSet());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private Object getValueFromObject(Log log, String fieldName) {
        Object obj = null;

        try {
            Field field = log.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            obj = field.get(log);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }

    private boolean equalsFields(Object obj, String str) {
        boolean result = false;

        switch (obj.getClass().getSimpleName()) {
            case "String":
                result = obj.toString().equals(str);
                break;
            case "Date":
                SimpleDateFormat formatter = new SimpleDateFormat("d.M.y HH:m:s");
                try {
                    result = obj.equals(formatter.parse(str));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "Event":
                result = obj == Event.valueOf(str);
                break;
            case "Integer":
                result = obj.equals(Integer.parseInt(str));
                break;
            case "Status":
                result = obj == Status.valueOf(str);
                break;
        }

        return result;
    }
}