package com.zl.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class TestTopicProducer {

    @Test
    public void testConsumer(){
        //	�������ӹ�������
//		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        try {
            //	ͨ�����ӹ������󴴽����Ӷ���
            Connection connection = connectionFactory.createConnection();

            //	��������
            connection.start();

            //	����Session���󣬵�һ���������Ƿ�������
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
                TextMessage msg = session.createTextMessage("activeMQ������Ϣ" + i);
                System.out.println("activeMQ������Ϣ" + i);
                producer.send(msg);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

