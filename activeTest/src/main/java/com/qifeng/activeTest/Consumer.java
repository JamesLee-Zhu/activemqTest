package com.qifeng.activeTest;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

/**
 * 消息生产者-消息发布者（多线程发送消息）
 * 
 */
public class Consumer {

	public void consumer() {
		MessageConsumer messageConsumer; // 消息的消费者
		try {
			ActiveConnector ac = new ActiveConnector();
			Session session = ac.initSession(false);
			messageConsumer = session.createConsumer(ac
					.initDestination(session)); // 创建消息消费者
			messageConsumer.setMessageListener(new Listener3()); // 注册消息监听
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}