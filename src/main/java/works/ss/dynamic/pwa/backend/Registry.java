package works.ss.dynamic.pwa.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import works.ss.dynamic.pwa.backend.service.BaseService;
import works.ss.dynamic.pwa.backend.service.CategoryService;
import works.ss.dynamic.pwa.backend.service.ProductService;

import javax.annotation.PostConstruct;

@Component
public class Registry {

    private static Registry INSTANCE;

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BaseService baseService;


    @PostConstruct
    private void init() {
        INSTANCE = this;
    }

    public static Registry get() {
        return INSTANCE;
    }

    public ProductService getProductService() {
        return productService;
    }


    public CategoryService getCategoryService() {
        return categoryService;
    }

    public BaseService getBaseService() {
        return baseService;
    }
}
