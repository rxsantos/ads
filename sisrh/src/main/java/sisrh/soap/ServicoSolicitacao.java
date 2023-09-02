package sisrh.soap; 

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import sisrh.banco.Banco; 
import sisrh.dto.Solicitacao;
import sisrh.dto.Solicitacoes;
import sisrh.seguranca.Autenticador;


@WebService
@SOAPBinding(style = Style.RPC) 
public class ServicoSolicitacao {
	
	@Resource
	WebServiceContext context;
	
	@WebMethod(action = "listar") 
	public Solicitacoes listar() throws Exception {
			//Autenticador.autenticarUsuarioSenha(context);
			Solicitacoes solicitacoes = new Solicitacoes();
			List<Solicitacao> lista = Banco.listarSolicitacoes(); 
			for(Solicitacao emp: lista) {
					solicitacoes.getSolicitacoes().add(emp); 
			}		
			return solicitacoes;	 
	}
	
	@WebMethod(action = "listarSolicitacoesAtivas")
	//public Solicitacoes listarSolicitacoesAtivas(@WebParam(name = "usuario") String usuario) throws Exception {	
	public Solicitacoes listarSolicitacoesAtivas(String usuario) throws Exception {
			Autenticador.autenticarUsuarioSenha(context);
			// Pegar usuario do header
			usuario = Autenticador.getUsuario(context);
			Solicitacoes solicitacoes = new Solicitacoes();
			List<Solicitacao> lista = Banco.listarSolicitacoesPorUsuario(usuario); 
			for(Solicitacao emp: lista) {
					solicitacoes.getSolicitacoes().add(emp); 
			}
			return solicitacoes;	
	}

}
