package ru.itis.services;

import ru.itis.models.Roll;
import ru.itis.repositories.RollHistRepository;

import java.util.Collections;
import java.util.List;

public class RollHistServiceImpl implements RollHistService {

    private RollHistRepository historyRepository;

    public RollHistServiceImpl(RollHistRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void updateRollHistory(Long userId, List<Roll> rolls) {
        List<Roll> currentHistory = historyRepository.findAllByUserId(userId);
        if (currentHistory.size() >= 5) {
            historyRepository.deleteLastByUserId(userId);
        }
        Roll newRoll = rolls.get(rolls.size()-1);
        newRoll.setUserId(userId);
        historyRepository.save(newRoll);
    }

    @Override
    public List<Roll> getRollHistory(Long userId) {
        List<Roll> rolls = historyRepository.findAllByUserId(userId);
        Collections.reverse(rolls);
        return rolls;
    }
}
