package ru.itis.services;

import ru.itis.models.DndEntity;

import java.util.List;

public interface DndEntityService<T extends DndEntity> {
    List<T> getAll();
    List<T> getMatching(String prefix);
}
