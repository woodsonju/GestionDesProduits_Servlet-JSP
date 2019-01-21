package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.javafx.fxml.ParseTraceElement;

import dao.IProduitDAO;
import dao.ProduitDAOImpl;
import metier.entities.Produit;

@WebServlet(name="cs", urlPatterns= {"*.do"})
public class ControleurServlet extends HttpServlet{
	
	IProduitDAO metier;
	
	@Override
	public void init() throws ServletException {
		
		//metier = new ProduitDAOImpl();  //Avec spring on n'instancie pas l'objet métier
		
		
		ApplicationContext springContext  = WebApplicationContextUtils
											.getRequiredWebApplicationContext(this.getServletContext());
		
		//On obtient un des objets de spring qui crée au demarrage
		//metier = (IProduitDAO) springContext.getBean("dao");
		metier = springContext.getBean(IProduitDAO.class);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//On recupère l'action, la ressource
		String path = request.getServletPath();
		if(path.equals("/index.do"))
		{
			request.getRequestDispatcher("produits.jsp").forward(request, response);
		}
		
		else if(path.equals("/chercher.do")) {
			
			//Le controleur recupère le mot clé saisie dans le navigateur
			String motCle =  request.getParameter("motCle");
			
			//On stock le mot clé dans le modéle
			ProduitModel model = new ProduitModel();
			model.setMotCle(motCle);
			
			//Le controleur fait appel à la couche métier afin de récuperer la liste des produits
			List<Produit> produits = metier.produitParMC("%" + motCle + "%");
			
			//Le controleur stock la liste des produits dans le modèle
			model.setProduits(produits);
			
			//L'objet request récupère l'objet model afin de le transmettre à la vue
			request.setAttribute("model", model);
			
			//Forward vers la vue produit.jsp
			request.getRequestDispatcher("produits.jsp").forward(request, response);
		}
		else if(path.equals("/saisie.do"))
		{
			/*Stocker l'objet produit dont sa valeur est le constructeur par défaut, 
			 * dans l'objet request
			 * */
			request.setAttribute("produit", new Produit());
			//Forward vers la vue saisieProduit.jsp
			request.getRequestDispatcher("saisieProduit.jsp").forward(request, response);
		}
		else if(path.equals("/saveProduit.do") && (request.getMethod().equals("POST")))
		{
			/*
			 * Le controleur Recupère les données de la requête qui est saisie dans la vue
			 *  via la methode post
			 */
			String designation = request.getParameter("designation");
			double prix = Double.parseDouble(request.getParameter("prix"));
			int quantite = Integer.parseInt(request.getParameter("quantite"));
			
			
			/*
			 * Le controleur fait appel à la couche métier :
			 * - Transmet les valeurs recupérés vers la couche metier (objet Produit)
			 * - Appel la methode qui permet de sauver les valeurs récupérées vers la base de données
			 * - Il récupères ces valeurs avec l'ID afin de l'afficher
			 */
			Produit p = metier.saveProduit(new Produit(designation, prix, quantite));
			
			//On stock un objet produit dont sa valeur est p dans l'objet request
			request.setAttribute("produit", p);
			
			 //Le contoleur Forward vers la vue confirmation 
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
			
		}
		else if(path.equals("/supprimer.do"))
		{
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteProduit(id);
			//request.getRequestDispatcher("produits.jsp").forward(request, response);
			//On utilise plutot la redirection afin d'aller vers l'action chercher.do, on demande un autre ressource
			//Le client envoie une requete au serveur lu par la servlet
			//La servlet lui repond en lui renvoyant une ressource, un action vers laquelle il doit se rediriger
			//Le client envoie ensuite cette action au serveur
			response.sendRedirect("chercher.do?motCle=");

		}
		else if(path.equals("/editer.do"))
		{
			Long id = Long.parseLong(request.getParameter("id"));
			//Le controleur va chercher dans la base de données le produit qu'on veut editer
			Produit p = metier.getProduit(id);
			
			//Le controleur stock p dans le l'objet request
			request.setAttribute("produit", p);
			
			request.getRequestDispatcher("editProduit.jsp").forward(request, response);

		}
		else if(path.equals("/updateProduit.do") && (request.getMethod().equals("POST")))
		{
			Long id = Long.parseLong(request.getParameter("id")); //Pour faire un update il faut connaitre le id car c'est elle qui identifié le produit à mettre à jout
			String designation = request.getParameter("designation");
			double prix = Double.parseDouble(request.getParameter("prix"));
			int quantite = Integer.parseInt(request.getParameter("quantite"));
			
			Produit p = new Produit(designation, prix, quantite);
			p.setId(id);
			
			metier.updateProduit(p);
			
			//On stock un objet produit dont sa valeur est p dans l'objet request
			request.setAttribute("produit", p);
			
			 //Le contoleur Forward vers la vue confirmation 
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
			
			
		}
		else {
			response.sendError(Response.SC_NOT_FOUND);
		}
				
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
