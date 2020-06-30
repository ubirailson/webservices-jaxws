
package br.com.caelum.estoque.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5-jbossorg-1
 * 2019-05-30T15:31:54.767-03:00
 * Generated source version: 3.2.5-jbossorg-1
 */

@WebFault(name = "AutorizacaoFault", targetNamespace = "http://ws.estoque.caelum.com.br/")
public class AutorizacaoFault extends Exception {

    private java.lang.String autorizacaoFault;

    public AutorizacaoFault() {
        super();
    }

    public AutorizacaoFault(String message) {
        super(message);
    }

    public AutorizacaoFault(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public AutorizacaoFault(String message, java.lang.String autorizacaoFault) {
        super(message);
        this.autorizacaoFault = autorizacaoFault;
    }

    public AutorizacaoFault(String message, java.lang.String autorizacaoFault, java.lang.Throwable cause) {
        super(message, cause);
        this.autorizacaoFault = autorizacaoFault;
    }

    public java.lang.String getFaultInfo() {
        return this.autorizacaoFault;
    }
}