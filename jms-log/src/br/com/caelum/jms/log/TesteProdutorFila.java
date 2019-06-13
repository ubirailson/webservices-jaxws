package br.com.caelum.jms.log;


import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteProdutorFila {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination fila = (Destination) context.lookup("LOG");
		
		MessageProducer producer = session.createProducer(fila);
		
		
//		for (int i = 0; i < 1000; i++) {
//			Message message = session.createTextMessage("<pedido><id>" + i + "</id></pedido>");
//			producer.send(message);
//		}
		
		Message message = session.createTextMessage("INFO | Apache Active MQ");
		producer.send(message, DeliveryMode.NON_PERSISTENT, 3, 80000);
		
//		new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}

	public TesteProdutorFila() {
		// TODO Auto-generated constructor stub
	}
}
