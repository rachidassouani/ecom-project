package ma.enset.pfe_ecommerce.configs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ayoub ANBARA
 */
@Configuration
public class Beans {

    @Bean
    public AmazonS3 amazonS3(AppConfig appConfig) {
        final AppConfig.AWS awsConfig = appConfig.getAws();
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                awsConfig.getAccessKey(),
                awsConfig.getSecretKey()
        );
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(awsConfig.getRegion())
                .build();
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}