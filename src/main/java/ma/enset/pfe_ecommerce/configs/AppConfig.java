package ma.enset.pfe_ecommerce.configs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.annotation.Validated;

@Data
@ConfigurationProperties("app.config")
@Configuration
@EnableAsync
@Validated
public class AppConfig {

    @NotNull
    @Valid
    private AWS aws;

    @Data
    public static class AWS {
        @NotBlank
        private String region;
        @NotBlank
        private String accessKey;
        @NotBlank
        private String secretKey;

    }

}