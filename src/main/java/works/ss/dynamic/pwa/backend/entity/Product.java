package works.ss.dynamic.pwa.backend.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Document(collection = "product")
public class Product extends BaseEntity implements Serializable {


    @Min(0)
    private BigDecimal price = BigDecimal.ZERO;

    private List<Category> category;

    @Min(value = 0, message = "Can't have negative amount in stock")
    private int stockCount = 0;

    @NotNull
    private Availability availability = Availability.COMING;

    public Product() {
        super();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
