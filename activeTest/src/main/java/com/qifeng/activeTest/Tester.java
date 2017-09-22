package com.qifeng.activeTest;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Session;

/**
 * 多线程发送消息
 */

public class Tester {
	public static void main(String[] args) {
		new Consumer().consumer();

		final ActiveConnector ac = new ActiveConnector();
		final Session session = ac.initSession(true);
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 5; i++) {
			threadPool.submit(new Runnable() {
				@Override
				public void run() {
					Producer jph = new Producer();
					try {
						Thread.sleep(new Random().nextInt(5) * 500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					jph.produce(ac, session);
				}
			});

		}
	}
}
