package io.eddie1031.app;

import org.springframework.stereotype.Component;
import io.eddie1031.repository.AbilityRepository;

import java.util.List;

@Component
public class KoreanCuisineAbility implements CuisineAbility {

    private final AbilityRepository repository;

    public KoreanCuisineAbility(AbilityRepository repository) {
        this.repository = repository;
    }

    private void increaseExp() {
        String abilityName = this.getClass().getSimpleName();
        int currentExp = repository.getCurrentExp(abilityName);
        repository.applyExp(abilityName, ++currentExp);
    }

    @Override
    public void apply(List<String> ingredient) {

        this.increaseExp();

        String usedIngredients = String.join(" ", ingredient);
        System.out.println("%s를 끓여서 한국 음식을 요리했습니다.".formatted(usedIngredients));

    }

    @Override
    public int getExp() {
        return repository.getCurrentExp(this.getClass().getSimpleName());
    }

}
