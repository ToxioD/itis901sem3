package ru.itis.services;

import ru.itis.dto.RollForm;
import ru.itis.models.Roll;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RollServiceImpl implements RollService {

    @Override
    public List<Roll> getRollResult(RollForm rollForm) {
        Integer count = rollForm.getCount();
        Integer dice = rollForm.getDice();

        List<Roll> rolls = new LinkedList<>();
        Long finalResult = Long.valueOf(0);
        String dices = count + "d" + dice;

        for (int i = 0; i < count; i++) {
            Long rollResult = ThreadLocalRandom.current().nextLong(dice) + 1;
            String rollColor = "black";
            if (rollResult == Long.valueOf(dice)) rollColor = "green";
                else if (rollResult == 1) rollColor = "red";
            Roll newRoll = Roll.builder().dices(dices).result(rollResult).color(rollColor).build();
            rolls.add(newRoll);

            finalResult += rollResult;
        }
        
        String finalColor = "black";
        if (count == 1) finalColor = rolls.get(0).getColor();
        Roll finalRoll = Roll.builder().dices(dices).result(finalResult).color(finalColor).build();
        rolls.add(finalRoll);
        return rolls;
    }
}
