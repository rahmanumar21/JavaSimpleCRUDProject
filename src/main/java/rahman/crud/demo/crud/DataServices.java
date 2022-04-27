package rahman.crud.demo.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataServices {

    @Autowired
    private DataRepository repo;

    public List<Data> listAll() {
        return (List<Data>) repo.findAll();
    }

    public void save(Data data) {
        repo.save(data);
    }

    public Data get(Integer id) throws DataNotFoundException {
        Optional<Data> result = repo.findById(id);

        if (result.isPresent()) {
            return result.get();
        }
        throw new DataNotFoundException("Could not find any clients with ID " + id);
    }

    public void delete(Integer id) throws DataNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new DataNotFoundException("Could not find any clients with ID " + id);
        }
        repo.deleteById(id);
    }
}
