package works.ss.dynamic.pwa.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import works.ss.dynamic.pwa.backend.entity.BaseEntity;
import works.ss.dynamic.pwa.backend.entity.Product;
import works.ss.dynamic.pwa.backend.repository.CategoryRepository;
import works.ss.dynamic.pwa.backend.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BaseService{

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    public void delete(Class clazz, String id){
        getRelatedRepository(clazz).deleteById(id);
    }

    public void delete(BaseEntity baseEntity){
        getRelatedRepository(baseEntity.getClass()).delete(baseEntity);
    }

    public List<BaseEntity> getAll(Class clazz) {
        return getRelatedRepository(clazz).findAll();
    }

    public Optional<BaseEntity> findEntity(Class clazz, String id) {
        return getRelatedRepository(clazz).findById(id);
    }

    public void saveEntity(BaseEntity baseEntity){
        getRelatedRepository(baseEntity.getClass()).save(baseEntity);
    }


    private MongoRepository getRelatedRepository(Class clazz) {
        if(clazz == Product.class)
            return productRepository;
        else
            return categoryRepository;
    }
}
