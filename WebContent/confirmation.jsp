<!DOCTYPE html>
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
			<div class="panel-heading">Confirmation</div>
			<div class="panel-body">
				<div class="form-group">
					<label>ID:</label>
					<label>${produit.id}</label>
				</div>
				<div class="form-group">
					<label>Designation:</label>
					<label>${produit.designation}</label>
				</div>
				<div class="form-group">
					<label>Prix:</label>
					<label>${produit.prix}</label>
				</div>
				<div class="form-group">
					<label>Quantité:</label>
					<label>${produit.quantite}</label>
				</div>
			</div>
		</div>
	</div>
</body>
</html>