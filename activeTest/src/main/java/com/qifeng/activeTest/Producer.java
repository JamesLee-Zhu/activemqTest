package com.qifeng.activeTest;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 消息生产者-消息发布者（多线程发送）
 * 
 */
public class Producer {

	public void produce(ActiveConnector ac, Session session) {
		try {
			MessageProducer messageProducer; // 消息生产者
			messageProducer = session.createProducer(ac
					.initDestination(session)); // 创建消息生产者
			for (int i = 0; i < 10; i++) {
				TextMessage message = session.createTextMessage("ActiveMQ中"
						+ Thread.currentThread().getName() + "线程发送的数据" + ":"
						+ i);
				System.out.println(Thread.currentThread().getName() + "线程"
						+ "发送消息：" + "ActiveMQ 发布的消息" + ":" + i);
				messageProducer.send(message);
				session.commit();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}