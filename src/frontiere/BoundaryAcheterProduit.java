package frontiere;

import controleur.ControlAcheterProduit;
import villagegaulois.Etal;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		String produit = Clavier.entrerChaine("Quel produit voulez vous acheter ?");
		Etal[] etal = controlAcheterProduit.trouverEtalProduit(produit);
		if (etal == null) {
			System.out.println("Désolé, personne ne vend ce produit au marché.");
			return;
		}	
		StringBuilder chaine = new StringBuilder();
		chaine.append("Chez quel commerçant voulez-vous acheter des fleurs ?");
		for(int i = 0; i<etal.length; i++) {
			chaine.append("\n" + (i+1) + " - " + etal[i].getVendeur().getNom());
		}
		
		int numeroEtal = Clavier.entrerEntier(chaine.toString()) - 1;
		while (numeroEtal < 0 || numeroEtal > etal.length) {
			numeroEtal = Clavier.entrerEntier(chaine.toString());
		}
		
		String nomVendeur = etal[numeroEtal].getVendeur().getNom();
		
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " +
				nomVendeur);
		System.out.println("Bonjour " + nomAcheteur);
		int quantiteAchat = Clavier.entrerEntier("Combien de " + produit +
				" voulez-vous acheter ?");
		int quantiteVente = etal[numeroEtal].getQuantite();
		if (quantiteVente == 0) {
			System.out.println(nomAcheteur + " veut acheter " + quantiteAchat +
					produit + ", malheureusement il n'y en a plus !");
		} else if (quantiteAchat > quantiteVente ) {
			System.out.println(nomAcheteur + " veut acheter " + quantiteAchat +
					produit + ", malheureusement " + nomVendeur + " n'en a " +
					"plus que " + quantiteVente + ". " + nomAcheteur + " achète tout le stock de " + nomVendeur);
			controlAcheterProduit.acheterProduit(nomVendeur, quantiteVente);
		} else {
			System.out.println(nomAcheteur + " achète " + quantiteAchat + " " + produit + " à " + nomVendeur);
			controlAcheterProduit.acheterProduit(nomVendeur, quantiteAchat);
		}
	}
}
