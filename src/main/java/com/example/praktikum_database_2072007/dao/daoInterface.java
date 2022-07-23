package com.example.praktikum_database_2072007.dao;

import javafx.collections.ObservableList;

public interface daoInterface<T> {

    ObservableList<T> getData();
    void addData(T data);
    void deleteData(T data);
}
