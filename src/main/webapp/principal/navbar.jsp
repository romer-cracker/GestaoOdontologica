<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li class="" ><a title="" href="#"><i class="icon icon-user"></i> <span class="text"><%= request.getSession().getAttribute("usuario") %></span></a></li>
    <li class=" dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">Messages</span> <span class="label label-important">5</span> <b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a class="sAdd" title="" href="#">new message</a></li>
        <li><a class="sInbox" title="" href="#">inbox</a></li>
        <li><a class="sOutbox" title="" href="#">outbox</a></li>
        <li><a class="sTrash" title="" href="#">trash</a></li>
      </ul>
    </li>
    <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">Settings</span></a></li>
    <li class=""><a title="" href="<%= request.getContextPath() %>/ServletLogin?acao=logout"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
  </ul>
</div>
<div id="search">
  <input type="text" placeholder="Search here..."/>
  <button type="submit" class="tip-left" title="Search"><i class="icon-search icon-white"></i></button>
</div>
<!--close-top-Header-menu-->

<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>

<ul>

    <li class="active"> <a href="principal/principal.jsp"> <i class="icon icon-home"></i> <span>Inicio</span></a> </li> 
    
    <li> <a href="<%= request.getContextPath() %>/ServletUsuarioController"> <i class="icon-user"></i> <span>Usuários</span> </a></li>
     
    <li> <a href="<%= request.getContextPath() %>/ServletPaciente"> <i class="icon-list-alt"></i> <span>Pacientes</span> </a></li>
    
    <li> <a href="<%= request.getContextPath() %>/ServletConsultaController"> <i class="icon-book"></i> <span>Consultas</span> </a></li>
    
    <li> <a href="<%= request.getContextPath() %>/ServletAuditorController"> <i class="icon-briefcase"></i> <span>Auditoria</span> </a></li>
    
    <li> <a href="grid.html"> <i class="icon icon-fullscreen"></i> <span>Contratos</span> </a></li>
    
    <li> <a href="<%= request.getContextPath() %>/ServletDentistaController"> <i class="icon icon-th-list"></i> <span>Dentistas</span> </a></li>
    
    <li> <a href="<%= request.getContextPath() %>/ServletPagamentoController"> <i class="icon-barcode"></i> <span>Pagamentos</span> </a></li>
    
    <li> <a href="<%= request.getContextPath() %>/ServletProcedimentoController""> <i class="icon icon-pencil"></i> <span>Procedimentos</span> </a></li>
    
  </ul>
</div>
