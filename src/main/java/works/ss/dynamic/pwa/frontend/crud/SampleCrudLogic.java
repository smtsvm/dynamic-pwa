package works.ss.dynamic.pwa.frontend.crud;

import com.vaadin.flow.component.UI;
import works.ss.dynamic.pwa.backend.Registry;
import works.ss.dynamic.pwa.backend.entity.BaseEntity;

import java.io.Serializable;

public class SampleCrudLogic implements Serializable {

    private SampleCrudView view;

    private Class clazz;

    public SampleCrudLogic(SampleCrudView simpleCrudView, Class clazz) {

        this.clazz = clazz;
        view = simpleCrudView;
    }

    public void init() {
        editEntity(null);
    }

    public void cancelBaseEntityAction() {
        setFragmentParameter("");
        view.clearSelection();
    }

    /**
     * Update the fragment without causing navigator to change view
     */
    private void setFragmentParameter(String baseEntityId) {
        String fragmentParameter;
        if (baseEntityId == null || baseEntityId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = baseEntityId;
        }

        UI.getCurrent().navigate(SampleCrudView.class, fragmentParameter);
    }

    public void enter(String baseEntityId) {
        if (baseEntityId != null && !baseEntityId.isEmpty()) {
            if (baseEntityId.equals("new")) {
                newEntiy();
            } else {
                // Ensure this is selected even if coming directly here from
                // login
                    BaseEntity baseEntity= findBaseEntity(baseEntityId);
                    view.selectRow(baseEntity);
            }
        } else {
            view.showForm(false);
        }
    }

    private BaseEntity findBaseEntity(String entityId) {
        return Registry.get().getBaseService().findEntity(clazz, entityId).get();
    }

    public void saveEntity(BaseEntity baseEntity) {
        boolean isNew = baseEntity.isNewEntity();
        view.clearSelection();
        view.updateEntity(baseEntity);
        setFragmentParameter("");

    }

    public void deleteEntity(BaseEntity baseEntity) {
        view.clearSelection();
        view.removeEntity(baseEntity);
        setFragmentParameter("");
    }

    public void editEntity(BaseEntity baseEntity) {
        if (baseEntity == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(baseEntity.getId() + "");
        }
        view.editEntity(baseEntity);
    }

    public void newEntiy() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editEntity(new BaseEntity());
    }

    public void rowSelected(BaseEntity baseEntity) {
        editEntity(baseEntity);
    }
}
