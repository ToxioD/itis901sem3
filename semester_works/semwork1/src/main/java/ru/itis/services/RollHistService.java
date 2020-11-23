package ru.itis.services;

import ru.itis.models.Roll;

import java.util.List;

public interface RollHistService {
    void updateRollHistory(Long userId, List<Roll> rolls);
    List<Roll> getRollHistory(Long userId);
}
