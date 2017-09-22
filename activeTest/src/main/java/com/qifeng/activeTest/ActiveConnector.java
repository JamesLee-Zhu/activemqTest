package com.qifeng.activeTest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveConnector {

	public Session initSession(boolean tra) {
		// 实例化连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD,
				ActiveMQConnection.DEFAULT_BROKER_URL);
		try {
			Connection connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
			connection.start();
			return connection.createSession(tra,
					Session.AUTO_ACKNOWLEDGE); // 创建Session
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Destination initDestination(Session session) {
		try {
			return session.createQueue("queue1");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}
}
