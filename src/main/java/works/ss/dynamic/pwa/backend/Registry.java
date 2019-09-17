package works.ss.dynamic.pwa.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import works.ss.dynamic.pwa.backend.service.BaseService;
import works.ss.dynamic.pwa.backend.service.CategoryService;
import works.ss.dynamic.pwa.backend.service.ProductService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class Registry {

    private static Registry INSTANCE;

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BaseService baseService;

    private Map<Class, Class> entityRepositoryMap = new HashMap<>();

    @PostConstruct
    private void init() {
        INSTANCE = this;
    }

    public static Registry get() {
        return INSTANCE;
    }

    public BaseService getBaseService() {
        return baseService;
    }


    public void addEntityToMap(Class entity, Class repository) {
        entityRepositoryMap.putIfAbsent(entity, repository);
    }

    public Map<Class, Class> getEntityRepositoryMap() {
        return entityRepositoryMap;
    }
}
