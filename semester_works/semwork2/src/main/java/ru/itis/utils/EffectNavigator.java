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
                buffs.add(Effect.builder()
                        .cost(Integer.parseInt(buff[0]))
                        .effectText(buff[1])
                        .effectTarget(buff[2])
                        .effectModifier(Integer.parseInt(buff[3]))
                        .isPermanent(Boolean.parseBoolean(buff[4]))
                        .imagePath("/img/"+buff[5]+".png").build());
                buffAsString = br.readLine();
            }

            BufferedReader br2 = new BufferedReader(
                    new InputStreamReader(EffectNavigator.class.getResourceAsStream(DEBUFFS)));
            debuffs = new ArrayList<Effect>();
            String debuffAsString = br2.readLine();
            while (debuffAsString != null) {
                String[] debuff = debuffAsString.split(",");
                debuffs.add(Effect.builder()
                        .cost(Integer.parseInt(debuff[0]))
                        .effectText(debuff[1])
                        .effectTarget(debuff[2])
                        .effectModifier(Integer.parseInt(debuff[3]))
                        .isPermanent(Boolean.parseBoolean(debuff[4]))
                        .imagePath("/img/" + debuff[5] + ".png").build());
                debuffAsString = br2.readLine();
            }
        } catch (IOException e) {}
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
