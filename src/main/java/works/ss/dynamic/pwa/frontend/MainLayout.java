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
import works.ss.dynamic.pwa.backend.Registry;
import works.ss.dynamic.pwa.backend.entity.Category;
import works.ss.dynamic.pwa.backend.entity.Product;
import works.ss.dynamic.pwa.frontend.crud.BaseCrudView;

import java.util.Map;

/**
 * The main layout. Contains the navigation menu.
 */
@HtmlImport("css/shared-styles.html")
@Theme(value = Lumo.class)
@PWA(name = "Spring Mongo Starter", shortName = "Spring Mongo")
public class MainLayout extends HorizontalLayout implements RouterLayout {

    private final VerticalLayout detailLayout;

    public MainLayout() {
        setSizeFull();
        setClassName("main-layout");

        VerticalLayout buttonsLayout = new VerticalLayout();
        detailLayout = new VerticalLayout();


        Registry.get().getEntityRepositoryMap().keySet().forEach(e-> {
            buttonsLayout.add(createEntityClassButton(e));
        });


        add(buttonsLayout, detailLayout);

        buttonsLayout.setWidth("100px");
        setHeight("800px");
        detailLayout.setHeight("800px");



    }

    private Button createEntityClassButton(Class clazz) {
        Button entityButton = new Button(clazz.getSimpleName());
        entityButton.addClickListener(e-> {
            detailLayout.removeAll();
            detailLayout.add(new BaseCrudView(clazz));
        });
        return entityButton;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);


    }
}
