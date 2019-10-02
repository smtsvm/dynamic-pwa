package works.ss.dynamic.pwa.backend.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document(collection = "product")
@Data
public class Product extends BaseEntity implements Serializable {


    @Min(0)
    private long price;

    private Category category;


    @Min(value = 0, message = "Can't have negative amount in stock")
    private int stockCount = 0;

    @NotNull
    private Availability availability = Availability.COMING;

}
