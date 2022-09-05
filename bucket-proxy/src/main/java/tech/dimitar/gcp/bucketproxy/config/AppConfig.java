package tech.dimitar.gcp.bucketproxy.config;

import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.dimitar.gcp.bucketproxy.api.bucket.BucketApi;
import tech.dimitar.gcp.bucketproxy.api.bucket.BucketApiImpl;

@Configuration
public class AppConfig {
    @Value("${scans.bucket.name}")
    private String scansBucketName;

    @Bean
    public BucketApi bucketApi() {
        return new BucketApiImpl(scansBucketName, StorageOptions.getDefaultInstance().getService());
    }
}
