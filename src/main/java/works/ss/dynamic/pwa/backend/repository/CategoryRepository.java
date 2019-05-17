package works.ss.dynamic.pwa.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import works.ss.dynamic.pwa.backend.entity.Category;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findByCategoryDetail(String categoryDetail);
    List<Category> findByName(String name);
}
