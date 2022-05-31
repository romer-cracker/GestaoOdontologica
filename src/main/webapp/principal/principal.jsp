<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
		<div id="content">
  		<div id="content-header">
    	<div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
	 	 </div>
	  		Conteudos do Sistema
 	</div>
	
	
	</div>
		
	<div class="row-fluid">
		<div id="footer" class="span12">
			2012 &copy; Marutii Admin. Brought to you by <a
				href="http://themedesigner.in">Themedesigner.in</a>
		</div>
	</div>
	<jsp:include page="javascriptfiles.jsp"></jsp:include>
</body>
</html>
