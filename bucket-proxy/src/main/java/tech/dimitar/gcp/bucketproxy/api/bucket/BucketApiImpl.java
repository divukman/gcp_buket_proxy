package tech.dimitar.gcp.bucketproxy.api.bucket;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.channels.Channels;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class BucketApiImpl implements BucketApi {
    private final String bucketName;
    private final Storage storage;

    @Override
    public byte[] readScan(UUID scanId, String imageName) {
        byte[] result = new byte[0];
        log.info("READING FROM STORAGE... scan id: " + scanId + ", image name: " + imageName);
        final Bucket scansBucket = storage.get(bucketName);
        log.info("File path: " + scanId.toString() + "/scan/" + imageName);
        Blob scan = scansBucket.get(scanId.toString() + "/scan/" + imageName);
        if (scan != null) {
            log.info("Scan file found");
            byte[] bytes = storage.readAllBytes(scan.getBlobId());
            log.info("READ: " + bytes.length + " bytes...");
            result = bytes;
        } else {
            log.error("Scan file not found!");
        }

        return result;
    }

    @Override
    public InputStream readScanStream(UUID scanId, String imageName) {
        ReadChannel reader = storage.reader(BlobId.of(bucketName, scanId.toString() + "/scan/" + imageName));
        return Channels.newInputStream(reader);
    }
}
