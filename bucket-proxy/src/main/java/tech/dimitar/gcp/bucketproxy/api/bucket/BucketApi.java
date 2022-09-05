package tech.dimitar.gcp.bucketproxy.api.bucket;

import java.io.InputStream;
import java.util.UUID;

public interface BucketApi {
    byte[] readScan(UUID scanId, String imageName);

    InputStream readScanStream(UUID scanId, String imageName);
}
