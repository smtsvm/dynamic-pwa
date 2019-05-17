package works.ss.dynamic.pwa.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.ss.dynamic.pwa.backend.entity.Category;
import works.ss.dynamic.pwa.backend.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService extends BaseService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findByDescription(String description) {
        return categoryRepository.findByCategoryDetail(description);
    }
}
