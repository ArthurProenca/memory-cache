package dev.friday.com.memorycache.controller;

import dev.friday.com.memorycache.service.ZipCodeRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ZipCodeRestController {

    private final ZipCodeRestService zipCodeRestService;

    @RequestMapping(method = RequestMethod.GET, value = "/zipcodes/info")
    public ResponseEntity<?> getZipCodeInfo(@RequestParam("zipCode") String zipCode) {
        return ResponseEntity.ok(zipCodeRestService.getZipCodeInfo(zipCode));
    }
}
