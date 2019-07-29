package works.ss.dynamic.pwa.frontend;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import works.ss.dynamic.pwa.backend.entity.Category;
import works.ss.dynamic.pwa.backend.entity.Product;
import works.ss.dynamic.pwa.frontend.crud.BaseCrudView;

/**
 * The main layout. Contains the navigation menu.
 */
@HtmlImport("css/shared-styles.html")
@Theme(value = Lumo.class)
@PWA(name = "Spring Mongo Starter", shortName = "Spring Mongo")
public class MainLayout extends HorizontalLayout implements RouterLayout {

    public MainLayout() {
        setSizeFull();
        setClassName("main-layout");

        VerticalLayout buttonsLayout = new VerticalLayout();
        VerticalLayout detailLayout = new VerticalLayout();

        Button productButton = new Button("Product");
        Button categoryButton = new Button("Category");
        buttonsLayout.add(productButton, categoryButton);

        productButton.addClickListener(e-> {
            detailLayout.removeAll();
            detailLayout.add(new BaseCrudView(Product.class));
        });


        categoryButton.addClickListener(e-> {
            detailLayout.removeAll();
            detailLayout.add(new BaseCrudView(Category.class));
        });

        add(buttonsLayout, detailLayout);

        buttonsLayout.setWidth("100px");



    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);


    }
}
