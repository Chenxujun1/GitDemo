package cloud;

import cloud.util.HttpClientUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan(value = "cloud.dao")
@SpringBootApplication
public class MicrosoftserviceSimpleProviderUserApplication {

    @Bean
    public HttpClientUtils httpClientUtils(){
        return new HttpClientUtils();
    }
    public static void main(String[] args) {
        SpringApplication.run(MicrosoftserviceSimpleProviderUserApplication.class, args);
    }

}
