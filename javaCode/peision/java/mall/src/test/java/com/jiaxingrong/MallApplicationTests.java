package com.jiaxingrong;

import com.jiaxingrong.handler.IntArray2String;
import org.apache.ibatis.type.TypeHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class MallApplicationTests {


    @Test
    public void mytest() throws FileNotFoundException {
        /*String accessKeyId = "LTAI4FxcVFjPQLWPP2DWLhN4";
        String accessSecret = "USkVxf3RVA99tD17Ty3xCGN7Won77q";
        String bucket = "spring-project2";
        String endPoint = "oss-cn-beijing.aliyuncs.com";

        File file = new File("C:\\Users\\76671\\Pictures", "aa).jpg");

        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessSecret);
        FileInputStream fileInputStream = new FileInputStream(file);
        PutObjectResult putObjectResult = ossClient.putObject(bucket, "aa.jpg", fileInputStream);
        System.out.println(putObjectResult);*/

    }
}
