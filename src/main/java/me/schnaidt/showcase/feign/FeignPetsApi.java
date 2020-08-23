package me.schnaidt.showcase.feign;

import me.schnaidt.showcase.api.PetsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pet-server")
public interface FeignPetsApi extends PetsApi {
}
