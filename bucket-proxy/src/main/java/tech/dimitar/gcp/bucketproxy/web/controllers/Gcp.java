package tech.dimitar.gcp.bucketproxy.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Gcp {



    @GetMapping("/alive")
    public ResponseEntity<?> getAlive() {
        return new ResponseEntity<>("I am alive!", HttpStatus.OK);
    }

}
