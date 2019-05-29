package works.ss.dynamic.pwa.frontend.crud;

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

/**
 * A view for performing create-read-update-delete operations on products.
 *
 * See also {@link BaseCrudLogic} for fetching the entity, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
@Route(value = "base", layout = MainLayout.class)
public class BaseCrudView extends HorizontalLayout
        implements HasUrlParameter<String> {

    public BaseEntityGrid grid;
    public TextField filter;

    public Button newBaseEntity;

    public BaseEntityDataProvider dataProvider;


    public BaseCrudView() {


    }

    public HorizontalLayout createTopBar() {
        filter = new TextField();
        filter.setPlaceholder("Filter name, availability or category");
        // Apply the filter to grid's entity provider. TextField value is never null
        filter.addValueChangeListener(event -> dataProvider.setFilter(event.getValue()));
        filter.addFocusShortcut(Key.KEY_F, KeyModifier.CONTROL);

        newBaseEntity = new Button("New product");
        newBaseEntity.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        newBaseEntity.setIcon(VaadinIcon.PLUS_CIRCLE.create());
        // CTRL+N will create a new window which is unavoidable
        newBaseEntity.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.add(filter);
        topLayout.add(newBaseEntity);
        topLayout.setVerticalComponentAlignment(Alignment.START, filter);
        topLayout.expand(filter);
        return topLayout;
    }



    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {
    }
}
