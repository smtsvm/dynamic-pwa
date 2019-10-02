package works.ss.dynamic.pwa.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import works.ss.dynamic.pwa.backend.entity.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
