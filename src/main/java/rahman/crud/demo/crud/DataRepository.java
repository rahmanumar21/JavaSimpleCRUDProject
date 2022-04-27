package rahman.crud.demo.crud;

import org.springframework.data.repository.CrudRepository;

public interface DataRepository extends CrudRepository<Data, Integer> {
    long countById(Integer id);
}
