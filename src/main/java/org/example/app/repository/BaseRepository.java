package org.example.app.repository;

import org.example.app.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface BaseRepository <T,T2> {

    void create(T2 request);

    Optional<List<T>> readAll();

    Optional<T> readById(Long id);

    boolean updateById(Long id, T2 request);

    boolean deleteById(Long id);

    Optional<T> readLast();

}
