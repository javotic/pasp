/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.firma.fump.utils;

import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;


/**
 *
 * @author adrian
 */
public class SgSignSoapHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public void close(MessageContext ctx) {
    }

    @Override
    public boolean handleFault(SOAPMessageContext ctx) {
        return false;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext ctx) {
        Boolean outbound = (Boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound) {
            System.out.println("Salida");
            
        } else {
            System.out.println("Entrada");
        }
        return false;
    }
}

