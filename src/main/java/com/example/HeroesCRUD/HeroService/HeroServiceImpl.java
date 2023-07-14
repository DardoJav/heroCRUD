package com.example.HeroesCRUD.HeroService;

import com.example.HeroesCRUD.model.Hero;
import com.example.HeroesCRUD.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HeroServiceImpl implements HeroService{

    @Autowired
    private HeroRepository heroRepo;

    @Override
    public Hero createHero(Hero hero){
        return heroRepo.save(hero);
    }

    @Override
    public List<Hero> getAllHeroes(){
        List<Hero> heroList = new ArrayList<>();
        heroRepo.findAll().forEach(heroList::add);
        return heroList;
    }

    @Override
    public Hero getHeroById(Long id){
        Optional<Hero> heroData = heroRepo.findById(id);

        if (heroData.isPresent()) return heroData.get();
        return null;
    }

    @Override
    public List<Hero> getAllHeroesMatchingName(String name){
        List<Hero> heroTempList = new ArrayList<>();
        List<Hero> heroList = new ArrayList<>();
        heroRepo.findAll().forEach(heroTempList::add);
        for (Hero hero : heroTempList){
            if(hero.getName().toLowerCase().contains(name)){
                heroList.add(hero);
            }
        }
        return heroList;
    }

    @Override
    public Hero updateHero(Long heroId, Hero newHeroData){
        Optional<Hero> oldHeroData = heroRepo.findById(heroId);

        if (oldHeroData.isPresent()){
            Hero updatedHeroData = oldHeroData.get();
            updatedHeroData.setName(newHeroData.getName());
            updatedHeroData.setSuperPower(newHeroData.getSuperPower());

            Hero heroObj = heroRepo.save(updatedHeroData);
            return heroObj;
        }
        return null;
    }

    @Override
    public void deleteHero(Long id){
        heroRepo.deleteById(id);
    }

}
