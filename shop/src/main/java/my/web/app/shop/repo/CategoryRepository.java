package my.web.app.shop.repo;

import my.web.app.shop.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long>{
}
