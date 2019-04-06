package cloud;

import cloud.util.HttpClientUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicrosoftserviceSimpleProviderUserApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void uploadFile(){
        File file = new File("D:\\logs\\log.2019-04-06.0.log");
        String url = "http://localhost:22222//Microsoft-User/common/uploadFile";
        Map<String, Object> param = new HashMap<>();
        param.put("file1", file);
        String response = HttpClientUtils.doPost(url, param);
        System.out.println(response);
    }

}
