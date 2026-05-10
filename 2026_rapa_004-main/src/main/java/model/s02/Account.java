package model.s02;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(
        name = "SAMPLE_SEQ_GENERATOR",
        sequenceName = "SAMPLE_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Account {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SAMPLE_SEQ_GENERATOR"
    )
    private Long id;

}
