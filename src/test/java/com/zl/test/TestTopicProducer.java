package com.zl.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class TestTopicProducer {

    @Test
    public void testConsumer(){
        //	创建连接工厂对象
//		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        try {
            //	通过连接工厂对象创建连接对象
            Connection connection = connectionFactory.createConnection();

            //	启动连接
            connection.start();

            //	创建Session对象，第一个参数是是否开启事务
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Topic queue = session.createTopic("hellotopic");

            MessageProducer producer = session.createProducer(queue);

            sendMessage(session, producer);

            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Session session, MessageProducer producer) {
        for(int i = 0; i < 20; i++){
            try {
                TextMessage msg = session.createTextMessage("activeMQ发送消息" + i);
                System.out.println("activeMQ发送消息" + i);
                producer.send(msg);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

