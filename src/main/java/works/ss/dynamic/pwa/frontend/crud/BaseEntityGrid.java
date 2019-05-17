package works.ss.dynamic.pwa.frontend.crud;

import com.vaadin.flow.component.grid.Grid;
import works.ss.dynamic.pwa.backend.entity.BaseEntity;
import works.ss.dynamic.pwa.backend.entity.Category;
import works.ss.dynamic.pwa.backend.entity.Product;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory entity source that is suitable for small
 * entity sets.
 */
public class BaseEntityGrid extends Grid<BaseEntity> {

    public BaseEntityGrid() {
        setSizeFull();

        addColumn(BaseEntity::getName)
                .setHeader("Entity name")
                .setFlexGrow(20)
                .setSortable(true);

        // Format and add " €" to price
        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);


    }

    public BaseEntity getSelectedRow() {
        return asSingleSelect().getValue();
    }

    public void refresh(Product product) {
        getDataCommunicator().refresh(product);
    }

    private String formatCategories(Product product) {
        if (product.getCategory() == null || product.getCategory().isEmpty()) {
            return "";
        }
        return product.getCategory().stream()
                .sorted(Comparator.comparing(Category::getId))
                .map(Category::getName).collect(Collectors.joining(", "));
    }
}
