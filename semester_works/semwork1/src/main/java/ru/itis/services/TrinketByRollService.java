package ru.itis.services;

import ru.itis.models.Roll;
import ru.itis.models.Trinket;

import java.util.List;

public interface TrinketByRollService {
    List<Trinket> getTrinkets(List<Roll> rolls);
    List<Trinket> getShuffledTrinkets(List<Roll> rolls);
}
