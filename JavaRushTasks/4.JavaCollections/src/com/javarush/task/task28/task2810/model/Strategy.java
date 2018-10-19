package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

// Он будет отвечать за получение данных с сайта(паттерн Strategy).
public interface Strategy {
    // будет возвращать список вакансий.
    List<Vacancy> getVacancies(String searchString);
}