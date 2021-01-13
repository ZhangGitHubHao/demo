//package com.example.study.activeMq;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageConsumer;
//import javax.jms.MessageListener;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
//public class TestConsumer {
//    //服务地址，端口默认61616
//    private static final String url="tcp://127.0.0.1:61616";
//    //这次发送的消息名称
//    private static final String topicName="queue_style";
//    public static void main(String[] args) throws JMSException {
//        //0. 先判断端口是否启动了  Active MQ 服务器
//        ActiveMq.checkServer();
//        //1.创建ConnectionFactory,绑定地址
//        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
//        //2.创建Connection
//        Connection connection = factory.createConnection();
//        //3.启动连接
//        connection.start();
//        //4.创建会话
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        //5.创建一个目标 (队列类型)
//        Destination destination = session.createQueue(topicName);
//        //6.创建消费者
//        MessageConsumer consumer = session.createConsumer(destination);
//        //7.创建监听者
//        consumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                TextMessage textMessage = (TextMessage) message;
//                try {
//                    System.out.println("接收:"+textMessage.getText());
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//}
