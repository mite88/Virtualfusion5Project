package io.eddie.productsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ProductRequest(

        @NotBlank                       // Validation
        @Length(min = 10, max= 50)      // Validation
        @Schema(
                description = "추가될 아이템 명",
                example = "백엔드 기술강사님이 사용하는 롱소드",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 10,
                maxLength = 50
        )
        String name,

        @Schema(
                description = "추가될 아이템 설명",
                example = "크고 아름다운 검입니다.",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank
        String description,

        @Schema(
                description = "추가될 아이템 값",
                example = "1000",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minimum = "1000"
        )
        @NotNull
        @Min(value = 1000L)
        Long price

) {
}
