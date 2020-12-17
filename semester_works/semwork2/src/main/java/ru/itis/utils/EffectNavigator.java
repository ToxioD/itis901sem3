package ru.itis.utils;

import ru.itis.models.Effect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class EffectNavigator {

    private final static String BUFFS = "/csv/buffs.csv";
    private final static String DEBUFFS = "/csv/debuffs.csv";

    private List<Effect> buffs;
    private List<Effect> debuffs;

    public EffectNavigator() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(EffectNavigator.class.getResourceAsStream(BUFFS)));
            buffs = new ArrayList<Effect>();
            String buffAsString = br.readLine();
            while (buffAsString != null) {
                String[] buff = buffAsString.split(",");
                buffs.add(parseEffect(buff));
                buffAsString = br.readLine();
            }

            BufferedReader br2 = new BufferedReader(
                    new InputStreamReader(EffectNavigator.class.getResourceAsStream(DEBUFFS)));
            debuffs = new ArrayList<Effect>();
            String debuffAsString = br2.readLine();
            while (debuffAsString != null) {
                String[] debuff = debuffAsString.split(",");
                debuffs.add(parseEffect(debuff));
                debuffAsString = br2.readLine();
            }
        } catch (IOException e) {}
    }

    public Effect parseEffect(String[] effect) {
        return Effect.builder()
                .cost(Integer.parseInt(effect[0]))
                .effectText(effect[1])
                .effectTarget(effect[2])
                .effectModifier(Integer.parseInt(effect[3]))
                .isPermanent(Boolean.parseBoolean(effect[4]))
                .imagePath("/img/" + effect[5] + ".png").build();
    }

    public String encodeEffect(Effect effect) {
        return effect.toString();
    }

    public Effect getRandomBuff() {
        Collections.shuffle(buffs);
        return buffs.get(0);
    }

    public Effect getRandomDebuff() {
        Collections.shuffle(debuffs);
        return debuffs.get(0);
    }
}
