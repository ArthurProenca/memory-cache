package dev.friday.com.memorycache.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "zipCodeFeignClient", url = "${viacep-api.uri}")
public interface ZipCodeFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{zipCode}/json/", headers = "Accept=application/json")
    Object getZipCodeInfo(@PathVariable String zipCode);

}
