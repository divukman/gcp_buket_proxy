package tech.dimitar.gcp.bucketproxy.api.bucket;

import java.util.UUID;

public interface BucketApi {
    void readScan(UUID scanId, String imageName);
}
