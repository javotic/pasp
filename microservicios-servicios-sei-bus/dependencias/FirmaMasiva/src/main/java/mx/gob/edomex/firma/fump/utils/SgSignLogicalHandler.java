/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.firma.fump.utils;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author adrian
 */
public class SgSignLogicalHandler implements LogicalHandler<LogicalMessageContext> {

    @Override
    public void close(MessageContext ctx) {
    }

    @Override
    public boolean handleFault(LogicalMessageContext ctx) {
        return false;
    }

    @Override
    public boolean handleMessage(LogicalMessageContext ctx) {
        try {
            Boolean outbound = (Boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            if (outbound) {

                Source s = ctx.getMessage().getPayload();
                                
                Transformer t = TransformerFactory.newInstance().newTransformer();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                Result result = new StreamResult(os);
                t.transform(s, result);
                Calendar c = Calendar.getInstance();
                ArchivoUtils.crearArchivoBinario("/home/adrian/Escritorio/salida"+c.getTime().toString(),os.toByteArray());

            } else {

                Source s = ctx.getMessage().getPayload();
                                
                Transformer t = TransformerFactory.newInstance().newTransformer();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                Result result = new StreamResult(os);
                t.transform(s, result);
                Calendar c = Calendar.getInstance();
                ArchivoUtils.crearArchivoBinario("/home/adrian/Escritorio/entrada"+c.getTime().toString(),os.toByteArray());
            }
        } catch (Exception e) {
        } finally {
            return true;
        }
    }
}
