package com.chat.aj.unote.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class R2Service {

    private final S3Client r2Client;

    @Value("${cloudflare.r2.bucket}")
    private String bucket;

    @Value("${cloudflare.r2.account-id}")
    private String accountId;

    public R2Service(S3Client r2Client) {
        this.r2Client = r2Client;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();

        r2Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .contentType(file.getContentType())
                        .build(),
                RequestBody.fromBytes(file.getBytes())
        );

        // Returns the public URL
        return "https://" + accountId + ".r2.cloudflarestorage.com/" + bucket + "/" + key;
    }

    public void deleteFile(String fileUrl) {
        String key = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        r2Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build());
    }
}
