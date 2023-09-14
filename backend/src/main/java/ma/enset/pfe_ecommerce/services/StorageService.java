package ma.enset.pfe_ecommerce.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class StorageService {
    private static final String bucketName="storagepfe";

    private final AmazonS3 s3Client;

    public String uploadFile(MultipartFile file) {
        log.info("Uploading file: {}", file.getOriginalFilename());
        final String fileExtension = UriUtils.extractFileExtension(file.getOriginalFilename());
        final String fileName = String.join(".", UUID.randomUUID() + "", fileExtension);
        final File fileObj = convertMultiPartFileToFile(file);
        final PutObjectRequest request=new PutObjectRequest(bucketName, fileName, fileObj);
        request.setCannedAcl(CannedAccessControlList.PublicRead);
        try {
            s3Client.putObject(request);
        } catch (AmazonServiceException e) {
            log.error(e.getMessage(),e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Failed to store file, try again or later");
        }

        fileObj.delete();
        log.info("File uploaded : {}" , fileName);
        return fileName;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Failed to store file, try again or later");
        }
        return convertedFile;
    }
}