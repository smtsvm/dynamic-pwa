package works.ss.dynamic.pwa.frontend;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import works.ss.dynamic.pwa.backend.Registry;
import works.ss.dynamic.pwa.frontend.crud.BaseCrudView;

/**
 * The main layout. Contains the navigation menu.
 */
@HtmlImport("css/shared-styles.html")
@Theme(value = Lumo.class, variant = "dark")
@PWA(name = "Spring Mongo Starter", shortName = "Spring Mongo")
public class MainLayout extends AppLayout implements RouterLayout {


    public MainLayout() {

        Registry.get().getEntityRepositoryMap().keySet().forEach(e-> {
            addToDrawer(createEntityClassButton(e));
        });
    }

    private Button createEntityClassButton(Class clazz) {
        Button entityButton = new Button(clazz.getSimpleName());
        entityButton.setHeight("75px");
        entityButton.setWidthFull();
        entityButton.addClickListener(e-> {
            setContent(new BaseCrudView(clazz));
        });
        return entityButton;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);


    }
}
