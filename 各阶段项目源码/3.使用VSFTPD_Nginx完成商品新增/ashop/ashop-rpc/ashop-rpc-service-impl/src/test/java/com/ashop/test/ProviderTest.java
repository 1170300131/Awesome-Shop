package com.ashop.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ProviderTest {
    public static void main(String[] args) {
        /**
         * 加载spring容器, 完成服务的发布
         */
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml",
                        "spring/applicationContext-service.xml",
                        "spring/applicationContext-tx.xml",
                        "spring/applicationContext-dubbo.xml");
        //启动容器
        ac.start();
        //阻塞程序的运行
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ac.stop();
    }
}
