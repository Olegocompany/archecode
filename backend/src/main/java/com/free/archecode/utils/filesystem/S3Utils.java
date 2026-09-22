package com.free.archecode.utils.filesystem;

import java.util.HashMap;

public interface S3Utils {
    public void uploadFile(String filename, byte[] content, String bucket);
    public HashMap<String, Object> getFolder(String path, String bucket);
    public byte[] getFile(String path, String bucket);
    public boolean bucketExists(String bucketName);
    public void createBucket(String bucketName);
}
