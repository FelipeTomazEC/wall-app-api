package com.github.felipetomazec.adapters;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.github.felipetomazec.config.AWSConfig;
import com.github.felipetomazec.usecases.signup.dependencies.ImageUploader;
import com.github.felipetomazec.usecases.signup.exceptions.InvalidImageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;

@Component
public class S3ImageUploader implements ImageUploader {

    private final AWSConfig config;
    private final AmazonS3 client;

    @Autowired
    public S3ImageUploader(AWSConfig config) {
        this.config = config;
        this.client = createS3Client();
    }

    @Override
    public URL upload(String base64, String bucketName) throws InvalidImageException {
        try {
            var base64prefix = "data:image/jpeg;base64,";
            var path = Files.createTempFile(UUID.randomUUID().toString(), ".jpg");
            var bytes = Base64.getDecoder().decode(base64.replace(base64prefix, ""));
            Files.write(path, bytes);
            var key = path.getFileName().toString();
            var request = new PutObjectRequest(bucketName, key, path.toFile());
            this.client.putObject(request);

            return client.getUrl(bucketName, key);
        } catch (IllegalArgumentException e) {
            throw new InvalidImageException(base64);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private AmazonS3 createS3Client() {
        var region = Regions.US_EAST_1;
        var endpointConfig = new AwsClientBuilder.EndpointConfiguration(config.getS3().getServiceEndpoint(), region.name());
        var credentials = new BasicAWSCredentials(config.getAccessKey(), config.getSecretKey());
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpointConfig)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withPathStyleAccessEnabled(true)
                .build();
    }
}
