package br.com.caelum.consumer.queue;

import javax.jms.Message;
import java.util.Scanner;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;

public class QueueReceiverTest {

    public static void main(String[] args) throws Exception {
        
    	InitialContext context = new InitialContext();
        QueueConnectionFactory cf = (QueueConnectionFactory)context.lookup("ConnectionFactory");
        
        QueueConnection conexao = cf.createQueueConnection();
        conexao.start();
        QueueSession sessao = conexao.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue fila = (Queue) context.lookup("financeiro");
        QueueReceiver receiver = (QueueReceiver) sessao.createReceiver(fila );

        Message message = receiver.receive();
        
        System.out.println("Recebendo Queue msg: " + message);

        new Scanner(System.in).nextLine();

        sessao.close();
        conexao.close();    
        context.close();
    }
}