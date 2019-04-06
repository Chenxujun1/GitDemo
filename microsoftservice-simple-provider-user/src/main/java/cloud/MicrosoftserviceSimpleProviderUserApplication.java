package cloud;

import cloud.util.HttpClientUtils;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.util.Properties;

@MapperScan(value = "cloud.dao")
@SpringBootApplication
public class MicrosoftserviceSimpleProviderUserApplication {

    //上传文件时缓冲区位置
    @Value("${fileupload.bufferDir}")
    private String uploadFileBufferDir;
    /**
     * 注入HttpClient
     * @return
     */
    @Bean
    public HttpClientUtils httpClientUtils(){
        return new HttpClientUtils();
    }

    /**
     * 设置文件上传
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(10*1024*1024L));
        factory.setMaxRequestSize(DataSize.ofBytes(100*1024*1024L));
        File bufferDir = new File(uploadFileBufferDir);
        if(!bufferDir.exists()){
            bufferDir.mkdirs();
        }
        factory.setLocation(uploadFileBufferDir);
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(MicrosoftserviceSimpleProviderUserApplication.class, args);
    }

}
