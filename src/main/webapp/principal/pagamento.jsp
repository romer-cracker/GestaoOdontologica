<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
<body>

	<div id="header">
		<h1>
			<a href="dashboard.html">Maruti Admin</a>
		</h1>
	</div>

	<div class="btn-group rightzero">
		<a class="top_message tip-left" title="Manage Files"><i
			class="icon-file"></i></a> <a class="top_message tip-bottom"
			title="Manage Users"><i class="icon-user"></i></a> <a
			class="top_message tip-bottom" title="Manage Comments"><i
			class="icon-comment"></i><span class="label label-important">5</span></a>
		<a class="top_message tip-bottom" title="Manage Orders"><i
			class="icon-shopping-cart"></i></a>
	</div>

	<jsp:include page="navbar.jsp"></jsp:include>


	<div class="container-fluid">
		<div id="content" >
			<div id="content-header">
				<div id="breadcrumb">
					<a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				</div>
			</div>
			<div class="row-fluid" >
				<div class="span6" style="margin: 0 auto;">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i>
							</span>
							<h5>Pagamentos</h5>
						</div>
						<div class="widget-content nopadding" >
							<form action="<%=request.getContextPath()%>/ServletPagamentoController" method="post" class="form-horizontal" id="formUser">
								<div class="control-group">
									<label class="control-label" for="id">ID :</label>
									<div class="controls">
										<input type="text" class="span11" id="id" name="id" readonly="readonly" value="${pagamento.id}">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="dataPagamento">Data de Pagamento :</label>
									<div class="controls">
										<input type="text" class="span11" name="dataPagamento" id="dataPagamento" required="required" value="${pagamento.dataPagamento}">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="dataVencimento">Data de Vencimento :</label>
									<div class="controls">
										<input type="text" class="span11" name="dataVencimento" id="dataVencimento" required="required" value="${pagamento.dataVencimento}">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="valor">Valor :</label>
									<div class="controls">
										<input type="text" class="span11" name="valor" id="valor" required="required" value="${pagamento.valor}">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="juros">Juros :</label>
									<div class="controls">
										<input type="text" class="span11" name="juros" id="juros" required="required" value="${pagamento.juros}">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="valorTotal">Valor Total :</label>
									<div class="controls">
										<input type="text" class="span11" name="valorTotal" id="valorTotal" required="required" value="${pagamento.valorTotal}">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="descricao">Descrição :</label>
									<div class="controls">
										<input type="text" class="span11" name="descricao" id="descricao" required="required" value="${pagamento.descricao}">
									</div>
								</div>
								
									
								<div class="form-actions">
									<div class="span12 btn-icon-pg" style="padding-left: 20px;">
										<button class="btn btn-success">Salvar</button>
										<button type="button" class="btn btn-primary" onclick="limparForm();">Novo</button>
										<button type="button" class="btn btn-info" onclick="criarDeleteComAjax();">Excluir</button>
										<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#exampleModalPaciente"> Pesquisar </button>
									</div>
									
									<span>${msg}</span>
								</div>
							</form>
						</div>
					</div>
					<div style="height: 300px; overflow: scroll;">
						<table class="table" id="tabelaresultadosview">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Descrição</th>
									<th scope="col">Ver</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items='${pagamentos}' var='pag'>
									<tr>
										<td><c:out value="${pag.id}"></c:out></td>
										<td><c:out value="${pag.descricao}"></c:out></td>
										<td><a class="btn btn-success"
											href="<%= request.getContextPath() %>/ServletPagamentoController?acao=buscarEditar&id=${pag.id}">Ver</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>


			</div>

		</div>
	</div>
	

	<jsp:include page="javascriptfiles.jsp"></jsp:include>

	<script type="text/javascript">
	
	function verEditar(id) {
		   
	    var urlAction = document.getElementById('formUser').action;
	    
	    
	    window.location.href = urlAction + '?acao=buscarEditar&id='+id;
	    
	}
	
	function buscaUserPagAjax(url){
		   
	    
	    var urlAction = document.getElementById('formUser').action;
	    var nomeBusca = document.getElementById('nomeBusca').value;
	    
		 $.ajax({	     
		     method: "get",
		     url : urlAction,
		     data : url,
		     success: function (response, textStatus, xhr) {
			 
			 var json = JSON.parse(response);
			 
			 
			 $('#tabelaresultados > tbody > tr').remove();
			 $("#ulPaginacaoUserAjax > li").remove();
			 
			  for(var p = 0; p < json.length; p++){
			      $('#tabelaresultados > tbody').append('<tr> <td>'+json[p].id+'</td> <td> '+json[p].descricao+'</td> <td><button onclick="verEditar('+json[p].id+')" type="button" class="btn btn-info">Ver</button></td></tr>');
			  }
			  
			  document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
			  
			    var totalPagina = xhr.getResponseHeader("totalPagina");
		
			  
			    
				  for (var p = 0; p < totalPagina; p++){
				      
			
				      
				      var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ (p * 5);
				      
				   
				      $("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>'); 
				      
				  }
			 
		     }
		     
		 }).fail(function(xhr, status, errorThrown){
		    alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
		 });
	    
	}
	
	
		function limparForm(){
			var elementos = document.getElementById("formUser").elements;
			
			for(var p = 0; p < elementos.length; p++){
				
				elementos[p].value = '';
			}
			
		}
		
		function criarDelete(){
			
			if(confirm('Deseja realmente excluir esses dados?')){
				
				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();
			}
		}
		
		function criarDeleteComAjax() {
		    
		    if (confirm('Deseja realmente excluir os dados?')){
			
			 var urlAction = document.getElementById('formUser').action;
			 var idPagamento = document.getElementById('id').value;
			 
			 $.ajax({
			     
			     method: "get",
			     url : urlAction,
			     data : "id=" + idPagamento + '&acao=deletarajax',
			     success: function (response) {
				 
				  limparForm();
				  document.getElementById('msg').textContent = response;
			     }
			     
			 }).fail(function(xhr, status, errorThrown){
			    alert('Erro ao deletar paciente por id: ' + xhr.responseText);
			 });
			 
			  
		    }
		    
		}
		
		function buscarUsuario() {
		    
			var nomeBusca = document.getElementById('nomeBusca').value;
			
			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){ /*Validando que tem que ter valor pra buscar no banco*/
			
			 var urlAction = document.getElementById('formUser').action;
			
			 $.ajax({
				 
				 method: "get",
				 url : urlAction,
				 data : "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
				 success: function (response) {
				 
					 var json = JSON.parse(response);
					 
					 
					 $('#tabelaresultados > tbody > tr').remove();
					 $("#ulPaginacaoUserAjax > li").remove();
					 
					  for(var p = 0; p < json.length; p++){
					      $('#tabelaresultados > tbody').append('<tr> <td>'+json[p].id+'</td> <td> '+json[p].descricao+'</td> <td><button onclick="verEditar('+json[p].id+')" type="button" class="btn btn-info">Ver</button></td></tr>');
					  }
					  
					  document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
					  
					    var totalPagina = xhr.getResponseHeader("totalPagina");
				
					  
					    
						  for (var p = 0; p < totalPagina; p++){
						      
						      var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ (p * 5);
						      
						   
						      $("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>');
						      
						  }
				 
				 }
				 
			 }).fail(function(xhr, status, errorThrown){
				alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
			 });
			
			
			}
			
		}
		
	</script>
</body>
</html>
