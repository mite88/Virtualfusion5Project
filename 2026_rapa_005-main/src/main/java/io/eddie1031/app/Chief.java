package io.eddie1031.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Chief {

    private CuisineAbility ability;

    public Chief(@Qualifier("koreanCuisineAbility") CuisineAbility ability) {
        this.ability = ability;
    }

    public void cook(List<String> ingredients) {
        ability.apply(ingredients);
    }

    public int checkExp() {
        return ability.getExp();
    }


}
