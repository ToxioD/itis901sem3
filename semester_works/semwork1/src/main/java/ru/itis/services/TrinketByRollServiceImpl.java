package ru.itis.services;

import ru.itis.models.Roll;
import ru.itis.models.Trinket;
import ru.itis.repositories.CrudRepository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TrinketByRollServiceImpl implements TrinketByRollService {

    private CrudRepository<Trinket> trinketRepository;

    public TrinketByRollServiceImpl(CrudRepository<Trinket> trinketRepository) {
        this.trinketRepository = trinketRepository;
    }

    @Override
    public List<Trinket> getTrinkets(List<Roll> rolls) {
        List<Long> ids = new LinkedList<>();
        for (Roll roll : rolls) {
            ids.add(roll.getResult());
        }
        List<Trinket> trinkets = trinketRepository.findAllByIds(ids);
        return trinkets;
    }

    @Override
    public List<Trinket> getShuffledTrinkets(List<Roll> rolls) {
        List<Trinket> trinkets = getTrinkets(rolls);
        Collections.shuffle(trinkets);
        return trinkets;
    }


}
