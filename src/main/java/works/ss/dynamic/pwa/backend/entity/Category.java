package works.ss.dynamic.pwa.backend.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "category")
@Data
public class Category extends BaseEntity implements Serializable  {

    private String categoryDetail;

}
