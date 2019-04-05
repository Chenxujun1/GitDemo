package cloud;

import cloud.util.HttpClientUtils;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@MapperScan(value = "cloud.dao")
@SpringBootApplication
public class MicrosoftserviceSimpleProviderUserApplication {

    /**
     * 集成pagehaper 插件
     * @return
     */
    /**
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return  pageHelper;
    }
    */
    /**
     * 注入HttpClient
     * @return
     */
    @Bean
    public HttpClientUtils httpClientUtils(){
        return new HttpClientUtils();
    }
    public static void main(String[] args) {
        SpringApplication.run(MicrosoftserviceSimpleProviderUserApplication.class, args);
    }

}
