package rahman.crud.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import rahman.crud.demo.crud.Data;
import rahman.crud.demo.crud.DataRepository;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DataTests {

    @Autowired
    private DataRepository repo;

    @Test
    public void testAddNew() {
        Data data = new Data();
        data.setFirstName("A.");
        data.setLastName("Rahman 6");
        data.setEmail("rahman.uts.fti15.6@gmail.com");
        data.setPhone(576982748);

        Data saveData = repo.save(data);

        // Expect
        Assertions.assertThat(saveData).isNotNull();
        Assertions.assertThat(saveData.getId()).isGreaterThan(0);

    }


    @Test
    public void testListAll() {
        Iterable<Data> data = repo.findAll();
        Assertions.assertThat(data).hasSizeGreaterThan(0);

        for (Data user : data) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        Optional<Data> optionalData = repo.findById(id);
        Data data = optionalData.get();
        data.setPhone(999999999);
        repo.save(data);

        Data updatedData = repo.findById(id).get();

        // Expect
        Assertions.assertThat(updatedData.getPhone()).isEqualTo(999999999);

    }

    @Test
    public void testGet() {
        Integer id = 1;
        Optional<Data> optionalData = repo.findById(id);

        // Expect
        Assertions.assertThat(optionalData).isPresent();
        System.out.println(optionalData.get());
    }

    @Test
    public void testDelete() {
        Integer id = 5;
        repo.deleteById(id);

        Optional<Data> optionalData = repo.findById(id);

        // Expect
        Assertions.assertThat(optionalData).isNotPresent();
    }


}
