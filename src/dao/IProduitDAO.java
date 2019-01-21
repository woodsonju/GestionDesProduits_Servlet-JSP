package dao;

import java.util.List;

import metier.entities.Produit;

public interface IProduitDAO {
	
	public Produit saveProduit(Produit p);
	public List<Produit> produitParMC(String mc);
	public Produit getProduit(Long id);
	public Produit updateProduit(Produit p);
	public void deleteProduit(Long id);

}
