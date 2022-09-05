package tech.dimitar.gcp.bucketproxy.api.bucket;

import java.util.UUID;

public interface BucketApi {
    byte[] readScan(UUID scanId, String imageName);
}
