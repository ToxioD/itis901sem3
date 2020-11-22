package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DndClass extends DndEntity {
    private Integer hitDice;
    private boolean isSpellcasting;

    @Builder
    public DndClass(Long id, String name, String description,
                   Integer hitDice, boolean isSpellcasting) {
        super(id, name, description);
        this.hitDice = hitDice;
        this.isSpellcasting = isSpellcasting;
    }
}
