package ru.itis.services;

import ru.itis.dto.RollForm;
import ru.itis.models.Roll;
import java.util.List;

public interface RollService {
    List<Roll> getRollResult(RollForm rollForm);
}
