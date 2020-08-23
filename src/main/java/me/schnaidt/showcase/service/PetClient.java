package me.schnaidt.showcase.service;

import lombok.extern.slf4j.Slf4j;
import me.schnaidt.showcase.api.model.Pet;
import me.schnaidt.showcase.feign.FeignPetsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class PetClient {

  @Autowired
  private FeignPetsApi petsApi;

  @Scheduled(initialDelay = 5000, fixedRate = 5000)
  public void getAll() {
    ResponseEntity<List<Pet>> responseEntity = petsApi.listPets(5);
    Optional.ofNullable(responseEntity.getBody()).ifPresent(f -> log.info(f.toString()));
  }

  //@Scheduled(initialDelay = 3000, fixedRate = 3000)
  public void getSingleExisting() {
    ResponseEntity<Pet> responseEntity = petsApi.showPetById("3");
    Optional.ofNullable(responseEntity.getBody()).ifPresent(f -> log.info(f.toString()));
  }

  //@Scheduled(initialDelay = 4000, fixedRate = 4000)
  public void getNonExisting() {
    try {
      ResponseEntity<Pet> responseEntity = petsApi.showPetById("6");
      Optional.ofNullable(responseEntity.getBody()).ifPresent(f -> log.info(f.toString()));
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  @Scheduled(initialDelay = 2000, fixedRate = 4000)
  public void getConfigValue() {
    RestTemplate restTemplate = new RestTemplate();
    String value = restTemplate.getForObject("http://localhost:8080/greeting?name=Jochen", String.class);
    log.info(value);
  }
}
