package model.cpt;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class ColumnAndGeneration {

    @Id
    @Column(name = "products_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

}
