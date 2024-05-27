package my.web.app.shop.repo;

import my.web.app.shop.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByTocken(String tocken);
}
