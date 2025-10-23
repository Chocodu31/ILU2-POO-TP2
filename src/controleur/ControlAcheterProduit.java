package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}
	
	public Boolean verifierIdentite(String nomVendeur) {
		return controlVerifierIdentite.verifierIdentite(nomVendeur);
	}
	
	public Etal[] trouverEtalProduit(String produit) {
		Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);
		if (vendeurs == null) {
			return new Etal[0];
		} else {
			Etal[] etal = new Etal[vendeurs.length];
			for (int i = 0; i<vendeurs.length; i++) {
				etal[i] = village.rechercherEtal(vendeurs[i]);
			}
			return etal;
		}
		
	}
	
	public int acheterProduit(String nomVendeur, int quantiteAcheter) {
		Gaulois vendeur = village.trouverHabitant(nomVendeur);
		if (vendeur != null) {
			Etal etal = village.rechercherEtal(vendeur);
			if (etal != null) {
				return etal.acheterProduit(quantiteAcheter);
			}
		}
		return 0; 
	}
}
