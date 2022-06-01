package br.com.gestaodentista.modelo.servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestaodentista.modelo.dao.DAOProcedimentoRepository;
import br.com.gestaodentista.modelo.entidade.Procedimento;
import br.com.gestaodentista.modelo.entidade.UserAdminFuncionario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletProcedimentoController")
public class ServletProcedimentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOProcedimentoRepository daoProcedimentoRepository = new DAOProcedimentoRepository();

	public ServletProcedimentoController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idProcedimento = request.getParameter("id");

				daoProcedimentoRepository.deletarProcedimento(idProcedimento);

				List<Procedimento> procedimentos = daoProcedimentoRepository.consultarProcedimentoList();
				request.setAttribute("procedimentos", procedimentos);

				request.setAttribute("msg", "Excluido com sucesso!");
				request.getRequestDispatcher("principal/procedimento.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {

				String idProcedimento = request.getParameter("id");

				daoProcedimentoRepository.deletarProcedimento(idProcedimento);

				response.getWriter().write("Excluido com sucesso!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<Procedimento> dadosJson = daoProcedimentoRepository.consultarProcedimentoList(nomeBusca);

				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJson);

				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				Procedimento procedimento = daoProcedimentoRepository.consultarProcedimentoID(id);

				List<Procedimento> procedimentos = daoProcedimentoRepository.consultarProcedimentoList();
				request.setAttribute("procedimentos", procedimentos);

				request.setAttribute("msg", "procedimentos em edição");
				request.setAttribute("procedimento", procedimento);
				request.getRequestDispatcher("principal/procedimento.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

				List<Procedimento> procedimentos = daoProcedimentoRepository.consultarProcedimentoList();

				request.setAttribute("msg", "procedimentos carregados");
				request.setAttribute("procedimentos", procedimentos);
				request.getRequestDispatcher("principal/procedimentos.jsp").forward(request, response);

			} else {

				List<Procedimento> procedimentos = daoProcedimentoRepository.consultarProcedimentoList();
				request.setAttribute("procedimentos", procedimentos);
				request.getRequestDispatcher("principal/procedimento.jsp").forward(request, response);
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
			String descricao = request.getParameter("descricao");

			Procedimento procedimento = new Procedimento();

			procedimento.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			procedimento.setNome(nome);
			procedimento.setDescricao(descricao);

			if (procedimento.isNovo()) {
				msg = "Gravado com sucesso!";
			} else {
				msg = "Atualizado com sucesso!";
			}

			procedimento = daoProcedimentoRepository.gravarProcedimento(procedimento);

			List<Procedimento> procedimentos = daoProcedimentoRepository.consultarProcedimentoList();
			request.setAttribute("procedimentos", procedimentos);

			request.setAttribute("msg", msg);
			request.setAttribute("procedimento", procedimento);
			request.getRequestDispatcher("principal/procedimento.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}
}
