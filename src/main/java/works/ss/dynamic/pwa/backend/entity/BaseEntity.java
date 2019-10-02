package works.ss.dynamic.pwa.backend.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class BaseEntity{

    @Id
    @NotNull
    private String id;
    @Size(min = 2, message = "Product name must have at least two characters")
    private String name;
    private String version;


}
