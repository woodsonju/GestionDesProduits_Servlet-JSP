<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Produits</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container col-md-10 col-xs-12 col-sm-12s col-md-offset-1 spacer">
		<div class="panel panel-primary">
			<div class="panel-heading">Editer un produit</div>
			<div class="panel-body">
				<!-- Quand je valide mon formulaire j'appel l'action updateProduit.do -->
				<form action="updateProduit.do" method="post">
					<div class="form-group">
						<label class="control-label">ID: </label>
						<input type="hidden" name=id class="form-control" value="${produit.id}" required/>
						<label>${produit.id}</label>
						<span></span>
					</div>
					<div class="form-group">s
						<label class="control-label">Désignation</label>
						<input type="text" name=designation class="form-control" value="${produit.designation}" required/>
						<span></span>
					</div>
					<div class="form-group">
						<label class="control-label">Prix</label>
						<input type="text" name=prix class="form-control" value="${produit.prix}" />
						<span></span>
					</div>
					<div class="form-group">
						<label class="control-label">Quantité</label>
						<input type="text" name=quantite class="form-control" value="${produit.quantite}" />
						<span></span>
					</div>
					<div>
						<button type="submit" class="btn btn-primary">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>