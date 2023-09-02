package soap;


import java.util.List;

import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import exception.DivisaoPorZeroException;
import exception.FalhaDeAutenticacaoException;
 


@WebService
@SOAPBinding(style = Style.RPC)
public class Calculadora {
	
    @Resource
    WebServiceContext context;
	
	
    @WebMethod(action = "somar")
    public int somar(
                 @WebParam(name = "numero1") int numero1,
                 @WebParam(name = "numero2") int numero2) throws FalhaDeAutenticacaoException {
    	  autenticar(context);
          return numero1 + numero2;

    }     
    
    @WebMethod(action = "subtrair")
    public int subtrair(
                 @WebParam(name = "numero1") int numero1,
                 @WebParam(name = "numero2") int numero2) {
          return numero1 - numero2;

    }    
    
    @WebMethod(action = "multiplicar")
    public int multiplicar(
                 @WebParam(name = "numero1") int numero1,
                 @WebParam(name = "numero2") int numero2) {
          return numero1 * numero2;

    }    

    @WebMethod(action = "dividir")
    public double dividir(
                 @WebParam(name = "numero1") double numero1,
                 @WebParam(name = "numero2") double numero2) throws DivisaoPorZeroException {
    		
    		if (numero2 == 0) {
    			throw new DivisaoPorZeroException();
    		}
    		
          return numero1 / numero2;

    }
    
    @SuppressWarnings("rawtypes")

    private boolean autenticar (WebServiceContext context) throws FalhaDeAutenticacaoException {

            MessageContext mc = context.getMessageContext();       

            Map httpHeaders = (Map) mc.get(MessageContext.HTTP_REQUEST_HEADERS);

           

            if (!httpHeaders.containsKey("usuario"))

                    throw new FalhaDeAutenticacaoException("Informe um usuário");

           

            if (!httpHeaders.containsKey("senha"))

                    throw new FalhaDeAutenticacaoException("Informe uma senha");

           

            String usuario = ((List) httpHeaders.get("usuario")).get(0).toString();

            String senha = ((List) httpHeaders.get("senha")).get(0).toString();

           

            if (usuario.equals("sisfin") && senha.equals("sisfin123")) {

                    return true;

            } else {

                    throw new FalhaDeAutenticacaoException("Usuário e senha inválido");

            }

    }


}
