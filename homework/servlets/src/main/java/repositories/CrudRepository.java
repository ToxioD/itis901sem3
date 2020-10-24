package repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    boolean save(T entity);
    boolean updateById(Long id, T entity);
    boolean delete(T entity);
    boolean deleteById(Long id);
}
