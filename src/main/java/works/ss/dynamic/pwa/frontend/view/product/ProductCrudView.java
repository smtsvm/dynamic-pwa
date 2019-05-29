package works.ss.dynamic.pwa.frontend.view.product;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import works.ss.dynamic.pwa.backend.entity.Product;
import works.ss.dynamic.pwa.frontend.MainLayout;
import works.ss.dynamic.pwa.frontend.crud.BaseEntityDataProvider;
import works.ss.dynamic.pwa.frontend.crud.BaseEntityGrid;
import works.ss.dynamic.pwa.frontend.view.BaseCrudView;

@Route(value = "product", layout = MainLayout.class)
public class ProductCrudView extends BaseCrudView
        implements HasUrlParameter<String> {




    public ProductCrudView() {
        dataProvider = new BaseEntityDataProvider(Product.class);
        setSizeFull();
        HorizontalLayout topLayout = createTopBar();

        grid = new BaseEntityGrid();
        grid.setDataProvider(dataProvider);


        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(topLayout);
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);

        add(barAndGridLayout);

    }



    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {
    }
}
