package my.web.app.shop.repo;

import my.web.app.shop.models.Good;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IGoodRepository extends CrudRepository<Good,Long> {
    Good findByCategId(int categId);
    List<Good> findAllByCategId(int categId);
}
