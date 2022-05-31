package br.com.gestaodentista.modelo.servlets;

import java.io.IOException;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestaodentista.modelo.dao.PacienteDAO;
import br.com.gestaodentista.modelo.entidade.Paciente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ServletPaciente")
public class ServletPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PacienteDAO pacienteDAO = new PacienteDAO();
       
    
    public ServletPaciente() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {	
		String acao= request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			
			String idPaciente = request.getParameter("id");
			
			pacienteDAO.deletarPaciente(idPaciente);
			
			List<Paciente> pacientes = pacienteDAO.consultarPacienteList();
			request.setAttribute("pacientes", pacientes);
			
			request.setAttribute("msg", "Excluido com sucesso!");
			request.getRequestDispatcher("principal/paciente.jsp").forward(request, response);
			
		}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
			
			String idPaciente = request.getParameter("id");
			
			pacienteDAO.deletarPaciente(idPaciente);
			
			response.getWriter().write("Excluido com sucesso!");
			
		}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
			
			String nomeBusca = request.getParameter("nomeBusca");
			
			List<Paciente> dadosJson = pacienteDAO.consultarPacienteList(nomeBusca);
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(dadosJson);
			
			response.getWriter().write(json);
			
		}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {
			
			 String nomeBusca = request.getParameter("nomeBusca");
			 String pagina = request.getParameter("pagina");
			 
			 List<Paciente> dadosJsonUser =  pacienteDAO.consultarPacienteListOffSet(nomeBusca, Integer.parseInt(pagina));
			 
			 ObjectMapper mapper = new ObjectMapper();
			 
			 String json = mapper.writeValueAsString(dadosJsonUser);
			 
			 response.addHeader("totalPagina", ""+ pacienteDAO.consultarPacienteListTotalPaginaPaginacao(nomeBusca));
			 response.getWriter().write(json);
			
			
		}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
			
			String id = request.getParameter("id");
			
			Paciente paciente = pacienteDAO.consultarPacienteID(id);
			
			List<Paciente> pacientes = pacienteDAO.consultarPacienteList();
			request.setAttribute("pacientes", pacientes);
			
			request.setAttribute("msg", "Paciente em edição");
			request.setAttribute("paciente", paciente);
			request.getRequestDispatcher("principal/paciente.jsp").forward(request, response);
			
		}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
			
			List<Paciente> pacientes = pacienteDAO.consultarPacienteList();
			
			request.setAttribute("msg", "Pacientes carregados");
			request.setAttribute("pacientes", pacientes);
			request.getRequestDispatcher("principal/paciente.jsp").forward(request, response);
			
		} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
			
			Integer offset = Integer.parseInt(request.getParameter("pagina"));
			 
			List<Paciente> pacientes = pacienteDAO.consultarPacienteListPaginada(offset);
			 
			request.setAttribute("pacientes", pacientes);
			request.getRequestDispatcher("principal/paciente.jsp").forward(request, response);
			
		}else {
			
			List<Paciente> pacientes = pacienteDAO.consultarPacienteList();
			request.setAttribute("pacientes", pacientes);
			request.getRequestDispatcher("principal/paciente.jsp").forward(request, response);
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
	}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String msg = "Operação realizada com sucesso!";
			
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String rg = request.getParameter("admissao");
			String cpf = request.getParameter("login");
			String sexo = request.getParameter("senha");
			String endereco = request.getParameter("endereco");
			String bairro = request.getParameter("bairro");
			String municipio = request.getParameter("municipio");
			String cep = request.getParameter("cep");
			String uf = request.getParameter("uf");
			String telefone = request.getParameter("telefone");
			String celular = request.getParameter("celular");
			String email = request.getParameter("email");
			String grupo = request.getParameter("grupo");
			String situacao = request.getParameter("situacao");
			
			Paciente paciente = new Paciente();
			
			paciente.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			paciente.setNome(nome);
			paciente.setRg(rg);
			paciente.setCpf(cpf);
			paciente.setSexo(sexo);
			paciente.setEndereco(endereco);
			paciente.setBairro(bairro);
			paciente.setMunicipio(municipio);
			paciente.setCep(cep);
			paciente.setUf(uf);
			paciente.setTelefone(telefone);
			paciente.setCelular(celular);
			paciente.setEmail(email);
			paciente.setGrupo(grupo);
			paciente.setSituacao(situacao);
			
			if(pacienteDAO.validarCpf(paciente.getCpf()) && paciente.getId() == null) {
				
				msg = "cpf já existente, Informe outro!";
			}else {
				if(paciente.isNovo()) {
					msg = "Gravadocom sucesso!";
				}else {
					msg = "Atualizado com sucesso!";
				}
				
				paciente = pacienteDAO.gravarPaciente(paciente);
			}
			
			List<Paciente> pacientes = pacienteDAO.consultarPacienteList();
			request.setAttribute("pacientes", pacientes);
			
			request.setAttribute("msg", msg);
			request.setAttribute("paciente", paciente);
			request.getRequestDispatcher("principal/paciente.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
