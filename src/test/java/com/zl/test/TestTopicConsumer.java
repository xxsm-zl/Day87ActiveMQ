package com.zl.test;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class TestTopicConsumer {
    @Test
    public void testConsumer(){

//        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        try {
            Connection connection = factory.createConnection();

            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Topic queue = session.createTopic("hellotopic");

            MessageConsumer consumer = session.createConsumer(queue);

            while(true){
                TextMessage msg = (TextMessage)consumer.receive(1000000);

                if(msg != null){
                    System.out.println("接收到的消息：" + msg.getText());
                }else{
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

}
