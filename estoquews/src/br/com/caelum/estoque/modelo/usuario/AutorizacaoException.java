package br.com.caelum.estoque.modelo.usuario;

import java.util.Date;

import javax.xml.ws.WebFault;

@WebFault(name="AutorizacaoFault")
public class AutorizacaoException extends Exception {

	public AutorizacaoException() {
		// TODO Auto-generated constructor stub
	}

	public AutorizacaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AutorizacaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public AutorizacaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AutorizacaoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InfoFault getFaultInfo() {
		return new InfoFault("Token invalido", new Date());
	}
}
