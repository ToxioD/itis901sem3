package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Effect {
    public Integer cost;
    public String effectText;
    public String effectTarget;
    public Integer effectModifier;
    public Boolean isPermanent;
    public String imageName;
}
