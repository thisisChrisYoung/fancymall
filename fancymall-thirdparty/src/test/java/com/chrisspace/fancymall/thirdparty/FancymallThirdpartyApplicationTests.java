package com.chrisspace.fancymall.thirdparty;

import com.aliyun.oss.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class FancymallThirdpartyApplicationTests {



    @Test
    void contextLoads() {
    }

    @Test
    public void testUpload() throws FileNotFoundException{
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tDR4QQQ3uApCreVCRFR";
        String accessKeySecret = "C9Kff1vjFamRCd7vpey9N0VnEhefNe";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "fancymall-files";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "coverPic.jpeg";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        String filePath= "/Users/chris/Downloads/coverPic.jpeg";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = new FileInputStream(filePath);
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
            );}
        finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }


//        FileInputStream inputStream = new FileInputStream("/Users/chris/Downloads/coverPic.jpeg");
//        ossClient.putObject("fancymall-files","coverPic.jpeg",inputStream);
//
//        ossClient.shutdown();
//
//        System.out.println("UPLOAD OK...");


    }

}
