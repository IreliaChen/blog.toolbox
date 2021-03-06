package top.crossoverjie.nows.nows.util;

import okhttp3.*;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class DownloadPicTest {

    @Test
    public void download() throws Exception {

        String path = "/Users/chenjie/Documents/blog-img/006tKfTcly1fr3a3xwslqj319g10cthl.jpg";

        DownloadUploadPic.download("https://ws4.sinaimg.cn/large/006tKfTcly1fr3a3xwslqj319g10cthl.jpg", path);

        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), new File(path));
        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("smfile", "image", fileBody)

                .build();


        Request request = new Request.Builder()
                .url("https://sm.ms/api/upload")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36")
                .post(requestBody)
                .build();

        Response response = httpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }


    @Test
    public void str(){
        String path = "https://i.loli.net/2019/05/05/5ccef1ffd774f.jpg";

        int index = path.lastIndexOf("/");
        path = path.substring(index +1) ;
        System.out.println(path);
    }

    @Test
    public void str2(){
        String path = "/Users/chenjie/Documents/Hexo/source/_posts/distributed/distributed-discovery-zk.md";

        int index = path.lastIndexOf(System.getProperty("file.separator"));
        path = path.substring(index +1) ;
        System.out.println(path);
    }
}