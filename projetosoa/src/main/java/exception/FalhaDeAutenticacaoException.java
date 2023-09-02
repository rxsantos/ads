package exception;

 

import javax.xml.ws.WebFault;

 

@WebFault(name = "FalhaDeAutenticacao")

public class FalhaDeAutenticacaoException extends Exception {

       

        private static final long serialVersionUID = 1L;

 

        public FalhaDeAutenticacaoException(String mensagem) {

        	super(mensagem);

        }

}