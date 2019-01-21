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
	<div class="container col-md-10 col-xs-12 col-sm-12 col-md-offset-1 spacer">
		<div class="panel panel-primary">
			<div class="panel-heading">Rechercher des produits</div>
			<div class="panel-body">
				<!-- Quand je valide mon formulaire j'appel l'action chercher.do -->
				<form action="chercher.do" method="get">
					<label>Mot clé</label>
					<input type="text" name="motCle" value="${model.motCle}"/>
					<button type="submit" class="btn btn-primary">Chercher</button>
				</form>
				<table class="table table-striped">
					<tr>
						<th>ID</th>
						<th>Désignation</th>
						<th>Prix</th>
						<th>Quantité</th>
					</tr>
					<!-- Dans le modèle on recupère la liste des produits 
					et chaque itération on met la valeur d'un produit dans p-->
					<c:forEach items="${model.produits}" var="p">
						<tr>
							<td>${p.id}</td>
							<td>${p.designation}</td>
							<td>${p.prix}</td>
							<td>${p.quantite}</td>
							<td><a onclick="return confirm('Ëtes-vous sûre de vouloir supprimer ce produit?')" 
									href="supprimer.do?id=${p.id}">Supprimer</a></td>
							<td><a href="editer.do?id=${p.id}">Editer</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>