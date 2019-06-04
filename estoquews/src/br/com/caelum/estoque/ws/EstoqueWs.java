package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
//saber qual o metodo(padrao)
//@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
//sem saber qual o metodo
//@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.BARE) 
public class EstoqueWs {

	private ItemDao dao = new ItemDao();
	
	@WebMethod(operationName="TodosOsItens")
	@WebResult(name="itens")
	@ResponseWrapper(localName="itens")
	@RequestWrapper(localName="listaItens")
	public List<Item> getItens(@WebParam(name="filtros") Filtros filtros) {
		System.out.println("Chamando getItens()");
		List<Filtro> lista = filtros.getLista();
		List<Item> itensResultado = dao.todosItens(lista);
		return itensResultado;
	}
	
	@WebMethod(operationName="Cadastrar")
	@WebResult(name="item")
	public Item cadastrarItem(
			@WebParam(name="tokenUsuario",header=true) TokenUsuario token, 
			@WebParam(name="item") Item item) 
					throws AutorizacaoException {
		System.out.println("cadastrando item " + item + ", Token: "+ token);
		
		if(!new TokenDao().ehValido(token)) {
			throw new AutorizacaoException("Autorização falhou");
		}
		
		new ItemValidador(item).validate();
		
		dao.cadastrar(item);
		
		return item;
	}
	
	@WebMethod(exclude=true)
    public void outroMetodo() { 
        //nao vai fazer parte do WSDL
    }
}
