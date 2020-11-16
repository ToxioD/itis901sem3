package ru.itis.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RollForm {
    @NotNull(message = "Number of dices should not be empty")
    @Min(value = 1, message = "Number of dices should not be less than {value}")
    @Max(value = 100, message = "Number of dices should not be more than {value}")
    private Integer count;

    @NotNull(message = "Number of sides should not be empty")
    @Min(value = 1, message = "Number of sides should not be less than {value}")
    @Max(value = 100, message = "Number of sides should not be more than {value}")
    private Integer dice;
}
