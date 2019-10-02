package works.ss.dynamic.pwa.frontend.crud;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.layout.impl.WindowBasedCrudLayout;
import works.ss.dynamic.pwa.backend.Registry;
import works.ss.dynamic.pwa.backend.entity.BaseEntity;
import works.ss.dynamic.pwa.frontend.MainLayout;
import works.ss.dynamic.pwa.frontend.crud.provider.BaseEntityCrudProvider;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;


@Route(value = "", layout = MainLayout.class)
public class BaseCrudView extends HorizontalLayout implements CrudListener<BaseEntity> {


    private TextField nameFilter = new TextField();

    private Class clazz;
    private GridCrud<BaseEntity> crud;
    private CustomCrudFormFactory<BaseEntity> formFactory;

    public BaseCrudView() {

    }

    public BaseCrudView(Class clazz) {

        this.clazz = clazz;
        add(getConfiguredCrud());
        setWidth("100%");
        setHeight("100%");
    }

    private Component getConfiguredCrud() {
        crud = new GridCrud<>(clazz, new WindowBasedCrudLayout());
        crud.setCrudListener(this);

        formFactory = new CustomCrudFormFactory<>(clazz);
        crud.setCrudFormFactory(formFactory);

        formFactory.setErrorListener(e -> {
            Notification.show("Custom error message");
            e.printStackTrace();
        });

        manipulateColumns();

        crud.getGrid().setColumnReorderingAllowed(true);



        formFactory.setButtonCaption(CrudOperation.ADD, "Add new Entity");
        crud.setRowCountCaption("%d found");

        formFactory.setDisabledProperties("id");

        crud.setUpdateOperationVisible(true);

        nameFilter.setPlaceholder("filter by name...");
        nameFilter.addValueChangeListener(e -> crud.refreshGrid());
        crud.getCrudLayout().addFilterComponent(nameFilter);







        Button clearFilters = new Button(null, VaadinIcon.ERASER.create());
        clearFilters.addClickListener(event -> {
            nameFilter.clear();
        });
        crud.getCrudLayout().addFilterComponent(clearFilters);
        crud.getGrid().setPageSize(10);
        crud.setClickRowToUpdate(false);

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

    private void manipulateColumns() {
        for(Field field : clazz.getDeclaredFields()) {
            if(field.getType() == Boolean.class || field.getType() == boolean.class) {
                crud.getGrid().getColumnByKey(field.getName()).setVisible(false);
            }
            if(field.getType() == List.class) {
                crud.getGrid().getColumnByKey(field.getName()).setVisible(false);
            }


            if(field.getType().getSuperclass() != null && field.getType().getSuperclass() == BaseEntity.class) {
                field.getName();
                crud.getCrudFormFactory().setFieldProvider(field.getName(), new BaseEntityCrudProvider(Registry.get().getBaseService().getAll(field.getType())) );
                crud.getGrid().getColumnByKey(field.getName()).setVisible(false);
            }

        }

        for(Field field : clazz.getSuperclass().getDeclaredFields()) {
            if(field.getName().equals("id")) {
                crud.getGrid().getColumnByKey("id").setVisible(false);
            }
            if(field.getType() == Boolean.class || field.getType() == boolean.class) {
                if(crud.getGrid().getColumnByKey(field.getName().replace("is","").toLowerCase()) != null) {
                    crud.getGrid().getColumnByKey(field.getName().replace("is","").toLowerCase()).setVisible(false);
                }
            }

        }
    }
}
