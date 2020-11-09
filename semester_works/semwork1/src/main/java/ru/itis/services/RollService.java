package ru.itis.services;

import ru.itis.models.Roll;
import java.util.List;

public interface RollService {
    List<Roll> getRollResult(Integer count, Integer dice);
}
