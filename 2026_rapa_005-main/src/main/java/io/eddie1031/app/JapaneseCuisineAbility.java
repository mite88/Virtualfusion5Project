package io.eddie1031.app;

import org.springframework.stereotype.Component;
import io.eddie1031.repository.AbilityRepository;

import java.util.List;

@Component
public class JapaneseCuisineAbility implements CuisineAbility {

    private final AbilityRepository repository;

    public JapaneseCuisineAbility(AbilityRepository repository) {
        this.repository = repository;
    }

    private void increaseExp() {
        String abilityName = this.getClass().getSimpleName();
        int currentExp = repository.getCurrentExp(abilityName);
        repository.applyExp(abilityName, ++currentExp);
    }

    @Override
    public void apply(List<String> ingredients) {

        this.increaseExp();

        String usedIngredients = String.join(" ", ingredients);

        System.out.println("%s을/를 볶아서 일본 음식을 요리했습니다!".formatted(usedIngredients));

    }

    @Override
    public int getExp() {
        return repository.getCurrentExp(this.getClass().getSimpleName());
    }


}
