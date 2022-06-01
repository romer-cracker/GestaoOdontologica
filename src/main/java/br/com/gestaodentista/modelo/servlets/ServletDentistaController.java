package br.com.gestaodentista.modelo.servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestaodentista.modelo.dao.DAODentistaController;
import br.com.gestaodentista.modelo.entidade.Dentista;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletDentistaController")
public class ServletDentistaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAODentistaController daoDentistaController = new DAODentistaController();

	public ServletDentistaController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idDentista = request.getParameter("id");

				daoDentistaController.deletarDentista(idDentista);

				List<Dentista> dentistas = daoDentistaController.consultarDentistaList();
				request.setAttribute("dentistas", dentistas);

				request.setAttribute("msg", "Dentista com sucesso!");
				request.getRequestDispatcher("principal/dentista.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {

				String idDentista = request.getParameter("id");

				daoDentistaController.deletarDentista(idDentista);

				response.getWriter().write("Excluido com sucesso!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<Dentista> dadosJson = daoDentistaController.consultarDentistaList(nomeBusca);

				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJson);

				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				Dentista dentista = daoDentistaController.consultarDentistaID(id);

				List<Dentista> dentistas = daoDentistaController.consultarDentistaList();
				request.setAttribute("dentistas", dentistas);

				request.setAttribute("msg", "Dentista em edição");
				request.setAttribute("dentista", dentista);
				request.getRequestDispatcher("principal/dentista.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

				List<Dentista> dentistas = daoDentistaController.consultarDentistaList();

				request.setAttribute("msg", "Dentistas carregados");
				request.setAttribute("dentistas", dentistas);
				request.getRequestDispatcher("principal/dentista.jsp").forward(request, response);

			} else {

				List<Dentista> dentistas = daoDentistaController.consultarDentistaList();
				request.setAttribute("dentistas", dentistas);
				request.getRequestDispatcher("principal/dentista.jsp").forward(request, response);
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
			String cro = request.getParameter("cro");
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String rg = request.getParameter("rg");

			Dentista dentista = new Dentista();

			dentista.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			dentista.setCro(cro);
			dentista.setNome(nome);
			dentista.setCpf(cpf);
			dentista.setRg(rg);

			if (daoDentistaController.validarCpf(dentista.getCpf()) && dentista.getId() == null) {

				msg = "cpf já existente, Informe outro!";
			} else {
				if (dentista.isNovo()) {
					msg = "Gravado com sucesso!";
				} else {
					msg = "Atualizado com sucesso!";
				}

				dentista = daoDentistaController.gravarDentista(dentista);
			}

			List<Dentista> dentistas = daoDentistaController.consultarDentistaList();
			request.setAttribute("dentistas", dentistas);

			request.setAttribute("msg", msg);
			request.setAttribute("dentista", dentista);
			request.getRequestDispatcher("principal/dentista.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}
}
