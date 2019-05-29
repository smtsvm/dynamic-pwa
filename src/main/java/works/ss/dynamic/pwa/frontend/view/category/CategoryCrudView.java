package works.ss.dynamic.pwa.frontend.view.category;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import works.ss.dynamic.pwa.backend.entity.Category;
import works.ss.dynamic.pwa.frontend.MainLayout;
import works.ss.dynamic.pwa.frontend.crud.BaseEntityDataProvider;
import works.ss.dynamic.pwa.frontend.crud.BaseEntityGrid;
import works.ss.dynamic.pwa.frontend.crud.BaseCrudView;

@Route(value = "category", layout = MainLayout.class)
public class CategoryCrudView extends BaseCrudView
        implements HasUrlParameter<String> {



    public CategoryCrudView() {
        dataProvider = new BaseEntityDataProvider(Category.class);
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
