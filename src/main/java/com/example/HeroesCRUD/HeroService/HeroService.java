package com.example.HeroesCRUD.HeroService;

import com.example.HeroesCRUD.model.Hero;

import java.util.List;

public interface HeroService {

    Hero createHero(Hero hero);

    List<Hero> getAllHeroes();

    Hero getHeroById(Long heroId);

    List<Hero> getAllHeroesMatchingName(String heroName);

    Hero updateHero(Long heroId,Hero hero);

    void deleteHero(Long heroId);
}
