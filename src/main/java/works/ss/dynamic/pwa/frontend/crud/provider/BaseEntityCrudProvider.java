package works.ss.dynamic.pwa.frontend.crud.provider;

import com.vaadin.flow.component.combobox.ComboBox;
import org.vaadin.crudui.form.impl.field.provider.AbstractListingProvider;
import works.ss.dynamic.pwa.backend.entity.BaseEntity;

import java.util.Collection;

public class BaseEntityCrudProvider extends AbstractListingProvider<ComboBox<BaseEntity>, BaseEntity> {


    public BaseEntityCrudProvider(Collection<BaseEntity> items) {
        super(items);
    }

    @Override
    protected ComboBox<BaseEntity> buildAbstractListing() {
        ComboBox<BaseEntity> comboBox = new ComboBox<>();
        comboBox.setItemLabelGenerator(e-> e.getName());
        return comboBox;
    }
}
