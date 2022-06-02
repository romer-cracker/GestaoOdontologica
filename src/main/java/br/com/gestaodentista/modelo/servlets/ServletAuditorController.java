package br.com.gestaodentista.modelo.servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestaodentista.modelo.dao.DAOAuditorRepository;
import br.com.gestaodentista.modelo.entidade.Auditor;

@WebServlet("/ServletAuditorController")
public class ServletAuditorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOAuditorRepository daoAuditorRepository = new DAOAuditorRepository();

	public ServletAuditorController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idAuditor = request.getParameter("id");

				daoAuditorRepository.deletarAuditor(idAuditor);

				List<Auditor> auditores = daoAuditorRepository.consultarAuditorList();
				request.setAttribute("auditores", auditores);

				request.setAttribute("msg", "Excluido com sucesso!");
				request.getRequestDispatcher("principal/auditor.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {

				String idAuditor = request.getParameter("id");

				daoAuditorRepository.deletarAuditor(idAuditor);

				response.getWriter().write("Excluido com sucesso!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<Auditor> dadosJson = daoAuditorRepository.consultarAuditorList(nomeBusca);

				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJson);

				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				Auditor auditor = daoAuditorRepository.consultarAuditorID(id);

				List<Auditor> auditores = daoAuditorRepository.consultarAuditorList();
				request.setAttribute("auditores", auditores);

				request.setAttribute("msg", "Auditor em edição");
				request.setAttribute("auditor", auditor);
				request.getRequestDispatcher("principal/auditor.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

				List<Auditor> auditores = daoAuditorRepository.consultarAuditorList();

				request.setAttribute("msg", "Auditores carregadas");
				request.setAttribute("auditores", auditores);
				request.getRequestDispatcher("principal/auditor.jsp").forward(request, response);

			} else {

				List<Auditor> auditores = daoAuditorRepository.consultarAuditorList();
				request.setAttribute("auditores", auditores);
				request.getRequestDispatcher("principal/auditor.jsp").forward(request, response);
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
			String dataCriacao = request.getParameter("dataCriacao");
			String dataUltimaModificacao = request.getParameter("dataUltimaModificacao");
			String modificadoPor = request.getParameter("modificadoPor");
			String criadoPor = request.getParameter("criadoPor");

			Auditor auditor = new Auditor();

			auditor.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			auditor.setDataCriacao(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataCriacao))));
			auditor.setDataUltimaModificacao(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataUltimaModificacao))));
			auditor.setModificadoPor(modificadoPor);
			auditor.setCriadoPor(criadoPor);

			if (auditor.isNovo()) {
				msg = "Gravado com sucesso!";
			} else {
				msg = "Atualizado com sucesso!";
			}

			auditor = daoAuditorRepository.gravarAuditor(auditor);

			List<Auditor> auditores = daoAuditorRepository.consultarAuditorList();
			request.setAttribute("auditores", auditores);

			request.setAttribute("msg", msg);
			request.setAttribute("auditor", auditor);
			request.getRequestDispatcher("principal/auditor.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}
}
