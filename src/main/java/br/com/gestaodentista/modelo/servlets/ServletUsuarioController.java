package br.com.gestaodentista.modelo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestaodentista.modelo.dao.DAOUserAdminFuncionariorepository;
import br.com.gestaodentista.modelo.entidade.UserAdminFuncionario;


@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUserAdminFuncionariorepository daoUserAdminFuncionariorepository = new DAOUserAdminFuncionariorepository();

	public ServletUsuarioController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idAdmin = request.getParameter("id");

				daoUserAdminFuncionariorepository.deletarAdmin(idAdmin);

				List<UserAdminFuncionario> admins = daoUserAdminFuncionariorepository.consultarAdminList();
				request.setAttribute("admins", admins);

				request.setAttribute("msg", "Excluido com sucesso!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {

				String idAdmin = request.getParameter("id");

				daoUserAdminFuncionariorepository.deletarAdmin(idAdmin);

				response.getWriter().write("Excluido com sucesso!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<UserAdminFuncionario> dadosJson = daoUserAdminFuncionariorepository.consultarAdminList(nomeBusca);

				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJson);

				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				UserAdminFuncionario userAdminFuncionario = daoUserAdminFuncionariorepository.consultarAdminID(id);

				List<UserAdminFuncionario> admins = daoUserAdminFuncionariorepository.consultarAdminList();
				request.setAttribute("admins", admins);

				request.setAttribute("msg", "Usuários em edição");
				request.setAttribute("userAdminFuncionario", userAdminFuncionario);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

				List<UserAdminFuncionario> admins = daoUserAdminFuncionariorepository.consultarAdminList();

				request.setAttribute("msg", "Usuários carregados");
				request.setAttribute("admins", admins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else {

				List<UserAdminFuncionario> admins = daoUserAdminFuncionariorepository.consultarAdminList();
				request.setAttribute("admins", admins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Operação realizada com sucesso!";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String admissao = request.getParameter("admissao");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			UserAdminFuncionario userAdminFuncionario = new UserAdminFuncionario();

			userAdminFuncionario.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			userAdminFuncionario.setNome(nome);
			userAdminFuncionario.setAdmissao(admissao);
			userAdminFuncionario.setLogin(login);
			userAdminFuncionario.setSenha(senha);

			if (daoUserAdminFuncionariorepository.validarLogin(userAdminFuncionario.getLogin())
					&& userAdminFuncionario.getId() == null) {

				msg = "Login já existente, Informe outro!";
			} else {
				if (userAdminFuncionario.isNovo()) {
					msg = "Gravado com sucesso!";
				} else {
					msg = "Atualizado com sucesso!";
				}

				userAdminFuncionario = daoUserAdminFuncionariorepository.gravarAdmin(userAdminFuncionario);
			}

			List<UserAdminFuncionario> admins = daoUserAdminFuncionariorepository.consultarAdminList();
			request.setAttribute("admins", admins);

			request.setAttribute("msg", msg);
			request.setAttribute("userAdminFuncionario", userAdminFuncionario);
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
