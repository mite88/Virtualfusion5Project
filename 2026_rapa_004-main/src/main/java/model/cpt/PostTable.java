package model.cpt;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "article")
public class PostTable {

    @Id
    private Long id;

    private String title;
    private String contents;

}
