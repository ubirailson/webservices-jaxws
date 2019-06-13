package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.modelo.Pedido;

public class TesteConsumidorTopicoComercial {

	public static void main(String[] args) throws NamingException, JMSException {
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
	    		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.setClientID("comercial");
		
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Topic topico = (Topic) context.lookup("loja");
		MessageConsumer consumer = session.createDurableSubscriber(topico, "assinatura");
				
//		Message message = consumer.receive(2000);
		
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
//				TextMessage textMessage = (TextMessage)message;
				ObjectMessage objectMessage = (ObjectMessage)message;
				
				
				
				try {
//					System.out.println(textMessage.getText());
					Pedido pedido = (Pedido)objectMessage.getObject();
					System.out.println(pedido.getCodigo());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			
		});
		
		
		new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}

	public TesteConsumidorTopicoComercial() {
		// TODO Auto-generated constructor stub
	}
}
