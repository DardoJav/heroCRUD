package com.example.HeroesCRUD.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.HeroesCRUD.HeroService.HeroService;
import com.example.HeroesCRUD.model.Hero;
import com.example.HeroesCRUD.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hero")
public class HeroController {

    @Autowired
    private HeroService heroService;

    /**
     * This method will add a new Hero using Post
     * @body contains the info of the hero to be saved
     * @return the Hero object created
     * @throws "INTERNAL_SERVER_ERROR" if anything happens
     */
    @PostMapping("/add")
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        try{
            Hero heroObj = heroService.createHero(hero);
            return new ResponseEntity<>(heroObj, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method will get All Heroes
     * @return the list of heroes or NO_CONTENT
     * @throws "INTERNAL_SERVER_ERROR" if anything happens
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Hero>> getAllHeroes(){
        try{
            List<Hero> heroList = heroService.getAllHeroes();
            if(heroList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(heroList,HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method will get a specific Hero by id
     * @pathVariable id of the Hero to find
     * @return a hero object or NOT_FOUND
     * @throws "INTERNAL_SERVER_ERROR" if anything happens
     */
    @GetMapping("/getById/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable Long id){
        try{
            Hero heroData = heroService.getHeroById(id);

            if (heroData != null) return new ResponseEntity<>(heroData,HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method will get All Heroes that match or contain the name given
     * @param name to use for filtering
     * @return the list of heroes or NO_CONTENT
     * @throws "INTERNAL_SERVER_ERROR" if anything happens
     */
    @GetMapping("/getAllByName")
    public ResponseEntity<List<Hero>> getHeroById(@PathParam("name") String name){
        try{
            List<Hero> heroList = heroService.getAllHeroesMatchingName(name);
            if(heroList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(heroList,HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method will update a specific hero by id
     * @body with the info to update
     * @pathVariable id of the Hero to update
     * @return the hero updated or NO_CONTENT
     * @throws "INTERNAL_SERVER_ERROR" if anything happens
     */
    @PutMapping("/updateById/{id}")
    public ResponseEntity<Hero> updateHeroById(@PathVariable Long id, @RequestBody Hero newHeroData){
        try{
            Hero heroData = heroService.updateHero(id,newHeroData);

            if (heroData != null) {
                return new ResponseEntity<>(heroData, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method will delete a specific hero by id
     * @pathVariable id of the Hero to delete
     * @return httpStatus OK
     * @throws "INTERNAL_SERVER_ERROR" if anything happens
     */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deleteHeroById(@PathVariable Long id){
        try{
            heroService.deleteHero(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
