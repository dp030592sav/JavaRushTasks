package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {
    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<Log> logs = getLogsForPeriod(after, before);

        for (Log log : logs)
            set.add(log.ip);

        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<Log> logs = getLogsForPeriod(after, before);

        for (Log log : logs)
            if (log.name.equals(user))
                set.add(log.ip);

        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<Log> logs = getLogsForPeriod(after, before);

        for (Log log : logs)
            if (log.event == event)
                set.add(log.ip);

        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<Log> logs = getLogsForPeriod(after, before);

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
            } else {
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

    private List<Log> parsLinesToObjects(List<String> lines) {
        List<Log> result = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("d.M.y H:m:s");

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

    private List<Log> getLogsForPeriod(Date after, Date before) {
        List<Log> logs = parsLinesToObjects(readLinesFromFolder(logDir));

        List<Log> result = new ArrayList<>();

        for (Log log : logs) {
            if ((after == null || log.date.after(after)) && (before == null || log.date.before(before)))
                result.add(log);
        }

        return result;
    }
}