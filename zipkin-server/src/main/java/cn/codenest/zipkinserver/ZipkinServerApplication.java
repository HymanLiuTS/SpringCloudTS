package cn.codenest.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//todo 官网已经不再推荐自己编译ZIPSERVER，相反，建议直接下载jar
// 若非要自己搭建，可以参考https://blog.csdn.net/setlilei/article/details/102961605
//@EnableZipkinServer
@SpringBootApplication
public class ZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }

}
