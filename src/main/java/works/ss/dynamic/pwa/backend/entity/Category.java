package works.ss.dynamic.pwa.backend.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "category")
public class Category extends BaseEntity implements Serializable  {

    //TODO for test
    private String categoryDetail;

    public String getCategoryDetail() {
        return categoryDetail;
    }

    public void setCategoryDetail(String categoryDetail) {
        this.categoryDetail = categoryDetail;
    }
}
