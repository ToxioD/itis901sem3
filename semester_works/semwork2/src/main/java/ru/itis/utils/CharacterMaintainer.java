package ru.itis.utils;

import ru.itis.models.Character;
import ru.itis.models.Effect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CharacterMaintainer {

    private static final String DEFAULT_CHAR = "/csv/character.csv";

    private Character player;

    public CharacterMaintainer() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(CharacterMaintainer.class.getResourceAsStream(DEFAULT_CHAR)));
            String[] valuesAsStrings = br.readLine().split(",");
            List<Integer> values = Arrays.stream(valuesAsStrings).map(x -> Integer.parseInt(x))
                    .collect(Collectors.toList());
            player = Character.builder()
                    .maxHp(values.get(0))
                    .hp(values.get(0))
                    .gold(values.get(1))
                    .hitChance(values.get(2))
                    .damage(values.get(3))
                    .permanentEffects(new LinkedList<>())
                    .build();
        } catch (IOException e) {}
    }

    public Optional<Integer> getAttribute(String attrName) {
        try {
            return Optional.of((Integer)player.getClass().getDeclaredField(attrName).get(player));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void setAttribute(String attrName, Integer value) throws Exception{
        try {
            player.getClass().getDeclaredField(attrName).set(player, value);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    public void payForEffect(Effect effect) {
        player.setGold(player.getGold() - effect.getCost());
    }

    public void addEffect(Effect effect) {
        if (effect.getIsPermanent()) {
            player.permanentEffects.add(effect);
        } else {
            takeEffect(effect);
        }
    }

    private void takeEffect(Effect effect) {
        try {
            String target = effect.getEffectTarget();
            Integer newValue = getAttribute(target).orElse(1) + effect.getEffectModifier();
            setAttribute(target, newValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void takePermanentEffects() {
        List<Effect> permanentEffects = player.getPermanentEffects();
        permanentEffects.forEach(this::takeEffect);
    }
}
