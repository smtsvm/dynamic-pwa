package works.ss.dynamic.pwa.backend.entity;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class BaseEntity{

    @Id
    @NotNull
    private String id;
    @Size(min = 2, message = "Product name must have at least two characters")
    private String name;
    private String version;
    private boolean isLatest;
    private List<String> childEntity;

    public BaseEntity() {
    }

    public BaseEntity(String name, String version, boolean isLatest, List<String> childEntity) {
        this.name = name;
        this.version = version;
        this.isLatest = isLatest;
        this.childEntity = childEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isLatest() {
        return isLatest;
    }

    public void setLatest(boolean latest) {
        isLatest = latest;
    }

    public List<String> getChildEntity() {
        return childEntity;
    }

    public void setChildEntity(List<String> childEntity) {
        this.childEntity = childEntity;
    }

    public boolean isNewEntity() {
        return StringUtils.isBlank(id);
    }
}
