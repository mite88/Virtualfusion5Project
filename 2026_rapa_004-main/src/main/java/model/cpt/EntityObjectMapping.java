package model.cpt;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EntityObjectMapping {

    @Id
    private String id;

    private String name;

    protected EntityObjectMapping() {}

    public EntityObjectMapping(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
