package me.schnaidt.showcase.service;

import lombok.extern.slf4j.Slf4j;
import me.schnaidt.showcase.api.model.Pet;
import me.schnaidt.showcase.feign.FeignPetsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class PetClient {

  @Autowired
  private FeignPetsApi petsApi;

  @Scheduled(initialDelay = 3000, fixedRate = 5000)
  public void go() {
    ResponseEntity<List<Pet>> responseEntity = petsApi.listPets(5);
    Optional.ofNullable(responseEntity.getBody()).ifPresent(f -> log.info(f.toString()));
  }
}
