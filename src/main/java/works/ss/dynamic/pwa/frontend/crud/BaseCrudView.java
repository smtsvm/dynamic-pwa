package works.ss.dynamic.pwa.frontend.crud;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.*;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;
import works.ss.dynamic.pwa.backend.Registry;
import works.ss.dynamic.pwa.backend.entity.BaseEntity;
import works.ss.dynamic.pwa.frontend.MainLayout;

import java.time.format.DateTimeFormatter;
import java.util.Collection;


@Route(value = "", layout = MainLayout.class)
public class BaseCrudView extends HorizontalLayout implements CrudListener<BaseEntity> {


    private TextField nameFilter = new TextField();

    private Class clazz;

    public BaseCrudView() {
//        this(BaseEntity.class);

    }

    public BaseCrudView(Class clazz) {

        this.clazz = clazz;
        add(getConfiguredCrud());
        setWidth("100%");
        setHeight("800px");
    }

    private Component getConfiguredCrud() {
        GridCrud<BaseEntity> crud = new GridCrud<>(clazz, new HorizontalSplitCrudLayout());
        crud.setCrudListener(this);

        DefaultCrudFormFactory<BaseEntity> formFactory = new DefaultCrudFormFactory<>(clazz);
        crud.setCrudFormFactory(formFactory);

        formFactory.setUseBeanValidation(true);

        formFactory.setErrorListener(e -> {
            Notification.show("Custom error message");
            e.printStackTrace();
        });


        formFactory.setDisabledProperties("id");


        crud.getGrid().setColumnReorderingAllowed(true);




        formFactory.setFieldCreationListener(CrudOperation.ADD, "name", f -> f.setValue("default name"));

        formFactory.setButtonCaption(CrudOperation.ADD, "Add new Entity");
        crud.setRowCountCaption("%d user(s) found");

        crud.setClickRowToUpdate(true);
        crud.setUpdateOperationVisible(false);

        nameFilter.setPlaceholder("filter by name...");
        nameFilter.addValueChangeListener(e -> crud.refreshGrid());
        crud.getCrudLayout().addFilterComponent(nameFilter);



        Button clearFilters = new Button(null, VaadinIcon.ERASER.create());
        clearFilters.addClickListener(event -> {
            nameFilter.clear();
        });
        crud.getCrudLayout().addFilterComponent(clearFilters);

//        crud.setFindAllOperation(
//                DataProvider.fromCallbacks(
//                        query -> Registry.get().getBaseService().
//                                UserRepository.findByNameLike(nameFilter.getValue(), groupFilter.getValue(), query.getOffset(), query.getLimit()).stream(),
//                        query -> UserRepository.countByNameLike(nameFilter.getValue(), groupFilter.getValue()))
//        );
        return crud;
    }




    @Override
    public Collection<BaseEntity> findAll() {
        return Registry.get().getBaseService().getAll(clazz);
    }

    @Override
    public BaseEntity add(BaseEntity baseEntity) {
        return Registry.get().getBaseService().saveEntity(baseEntity);
    }

    @Override
    public BaseEntity update(BaseEntity baseEntity) {
        return Registry.get().getBaseService().saveEntity(baseEntity);
    }

    @Override
    public void delete(BaseEntity baseEntity) {
        Registry.get().getBaseService().delete(clazz, baseEntity.getId());
    }
}
