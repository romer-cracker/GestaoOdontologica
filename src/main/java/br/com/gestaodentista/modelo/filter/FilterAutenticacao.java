package br.com.gestaodentista.modelo.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.gestaodentista.modelo.conexao.SingleConnectionBanco;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao extends HttpFilter {
	
	private Connection connection;
       
  
    public FilterAutenticacao(){
       
    }

	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		try { 
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String usuarioLogado = (String) session.getAttribute("usuario");
			
			String urlParaAutenticar = req.getServletPath();/*Url que está sendo acessada*/
			
			/*Validar se está logado senão redireciona para a tela de login*/
			if (usuarioLogado == null  && 
					!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {/*Não está logado*/
				
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				redireciona.forward(request, response);
				return; /*Para a execução e redireciona para o login*/
				
			}else {
				chain.doFilter(request, response);
			}
			
			connection.commit();/*Deu tudo certo, então comita as alteracoes no banco de dados*/
		
	    }catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
