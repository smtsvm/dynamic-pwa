package works.ss.dynamic.pwa.frontend.crud;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import works.ss.dynamic.pwa.backend.Registry;
import works.ss.dynamic.pwa.backend.entity.BaseEntity;
import works.ss.dynamic.pwa.backend.entity.Category;
import works.ss.dynamic.pwa.backend.entity.Product;
import works.ss.dynamic.pwa.frontend.MainLayout;

/**
 * A view for performing create-read-update-delete operations on products.
 *
 * See also {@link SampleCrudLogic} for fetching the entity, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
@Route(value = "Inventory", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class SampleCrudView extends HorizontalLayout
        implements HasUrlParameter<String> {

    private BaseEntityGrid grid;
    private BaseEntityForm form;
    private TextField filter;

    private SampleCrudLogic viewLogic = new SampleCrudLogic(this,Product.class);
    private Button newBaseEntity;

    private BaseEntityDataProvider dataProvider;


    public SampleCrudView() {
        dataProvider = new BaseEntityDataProvider(Product.class);
        setSizeFull();
        HorizontalLayout topLayout = createTopBar();

        grid = new BaseEntityGrid();
        grid.setDataProvider(dataProvider);
        grid.asSingleSelect().addValueChangeListener(
                event -> viewLogic.rowSelected(event.getValue()));

        form = new BaseEntityForm(viewLogic);

        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(topLayout);
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);

        add(barAndGridLayout);
        add(form);

        viewLogic.init();
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
        newBaseEntity.addClickListener(click -> viewLogic.newEntiy());
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

    public void showError(String msg) {
        Notification.show(msg);
    }

    public void showSaveNotification(String msg) {
        Notification.show(msg);
    }


    public void clearSelection() {
        grid.getSelectionModel().deselectAll();
    }

    public void selectRow(BaseEntity row) {
        grid.getSelectionModel().select(row);
    }

    public BaseEntity getSelectedRow() {
        return grid.getSelectedRow();
    }

    public void updateEntity(BaseEntity product) {
        dataProvider.save(product);
    }

    public void removeEntity(BaseEntity product) {
        dataProvider.delete(product);
    }

    public void editEntity(BaseEntity product) {
        showForm(product != null);
        form.editEntity(product);
    }

    public void showForm(boolean show) {
        form.setVisible(show);

        /* FIXME The following line should be uncommented when the CheckboxGroup
         * issue is resolved. The category CheckboxGroup throws an
         * IllegalArgumentException when the form is disabled.
         */
        //form.setEnabled(show);
    }

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {
        viewLogic.enter(parameter);
    }
}
