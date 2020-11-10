package ru.itis.services;

import ru.itis.models.Roll;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RollUniqueServiceImpl implements RollService {
    @Override
    public List<Roll> getRollResult(Integer count, Integer dice) {
        List<Roll> rolls = new LinkedList<>();
        List<Long> sides = new LinkedList<>();
        String dices = count + "d" + dice;

        for (long i = 1; i <= dice; i++) {
            sides.add(Long.valueOf(i));
        }

        Collections.shuffle(sides);

        for (int i = 0; i < count; i++) {
            Long rollResult = sides.get(i);
            String rollColor = "black";
            if (rollResult == Long.valueOf(dice)) rollColor = "green";
            else if (rollResult == 1) rollColor = "red";
            Roll newRoll = Roll.builder().dices(dices).result(rollResult).color(rollColor).build();
            rolls.add(newRoll);
        }

        return rolls;
    }
}
