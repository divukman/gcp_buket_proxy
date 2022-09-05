package tech.dimitar.gcp.bucketproxy.api.bucket;

import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class BucketApiImpl implements BucketApi {
    private final String bucketName;
    private final Storage storage;

    @Override
    public void readScan(UUID scanId, String imageName) {
        log.info("READING FROM STORAGE... scan id: " + scanId + ", image name: " + imageName);
    }
}
