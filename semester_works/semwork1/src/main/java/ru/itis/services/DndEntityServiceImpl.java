package ru.itis.services;

import ru.itis.models.DndEntity;
import ru.itis.repositories.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DndEntityServiceImpl<T extends DndEntity> implements DndEntityService<T> {

    private CrudRepository<T> entityRepository;

    public DndEntityServiceImpl(CrudRepository<T> entityRepository) {this.entityRepository = entityRepository;}

    @Override
    public List<T> getAll() {
        return entityRepository.findAll();
    }

    @Override
    public List<T> getMatching(String prefix) {
        List<T> entities = getAll();
        return entities.stream()
                .filter(x -> x.getName().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }
}
