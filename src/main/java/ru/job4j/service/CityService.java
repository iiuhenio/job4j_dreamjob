package ru.job4j.service;

import ru.job4j.model.City;

import java.util.Collection;

public interface CityService {
    Collection<City> findAll();
}