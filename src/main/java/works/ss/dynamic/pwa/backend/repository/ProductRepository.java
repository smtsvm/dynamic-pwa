package works.ss.dynamic.pwa.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import works.ss.dynamic.pwa.backend.entity.Availability;
import works.ss.dynamic.pwa.backend.entity.Product;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByAvailability(Availability availability);
    List<Product> findByName(String name);
}
