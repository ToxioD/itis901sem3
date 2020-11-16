package ru.itis.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RollForm {
    @Min(value = 1)
    @Max(value = 1000)
    private int count;

    @Min(value = 1)
    @Max(value = 1000)
    private int dice;
}
