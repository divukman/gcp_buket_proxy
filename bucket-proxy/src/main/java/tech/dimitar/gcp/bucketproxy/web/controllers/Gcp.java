package tech.dimitar.gcp.bucketproxy.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.dimitar.gcp.bucketproxy.api.bucket.BucketApi;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class Gcp {
    private final BucketApi bucketApi;

    @GetMapping("/alive")
    public ResponseEntity<?> getAlive() {
        return new ResponseEntity<>("I am alive!", HttpStatus.OK);
    }


    //curl localhost:8080/scans/ecf9925b-f03f-44b6-844a-671db1eef83f/lf
    @GetMapping(value="/scans/{id}/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getScanImage(@PathVariable String id, @PathVariable String image) {
        final byte[] imageBytes = bucketApi.readScan(UUID.fromString(id), image);
        return new ResponseEntity<>(imageBytes, HttpStatus.OK);
    }

}
