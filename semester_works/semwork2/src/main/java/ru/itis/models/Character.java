package ru.itis.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Character {
    private Integer hp;
    public Integer gold;
    private Integer hitChance;
    private Integer damage;
    private List<Effect> permanentEffects;
}
