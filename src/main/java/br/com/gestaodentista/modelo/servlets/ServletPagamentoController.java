package br.com.gestaodentista.modelo.servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestaodentista.modelo.dao.DAOPagamentoRepository;
import br.com.gestaodentista.modelo.entidade.Pagamento;
import javax.servlet.http.HttpServlet;

@WebServlet("/ServletPagamentoController")
public class ServletPagamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOPagamentoRepository daoPagamentoRepository =new DAOPagamentoRepository();
       
   
    public ServletPagamentoController() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idPagamento = request.getParameter("id");

				daoPagamentoRepository.deletarPagamento(idPagamento);

				List<Pagamento> pagamentos = daoPagamentoRepository.consultarPagamentoList();
				request.setAttribute("pagamentos", pagamentos);

				request.setAttribute("msg", "Excluido com sucesso!");
				request.getRequestDispatcher("principal/pagamento.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {

				String idPagamento = request.getParameter("id");

				daoPagamentoRepository.deletarPagamento(idPagamento);

				response.getWriter().write("Excluido com sucesso!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<Pagamento> dadosJson = daoPagamentoRepository.consultarPagamentoList(nomeBusca);

				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJson);

				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				Pagamento pagamento = daoPagamentoRepository.consultarPagamentoID(id);

				List<Pagamento> pagamentos = daoPagamentoRepository.consultarPagamentoList();
				request.setAttribute("pagamentos", pagamentos);

				request.setAttribute("msg", "Pagamento em edição");
				request.setAttribute("pagamento", pagamento);
				request.getRequestDispatcher("principal/pagamento.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

				List<Pagamento> pagamentos = daoPagamentoRepository.consultarPagamentoList();

				request.setAttribute("msg", "Pagamentos carregados");
				request.setAttribute("pagamentos", pagamentos);
				request.getRequestDispatcher("principal/pagamento.jsp").forward(request, response);

			} else {

				List<Pagamento> pagamentos = daoPagamentoRepository.consultarPagamentoList();
				request.setAttribute("pagamentos", pagamentos);
				request.getRequestDispatcher("principal/pagamento.jsp").forward(request, response);
			}

		} catch (Exception e) {
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
			String dataPagamento = request.getParameter("dataPagamento");
			String dataVencimento = request.getParameter("dataVencimento");
			String valor = request.getParameter("valor");
			String juros = request.getParameter("juros");
			String valorTotal = request.getParameter("valorTotal");
			String descricao = request.getParameter("descricao");

			Pagamento pagamento = new Pagamento();

			pagamento.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			pagamento.setDataPagamento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataPagamento))));
			pagamento.setDataVencimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataVencimento))));
			pagamento.setValor(Double.parseDouble(valor));
			pagamento.setJuros(Double.parseDouble(juros));
			pagamento.setValorTotal(Double.parseDouble(valorTotal));
			pagamento.setDescricao(descricao);

			if (pagamento.isNovo()) {
				msg = "Gravado com sucesso!";
			} else {
				msg = "Atualizado com sucesso!";
			}

			pagamento = daoPagamentoRepository.gravarPagamento(pagamento);

			List<Pagamento> pagamentos = daoPagamentoRepository.consultarPagamentoList();
			request.setAttribute("pagamentos", pagamentos);

			request.setAttribute("msg", msg);
			request.setAttribute("pagamento", pagamento);
			request.getRequestDispatcher("principal/pagamento.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
