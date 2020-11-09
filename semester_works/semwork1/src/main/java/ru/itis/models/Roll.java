package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Roll {
    private Long id;
    private Long userId;
    private String dices;
    private Long result;
    private String color;
}
