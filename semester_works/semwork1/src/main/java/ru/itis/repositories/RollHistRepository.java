package ru.itis.repositories;

import ru.itis.models.Roll;

import java.util.List;

public interface RollHistRepository extends CrudRepository<Roll> {
    List<Roll> findAllByUserId(Long userId);
    void deleteLastByUserId(Long userId);
}
