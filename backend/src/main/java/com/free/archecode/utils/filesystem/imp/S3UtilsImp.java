package com.free.archecode.utils.filesystem.imp;

import java.io.IOException;
import java.util.HashMap;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Component;

import com.free.archecode.utils.filesystem.S3Utils;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component 
public class S3UtilsImp implements S3Utils {

    private final S3Client s3Client;

    public S3UtilsImp(S3Client s3Client) {
        this.s3Client = s3Client;
    }
    
    public void uploadFile(String filename, byte[] content, String bucket) {
        s3Client.putObject(PutObjectRequest.builder().bucket(bucket).key(filename).build(), RequestBody.fromBytes(content));
    }
    public HashMap<String, Object> getFolder(String path, String bucket){
        // yeah
        HashMap<String, Object> tree = new HashMap<>();
        return tree;
    }
    public byte[] getFile(String path, String bucket) {
        try (ResponseInputStream<GetObjectResponse> response = s3Client.getObject(
            GetObjectRequest.builder().bucket(bucket).key(path).build()
            )
        ) {
            return response.readAllBytes();
        } catch(IOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public boolean bucketExists(String bucketName) {
        // yeah
        return true;
    }
    public void createBucket(String bucketName) {
        // yeah
    }
    
}
