package tech.dimitar.gcp.bucketproxy.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.dimitar.gcp.bucketproxy.api.bucket.BucketApi;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class Gcp {
    private final BucketApi bucketApi;

    @GetMapping("/alive")
    public ResponseEntity<?> getAlive() {
        return new ResponseEntity<>("I am alive!", HttpStatus.OK);
    }


    @GetMapping(value="/scans/{id}/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getScanImage(@PathVariable String id, @PathVariable String image) {
        final byte[] imageBytes = bucketApi.readScan(UUID.fromString(id), image);
        return new ResponseEntity<>(imageBytes, HttpStatus.OK);
    }

    @GetMapping(value="/scans/stream/{id}/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getScanImageStream(@PathVariable String id, @PathVariable String image, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        final InputStream imageInputStream = bucketApi.readScanStream(UUID.fromString(id), image);
        StreamUtils.copy(imageInputStream, response.getOutputStream());
    }

}
