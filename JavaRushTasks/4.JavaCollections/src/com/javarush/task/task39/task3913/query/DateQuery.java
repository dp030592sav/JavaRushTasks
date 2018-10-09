package com.javarush.task.task39.task3913.query;

import com.javarush.task.task39.task3913.Event;

import java.util.Date;
import java.util.Set;

public interface DateQuery {
    // должен возвращать даты, когда определенный пользователь произвел определенное событие
    Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before);

    // должен возвращать даты, когда любое событие не выполнилось (статус FAILED)
    Set<Date> getDatesWhenSomethingFailed(Date after, Date before);

    // должен возвращать даты, когда любое событие закончилось ошибкой (статус ERROR)
    Set<Date> getDatesWhenErrorHappened(Date after, Date before);

    // должен возвращать дату, когда пользователь залогинился впервые за указанный период. Если такой даты в логах нет - null
    Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before);

    // должен возвращать дату, когда пользователь впервые попытался решить определенную задачу. Если такой даты в логах нет - null
    Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before);

    // должен возвращать дату, когда пользователь впервые решил определенную задачу. Если такой даты в логах нет - null
    Date getDateWhenUserDoneTask(String user, int task, Date after, Date before);

    // должен возвращать даты, когда пользователь написал сообщение
    Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before);

    // должен возвращать даты, когда пользователь скачал плагин
    Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before);
}