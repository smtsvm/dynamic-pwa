package works.ss.dynamic.pwa.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.ss.dynamic.pwa.backend.entity.Availability;
import works.ss.dynamic.pwa.backend.entity.Product;
import works.ss.dynamic.pwa.backend.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService extends BaseService{

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findByAvailability(Availability availability) {
        return productRepository.findByAvailability(availability);
    }
}
