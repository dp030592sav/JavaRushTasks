package com.javarush.task.task39.task3913.query;

import java.util.Date;
import java.util.Set;

public interface UserQuery {
    // должен возвращать всех пользователей
    Set<String> getAllUsers();

    // должен возвращать количество уникальных пользователей
    int getNumberOfUsers(Date after, Date before);

    // должен возвращать количество событий от определенного пользователя
    int getNumberOfUserEvents(String user, Date after, Date before);

    // должен возвращать пользователей с определенным IP
    Set<String> getUsersForIP(String ip, Date after, Date before);

    //  должен возвращать пользователей, которые были залогинены
    Set<String> getLoggedUsers(Date after, Date before);

    // должен возвращать пользователей, которые скачали плагин
    Set<String> getDownloadedPluginUsers(Date after, Date before);

    // должен возвращать пользователей, которые отправили сообщение
    Set<String> getWroteMessageUsers(Date after, Date before);

    // должен возвращать пользователей, которые решали любую задачу
    Set<String> getSolvedTaskUsers(Date after, Date before);

    // должен возвращать пользователей, которые решали задачу с номером task
    Set<String> getSolvedTaskUsers(Date after, Date before, int task);

    // должен возвращать пользователей, которые решали любую задачу
    Set<String> getDoneTaskUsers(Date after, Date before);

    // должен возвращать пользователей, которые решали задачу с номером task
    Set<String> getDoneTaskUsers(Date after, Date before, int task);
}