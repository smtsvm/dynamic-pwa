package works.ss.dynamic.pwa.frontend;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import works.ss.dynamic.pwa.backend.entity.Product;
import works.ss.dynamic.pwa.frontend.crud.SampleCrudView;

/**
 * The main layout. Contains the navigation menu.
 */
@HtmlImport("css/shared-styles.html")
@Theme(value = Lumo.class)
@PWA(name = "Bookstore Starter", shortName = "Bookstore")
public class MainLayout extends FlexLayout implements RouterLayout {
    private Menu menu;

    public MainLayout() {
        setSizeFull();
        setClassName("main-layout");

        menu = new Menu();
        //TODO iterate base entities and add crud views
        menu.addView(SampleCrudView.class, "Product", VaadinIcon.INFO.create());
//        menu.addView(SampleCrudView.class, SampleCrudView.VIEW_NAME,
//                VaadinIcon.EDIT.create());

        add(menu);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);


    }
}
