package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DndRace extends DndEntity{
    private String ability;
    private String size;
    private Integer speed;
    private boolean hasDarkvision;

    @Builder
    public DndRace(Long id, String name, String description,
                   String ability, String size, Integer speed, boolean hasDarkvision) {
        super(id, name, description);
        this.ability = ability;
        this.size = size;
        this.speed = speed;
        this.hasDarkvision = hasDarkvision;
    }
}