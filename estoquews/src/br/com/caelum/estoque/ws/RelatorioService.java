package br.com.caelum.estoque.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class RelatorioService {

    @WebMethod(operationName="GerarRelatorio")
    public void gerarRelatorio() { 
        // código omitido
    }

    public RelatorioService() {
		// TODO Auto-generated constructor stub
	}

}
