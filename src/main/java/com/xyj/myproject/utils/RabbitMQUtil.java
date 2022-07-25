package com.xyj.myproject.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtil {

    private static Channel channel = null;

    private static Connection connection = null;

    public static Channel getChannel(){
        //定义连接池
        ConnectionFactory factory = new ConnectionFactory();
        //设置主机地址
        factory.setHost("192.168.20.195");
        //设置端口
        factory.setPort(5672);
        //设置用户名
        factory.setUsername("admin");
        //设置密码
        factory.setPassword("123456");
        //虚拟机路径
        factory.setVirtualHost("/");

        try {
            connection = factory.newConnection();
            //创建信道
            channel = connection.createChannel();
        }catch (IOException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            e.printStackTrace();
        }

        return channel;
    }

    public static void closeConnection(){
        try {
            channel.close();
            connection.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            e.printStackTrace();
        }
    }

}
