package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Effect {
    private Integer cost;
    private String effectText;
    private String effectTarget;
    private Integer effectModifier;
    private Boolean isPermanent;
    private String imagePath;

    @Override
    public String toString() {
        return "" + cost +
                "," + effectText +
                "," + effectTarget +
                "," + effectModifier +
                "," + isPermanent +
                "," + imagePath;
    }
}
