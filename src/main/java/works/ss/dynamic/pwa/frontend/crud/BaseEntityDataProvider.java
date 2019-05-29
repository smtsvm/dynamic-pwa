package works.ss.dynamic.pwa.frontend.crud;

import com.vaadin.flow.data.provider.ListDataProvider;
import works.ss.dynamic.pwa.backend.Registry;
import works.ss.dynamic.pwa.backend.entity.BaseEntity;

import java.util.Locale;
import java.util.Objects;

public class BaseEntityDataProvider extends ListDataProvider<BaseEntity> {


    /** Text filter that can be changed separately. */
    private String filterText = "";

    public BaseEntityDataProvider(Class clazz) {
        super(Registry.get().getBaseService().getAll(clazz));
    }

    public void save(BaseEntity baseEntity) {
        boolean newEntity = baseEntity.isNewEntity();

        Registry.get().getBaseService().saveEntity(baseEntity);
        if (newEntity) {
            refreshAll();
        } else {
            refreshItem(baseEntity);
        }
    }


    public void delete(BaseEntity baseEntity) {
        Registry.get().getBaseService().delete(baseEntity);
        refreshAll();
    }


    public void setFilter(String filterText) {
        Objects.requireNonNull(filterText, "Filter text cannot be null.");
        if (Objects.equals(this.filterText, filterText.trim())) {
            return;
        }
        this.filterText = filterText.trim();

        setFilter(baseEntity -> passesFilter(baseEntity.getName(), filterText)
                || passesFilter(baseEntity.getVersion(), filterText));
    }



    private boolean passesFilter(Object object, String filterText) {
        return object != null && object.toString().contains(filterText);
    }
}
