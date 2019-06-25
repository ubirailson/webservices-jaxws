package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaMovimentacoes {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				ProducerTemplate producer = context.createProducerTemplate();
				producer.sendBody("direct:entrada", 
				        "<movimentacoes>" +
				            "<movimentacao><valor>2314.4</valor><data>11/12/2015</data><tipo>ENTRADA</tipo></movimentacao>" +
				            "<movimentacao><valor>546.98</valor><data>11/12/2015</data><tipo>SAIDA</tipo></movimentacao>" + 
				            "<movimentacao><valor>314.1</valor><data>12/12/2015</data><tipo>SAIDA</tipo></movimentacao>" + 
				            "<movimentacao><valor>56.99</valor><data>13/12/2015</data><tipo>SAIDA</tipo></movimentacao>" + 
				        "</movimentacoes>");
				
				from("direct:entrada").
			    to("xslt:movimentacoes-para-html.xslt").
			        setHeader(Exchange.FILE_NAME, constant("movimentacoes.html")).
			        log("${body}").
			    to("file:saida");
			}
		});
		
		context.start();
		Thread.sleep(20000);
		context.stop();
	}	
}
