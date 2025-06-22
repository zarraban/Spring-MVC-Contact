package org.example.app.service;

import java.util.List;

public interface BaseService <T1,T2>{

    void create(T2 request);

    List<T1> readAll();

    T1 readById(Long id);

    boolean deleteById(Long id);

    boolean updateById(Long id, T2 request);

    T1 readLast();

}
