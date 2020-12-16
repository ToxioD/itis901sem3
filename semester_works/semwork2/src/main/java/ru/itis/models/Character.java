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
    public Integer maxHp;
    public Integer hp;
    public Integer gold;
    public Integer hitChance;
    public Integer damage;
    public List<Effect> permanentEffects;
}
