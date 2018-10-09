package com.javarush.task.task39.task3913.query;

import com.javarush.task.task39.task3913.Event;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface EventQuery {
    // должен возвращать количество событий за указанный период
    int getNumberOfAllEvents(Date after, Date before);

    // должен возвращать все события за указанный период
    Set<Event> getAllEvents(Date after, Date before);

    // должен возвращать события, которые происходили с указанного IP
    Set<Event> getEventsForIP(String ip, Date after, Date before);

    // должен возвращать события, которые инициировал определенный пользователь
    Set<Event> getEventsForUser(String user, Date after, Date before);

    // должен возвращать события, которые не выполнились
    Set<Event> getFailedEvents(Date after, Date before);

    // должен возвращать события, которые завершились ошибкой
    Set<Event> getErrorEvents(Date after, Date before);

    // должен возвращать количество попыток решить определенную задачу
    int getNumberOfAttemptToSolveTask(int task, Date after, Date before);

    // должен возвращать количество успешных решений определенной задачи
    int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before);

    // должен возвращать мапу (номер_задачи : количество_попыток_решить_ее)
    Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before);

    // должен возвращать мапу (номер_задачи : сколько_раз_ее_решили)
    Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before);
}