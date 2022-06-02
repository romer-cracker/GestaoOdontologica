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

import br.com.gestaodentista.modelo.dao.DAOConsultaRepository;
import br.com.gestaodentista.modelo.entidade.Consulta;


@WebServlet("/ServletConsultaController")
public class ServletConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOConsultaRepository daoConsultaRepository = new DAOConsultaRepository();

	public ServletConsultaController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idConsulta = request.getParameter("id");

				daoConsultaRepository.deletarConsulta(idConsulta);

				List<Consulta> consultas = daoConsultaRepository.consultarConsultaList();
				request.setAttribute("consultas", consultas);

				request.setAttribute("msg", "Excluido com sucesso!");
				request.getRequestDispatcher("principal/consulta.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {

				String idConsulta = request.getParameter("id");

				daoConsultaRepository.deletarConsulta(idConsulta);

				response.getWriter().write("Excluido com sucesso!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<Consulta> dadosJson = daoConsultaRepository.consultarConsultaList(nomeBusca);

				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJson);

				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				Consulta consulta = daoConsultaRepository.consultarConsultaID(id);

				List<Consulta> consultas = daoConsultaRepository.consultarConsultaList();
				request.setAttribute("consultas", consultas);

				request.setAttribute("msg", "consulta em edição");
				request.setAttribute("consulta", consulta);
				request.getRequestDispatcher("principal/consulta.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

				List<Consulta> consultas = daoConsultaRepository.consultarConsultaList();

				request.setAttribute("msg", "consultas carregadas");
				request.setAttribute("consultas", consultas);
				request.getRequestDispatcher("principal/consulta.jsp").forward(request, response);

			} else {

				List<Consulta> consultas = daoConsultaRepository.consultarConsultaList();
				request.setAttribute("consultas", consultas);
				request.getRequestDispatcher("principal/consulta.jsp").forward(request, response);
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
			String dentista = request.getParameter("dentista");
			String paciente = request.getParameter("paciente");
			String dataCriacao = request.getParameter("dataCriacao");
			String valor = request.getParameter("valor");
			String tratamento = request.getParameter("tratamento");

			Consulta consulta = new Consulta();

			consulta.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			consulta.setDentista(dentista != null && !dentista.isEmpty() ? Long.parseLong(dentista) : null);
			consulta.setPaciente(paciente != null && !paciente.isEmpty() ? Long.parseLong(paciente) : null);
			consulta.setDataCriacao(Date.valueOf(
					new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataCriacao))));
			consulta.setValor(Double.parseDouble(valor));
			consulta.setTratamento(tratamento != null && !tratamento.isEmpty() ? Long.parseLong(tratamento) : null);

			if (consulta.isNovo()) {
				msg = "Gravado com sucesso!";
			} else {
				msg = "Atualizado com sucesso!";
			}

			consulta = daoConsultaRepository.gravarConsulta(consulta);

			List<Consulta> consultas = daoConsultaRepository.consultarConsultaList();
			request.setAttribute("consultas", consultas);

			request.setAttribute("msg", msg);
			request.setAttribute("consulta", consulta);
			request.getRequestDispatcher("principal/consulta.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}
}
