package xyz.maojun.test;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import xyz.maojun.common.util.FastDFSClient;

import java.awt.*;
import java.io.IOException;

public class fastDFSTest {
    @Test
    public void testFastDFS() throws IOException, MyException {
        ClientGlobal.init("E:\\egouMall\\manager-web\\src\\test\\resources\\client.conf");
        TrackerClient client = new TrackerClient();
        TrackerServer server = client.getConnection();
        StorageServer storageServer =null;
        StorageClient storageClient =new StorageClient(server,storageServer);
        String[] jpgs = storageClient.upload_file("E:\\2.png", "png", null);
        for (String jpg : jpgs) {
            System.out.println(jpg);
        }
    }
   @Test
    public void testFastDFSUtil() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("E:\\egouMall\\manager-web\\src\\test\\resources\\client.conf");
        String s = fastDFSClient.uploadFile("E:\\2.png");
        System.out.println(s);
        String test = "test";
       System.out.println(test.lastIndexOf("s"));
       System.out.println(test.substring(2));

   }
}
