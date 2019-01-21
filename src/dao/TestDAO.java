package dao;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Produit;

public class TestDAO {

	public static void main(String[] args) {
		
		ProduitDAOImpl dao = new ProduitDAOImpl();
		Produit p1 = dao.saveProduit(new Produit("HP 650", 900, 5));
		Produit p2 = dao.saveProduit(new Produit("Imprimante Epson 760", 1000, 15));
		System.out.println(p1);
		System.out.println(p2);
		
		System.out.println("Rechercher des Produits");
		List<Produit> produits = new ArrayList<Produit>();
		produits = dao.produitParMC("%HP%");
		for(Produit p: produits)
		{
			System.out.println(p);
		}
	}

}
