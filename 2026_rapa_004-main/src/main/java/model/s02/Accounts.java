package model.s02;

import jakarta.persistence.*;

@Entity
@TableGenerator(
        name = "SAMPLE_SEQ_TABLE_GENERATOR",
        table = "SAMPLE_SEQUENCE_TABLE",
        pkColumnName = "SAMPLE_ACCOUNT_SEQ",
        allocationSize = 1
)
public class Accounts {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "SAMPLE_SEQ_TABLE_GENERATOR"
    )
    private Long id;

    private String name;

}
