package com.example.HeroesCRUD.repository;

import org.assertj.core.api.Assertions;
import com.example.HeroesCRUD.model.Hero;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HeroRepositoryTest{

    @Autowired
    private HeroRepository heroRepo;

    /**
     * JUnit Test for save Hero
     */
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){

        Hero hero = Hero.builder()
                .name("Thor")
                .superPower("Strength, flies")
                .build();

        heroRepo.save(hero);

        Assertions.assertThat(hero.getId()).isGreaterThan(0);
    }

    /**
     * JUnit Test for getHeroById
     */
    @Test
    @Order(2)
    public void getHeroByIdTest(){

        Hero hero = heroRepo.findById(1L).get();

        Assertions.assertThat(hero.getId()).isEqualTo(1L);
    }

    /**
     * JUnit Test for getAllHeroes
     */
    @Test
    @Order(3)
    public void getAllHeroesTest(){

        List<Hero> heroList = heroRepo.findAll();

        Assertions.assertThat(heroList.size()).isGreaterThan(0);
    }

    /**
     * JUnit Test for updateHeroById
     */
    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateHeroByIdTest(){

        Hero hero = heroRepo.findById(1L).get();
        hero.setName("captain america");
        Hero heroUpdated =  heroRepo.save(hero);

        Assertions.assertThat(heroUpdated.getName()).isEqualTo("captain america");
    }

    /**
     * JUnit Test for deleteHeroById
     */
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteHeroByIdTest(){

        Optional<Hero> hero = heroRepo.findById(1L);

        heroRepo.delete(hero.get());

        Hero hero1 = null;

        Optional<Hero> optionalhero = heroRepo.findByName("Thor");

        if(optionalhero.isPresent()){
            hero1 = optionalhero.get();
        }

        Assertions.assertThat(hero1).isNull();
    }

}
