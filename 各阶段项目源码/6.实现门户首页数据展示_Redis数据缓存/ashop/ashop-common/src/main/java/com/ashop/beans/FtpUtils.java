package com.ashop.beans;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.InputStream;

public class FtpUtils {
    /**
     * 完成图片上传, 将图片上传到图片服务器.
     * @param args
     */
    public static void main(String[] args) {
        String hostName = "192.168.117.131";
        int port = 21;
        String username = "ftpuser";
        String password = "123";
        String pathname = "/home/ftpuser/jd";
        String remote = "demo3.jpg";
        InputStream local = null;
        uploadFile(hostName, port, username, password, pathname, remote, local);
    }

    public static boolean uploadFile(String hostName, int port, String username,
                                  String password, String pathname, String remote, InputStream local) {
        boolean flag = false;
        FTPClient client = null;
        try {
            //创建FtpClient对象
            client = new FTPClient();
            //建立和FTP服务器的链接
            client.connect(hostName, port);
            //登陆ftp服务器
            client.login(username, password);
            //设置上传的文件的类型
            client.setFileType(FTP.BINARY_FILE_TYPE);

            //切换工作目录, 文件上传后保存到哪个目录
            if(!client.changeWorkingDirectory(pathname)){
                if(client.makeDirectory(pathname)) {//如果没有这个目录则创建该目录
                    client.changeWorkingDirectory(pathname);
                }
            }
//            local = new FileInputStream("C:/java/3.jpg");
            //实现文件上传, remote为上传后的文件名,
            client.storeFile(remote, local);
//            System.out.println(client.getReplyString()); //调试错误信息
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != local){
                    local.close();
                }
                client.logout();//退出
                client.disconnect();//断开连接
                flag = true;
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return flag;
    }
}
