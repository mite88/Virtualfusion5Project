package model.cpt;

import jakarta.persistence.*;

@Entity
@TableGenerator(
        name = "SAMPLE_SEQ_TABLE_GENERATOR",
        table = "SAMPLE_SEQUENCE_CHEK",
        pkColumnName = "SAMPLE_ACCOUNT_SEQ",
        allocationSize = 1
)
public class TableGenerationMapping {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "SAMPLE_SEQ_TABLE_GENERATOR"
    )
    private Long id;


}
