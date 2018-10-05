package com.javarush.task.task39.task3913.query;

import com.javarush.task.task39.task3913.Event;
import com.javarush.task.task39.task3913.Status;

import java.util.Date;
import java.util.Set;

public interface IPQuery {
    //  должен возвращать количество уникальных IP адресов за выбранный период
    int getNumberOfUniqueIPs(Date after, Date before);

    // должен возвращать множество, содержащее все не повторяющиеся IP
    Set<String> getUniqueIPs(Date after, Date before);

    // должен возвращать IP, с которых работал переданный пользователь
    Set<String> getIPsForUser(String user, Date after, Date before);

    // должен возвращать IP, с которых было произведено переданное событие
    Set<String> getIPsForEvent(Event event, Date after, Date before);

    // должен возвращать IP, события с которых закончилось переданным статусом
    Set<String> getIPsForStatus(Status status, Date after, Date before);
}