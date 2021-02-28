/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 11/04/2016
 */

package data;

import java.text.*;
import java.util.*;
import typedef.*;

public class Etape {

	// Attributs sources.
	private Heure heureDepart;
	private int lieuRavitaillement;
	private Condition dejeuner;
	private int lieuMidi;
	private Condition midi;
	private Condition soir;
	private Condition nuit;
	private Vector<Chemin> itineraire;

	// Attributs deduits.
	private Vector<PointGeo> listePoints;
	private int numero;
	private String nom;
	private PointGeo depart;
	private PointGeo arrivee;
	private double longueur;
	private double temps;
	private int denivelePos;
	private int deniveleNeg;

	/**
	 * CONSTRUCTEUR DE LA CLASSE ETAPE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public Etape() {
		// Attributs sources.
		heureDepart = Heure.H7M0;
		lieuRavitaillement = 0;
		dejeuner = Condition.R;
		midi = Condition.R;
		lieuMidi = 0;
		soir = Condition.R;
		nuit = Condition.R;
		itineraire = new Vector<Chemin>();
		depart = new PointGeo();
		arrivee = new PointGeo();
		// Attributs deduits.
		listePoints = new Vector<PointGeo>();
		numero = -1;
		nom = "Inconnu";
		longueur = 0.0;
		temps = 0.0;
		denivelePos = 0;
		deniveleNeg = 0;
	}

	/**
	 * AJOUTE UN POINT A L'ETAPE.
	 * 
	 * @param newPoint
	 *            Point a ajouter de type 'Point'.
	 * @return Aucun.
	 */
	public void ajouterPoint(PointGeo newPoint) {
		listePoints.addElement(newPoint);
	}

	/**
	 * AJOUTE UN CHEMIN A L'ETAPE.
	 * 
	 * @param newChemin
	 *            Chemin a ajouter de type 'Chemin'.
	 * @return Aucun.
	 */
	public void ajouterChemin(Chemin newChemin) {
		itineraire.addElement(newChemin);
		// Mise a jour du depart et de l'arrivee.
		depart = itineraire.firstElement().getDebut();
		arrivee = itineraire.lastElement().getFin();
		// Mise a jour des stats de l'etape.
		longueur = longueur + (newChemin.getLongueur());
		temps = temps + (newChemin.getTemps());
		int newDeniv = newChemin.getDenivele();
		if (newDeniv > 0) {
			denivelePos = denivelePos + newDeniv;
		} else {
			deniveleNeg = deniveleNeg - newDeniv;
		}
		// Mise a jour du nom de l'etape.
		nom = "\\textbf{ETAPE " + numero + "} $ \\qquad $ " + depart.getNom() + " $ \\ \\rightarrow \\ $ "
				+ arrivee.getNom();
	}

	/**
	 * RECALCULE TOUS LES HORAIRES DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void majHoraire() {
		double heure = heureDepart.getHeure();
		if (listePoints.isEmpty() == false) {
			listePoints.firstElement().setHeure(heure);
		}
		Iterator<Chemin> i = itineraire.iterator();
		int j = 0;
		while (i.hasNext()) {
			Chemin c = i.next();
			c.setHeureDebut(heure);
			c.setHeureFin(heure + c.getTemps());
			heure = c.getHeureFin();
			j++;
			if (listePoints.isEmpty() == false) {
				listePoints.elementAt(j).setHeure(heure);
			}
		}
	}

	/**
	 * RETOURNE LA LISTE DE TOUS LES POINTS CONSTITUANT L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return listePoints Liste de tous les points de type Vector<Point>.
	 */
	public Vector<PointGeo> getPoints() {
		return listePoints;
	}

	/**
	 * TESTE SI UN POINT KILOMETRIQUE EST PRESENT DANS L'ETAPE.
	 * 
	 * @param pk
	 *            Point kilometrique a tester de type 'double'.
	 * @return existe Booleen indiquant si le PK passe en parametre existe deja.
	 */
	public boolean existeDeja(double pk) {
		boolean existe = false;
		double pkCourant;
		Vector<PointGeo> listePoints = this.getPoints();
		Iterator<PointGeo> j = listePoints.iterator();
		while (j.hasNext()) {
			pkCourant = j.next().getPK();
			if (pkCourant == pk) {
				existe = true;
				break;
			}
		}
		return existe;
	}

	/**
	 * RETOURNE LA LISTE DES CHEMINS COMPOSANT L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return itineraire Liste des chemins de l'etape de type Vector<Chemin>.
	 */
	public Vector<Chemin> getItineraire() {
		return itineraire;
	}

	/**
	 * MODIFIE L'HEURE DE DEPART DE L'ETAPE.
	 * 
	 * @param newHeure
	 *            Nouvelle heure de depart de type 'Heure'.
	 * @return Aucun.
	 */
	public void setHeureDepart(Heure newHeure) {
		heureDepart = newHeure;
	}

	/**
	 * RETOURNE L'HEURE DE DEPART DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return heureDepart Heure de depart de type 'Heure'.
	 */
	public Heure getHeureDepart() {
		return heureDepart;
	}

	/**
	 * MODIFIE LE LIEU DE RAVITAILLEMENT DE L'ETAPE.
	 * 
	 * @param newRavitaillement
	 *            Nouveau lieu de ravitaillement de type 'String'.
	 * @return Aucun.
	 */
	public void setRavitaillement(int newRavitaillement) {
		lieuRavitaillement = newRavitaillement;
	}

	/**
	 * RETOURNE LE LIEU DE RAVITAILLEMENT DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return lieuRavitaillement Lieu de ravitaillement de type 'PointGeo'.
	 */
	public int getRavitaillement() {
		return lieuRavitaillement;
	}

	/**
	 * MODIFIE LE TYPE DE PETIT-DEJEUNER DE L'ETAPE.
	 * 
	 * @param String
	 *            Nouveau type de petit-dejeuner de type 'PointGeo'.
	 * @return Aucun.
	 */
	public void setDejeuner(String newDejeuner) {
		dejeuner = Condition.affecter(newDejeuner);
	}

	/**
	 * RETOURNE LE TYPE DE PETIT-DEJEUNER DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return dejeuner Type de petit-dejeuner de l'etape, de type 'Condition'.
	 */
	public Condition getDejeuner() {
		return dejeuner;
	}

	/**
	 * MODIFIE LE LIEU DU REPAS DE MIDI DE L'ETAPE.
	 * 
	 * @param newLieuMidi
	 *            Nouveau lieu du repas de midi, de type 'PointGeo'.
	 * @return Aucun.
	 */
	public void setLieuMidi(int newLieuMidi) {
		lieuMidi = newLieuMidi;
	}

	/**
	 * RETOURNE LE LIEU DU REPAS DE MIDI DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return lieuMidi Lieu du repas de midi de type 'PointGeo'.
	 */
	public int getLieuMidi() {
		return lieuMidi;
	}

	/**
	 * MODIFIE LE TYPE DE REPAS DU MIDI DE L'ETAPE.
	 * 
	 * @param newMidi
	 *            Nouveau type du repas de midi, de type 'String'.
	 * @return Aucun.
	 */
	public void setMidi(String newMidi) {
		midi = Condition.affecter(newMidi);
	}

	/**
	 * RETOURNE LE TYPE DE REPAS DU MIDI DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return midi Type du repas de midi de type 'Condition'.
	 */
	public Condition getMidi() {
		return midi;
	}

	/**
	 * MODIFIE LE TYPE DE REPAS DU SOIR DE L'ETAPE.
	 * 
	 * @param newSoir
	 *            Nouveau type du repas de soir, de type 'String'.
	 * @return Aucun.
	 */
	public void setSoir(String newSoir) {
		soir = Condition.affecter(newSoir);
	}

	/**
	 * RETOURNE LE TYPE DE REPAS DU SOIR DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return soir Type du repas de soir de type 'Condition'.
	 */
	public Condition getSoir() {
		return soir;
	}

	/**
	 * MODIFIE LE TYPE DE NUIT DE L'ETAPE.
	 * 
	 * @param newNuit
	 *            Nouveau type de nuit, de type 'String'.
	 * @return Aucun.
	 */
	public void setNuit(String newNuit) {
		nuit = Condition.affecter(newNuit);
	}

	/**
	 * RETOURNE LE TYPE DE NUIT DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return nuit Type de nuit de type 'Condition'.
	 */
	public Condition getNuit() {
		return nuit;
	}

	/**
	 * RETOURNE LE DENIVELE POSITIF CUMULE DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return denivelePos Denivele positif cumule en metres, de type 'int'.
	 */
	public int getDenivelePos() {
		return denivelePos;
	}

	/**
	 * RETOURNE LE DENIVELE NEGATIF CUMULE DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return deniveleNeg Denivele negatif cumule en metres, de type 'int'.
	 */
	public int getDeniveleNeg() {
		return deniveleNeg;
	}

	/**
	 * RETOURNE L'ALTITUDE MINIMUM DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return altitudeMin Altitude minimum en metres, de type 'int'.
	 */
	public int getAltitudeMin() {
		int altitudeMin = Integer.MAX_VALUE;
		Iterator<Chemin> itChemins = itineraire.iterator();
		while (itChemins.hasNext()) {
			Chemin cheminCourant = itChemins.next();
			if (cheminCourant.getAltitudeMin() < altitudeMin) {
				altitudeMin = cheminCourant.getAltitudeMin();
			}
		}
		return altitudeMin;
	}

	/**
	 * RETOURNE L'ALTITUDE MAXIMUM DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return altitudeMax Altitude maximum en metres, de type 'int'.
	 */
	public int getAltitudeMax() {
		int altitudeMax = Integer.MIN_VALUE;
		Iterator<Chemin> itChemins = itineraire.iterator();
		while (itChemins.hasNext()) {
			Chemin cheminCourant = itChemins.next();
			if (cheminCourant.getAltitudeMax() > altitudeMax) {
				altitudeMax = cheminCourant.getAltitudeMax();
			}
		}
		return altitudeMax;
	}

	/**
	 * RETOURNE LE NOM DE L'ETAPE FORMATEE POUR MATLAB.
	 * 
	 * @param Aucun.
	 * @return nom Nom de l'etape de type 'String'.
	 */
	public String getNomMatlab() {
		nom = "\\textbf{ETAPE " + numero + "} $ \\qquad $ " + depart.getNom() + " $ \\ \\rightarrow \\ $ "
				+ arrivee.getNom();
		return nom;
	}

	/**
	 * RETOURNE LE NOM DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return nom Nom de l'etape de type 'String'.
	 */
	public String getNom() {
		return (depart.getNom() + " -> " + arrivee.getNom());
	}

	/**
	 * RETOURNE LE NUMERO DE L'ETAPE AU SEIN DE LA RANDONNEE.
	 * 
	 * @param Aucun.
	 * @return numero Numero de l'etape de type 'int'.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * MODIFIE LE NUMERO DE L'ETAPE AU SEIN DE LA RANDONNEE.
	 * 
	 * @param newNumero
	 *            Nouveau numero de l'etape de type 'int'.
	 * @return Aucun.
	 */
	public void setNumero(int newNumero) {
		numero = newNumero;
	}

	/**
	 * RETOURNE LE POINT DE DEPART DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return depart Point de depart de type 'PointGeo'.
	 */
	public PointGeo getDepart() {
		return depart;
	}

	/**
	 * RETOURNE LE POINT D'ARRIVEE DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return depart Point d'arrivee de type 'PointGeo'.
	 */
	public PointGeo getArrivee() {
		return arrivee;
	}

	/**
	 * RETOURNE LA LONGUEUR DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return longueur Longueur de l'etape en kilometres, de type 'double'.
	 */
	public double getLongueur() {
		DecimalFormat df = new DecimalFormat("#.##");
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		longueur = Double.valueOf(df.format(longueur));
		return longueur;
	}

	/**
	 * RETOURNE LA DUREE THEORIQUE DE L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return temps Duree theorique en heures, de type 'double'.
	 */
	public double getTemps() {
		return temps;
	}

	/**
	 * RETOURNE LE NOMBRE DE CHEMINS UNITAIRES DECRIVANT L'ETAPE.
	 * 
	 * @param Aucun.
	 * @return Nombre de chemins unitaires de type 'int'.
	 */
	public int getNumChemins() {
		return itineraire.size();
	}
}
