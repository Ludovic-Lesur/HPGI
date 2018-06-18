/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 11/04/2016
 */

package data;

import typedef.*;

public class PointGeo {

	// Attributs
	private String nom;
	private double pk; // Distance au d�part (km).
	private int altitude;
	private Pause pause; // Temps de pause (heures).
	private double heure; // Heure d'arriv�e au point (ou heure de d�part si
							// point de d�part).
	private boolean ravitaillement; // Indique si un ravitaillement est pr�vu �
									// ce point.

	/**
	 * CONSTRUCTEUR SANS ARGUMENT DE LA CLASSE POINTGEO.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public PointGeo() {
		nom = "Inconnu";
		pk = 0.0;
		altitude = 0;
		pause = Pause.H0M0;
		heure = 0.0;
		ravitaillement = false;
	}

	/**
	 * CONSTRUCTEUR DE LA CLASSE POINTGEO.
	 * 
	 * @param pNom
	 *            Toponyme du point de type 'String'.
	 * @param pPk
	 *            Distance au d�part en kilom�tres, de type 'double'.
	 * @param pAltitude
	 *            Altitude du point en m�tres, de type 'int'.
	 * @param pPause
	 *            Temps de pause � ce point en heures, de type 'double'.
	 * @return Aucun.
	 */
	public PointGeo(String pNom, double pDistance, int pAltitude, Pause pPause) {
		nom = pNom;
		pk = pDistance;
		altitude = pAltitude;
		pause = pPause;
		heure = 0.0;
		ravitaillement = false;
	}

	/**
	 * CONSTRUCTEUR DE RECOPIE DE LA CLASSE POINTGEO.
	 * 
	 * @param p
	 *            Point � recopier, de type 'PointGeo'.
	 * @return Aucun.
	 */
	public PointGeo(PointGeo p) {
		nom = p.getNom();
		pk = p.getPK();
		altitude = p.getAltitude();
		pause = p.getPause();
		heure = p.getHeure();
		ravitaillement = p.getRavitaillement();
	}

	/**
	 * MODIFIE LE TOPONYME DU POINT.
	 * 
	 * @param newNom
	 *            Nouveau toponyme du point de type 'String'.
	 * @return Aucun.
	 */
	public void setNom(String newNom) {
		nom = newNom;
	}

	/**
	 * RENVOIE LE TOPONYME DU POINT.
	 * 
	 * @param Aucun.
	 * @return nom Toponyme du point de type 'String'.
	 */
	public String getNom() {
		String geek_name = nom.replace(" ", "_");
		geek_name = geek_name.replace("�", "e");
		geek_name = geek_name.replace("�", "e");
		geek_name = geek_name.replace("�", "a");
		geek_name = geek_name.replace("�", "u");
		return geek_name;
	}

	/**
	 * MODIFIE LE POINT KILOMETRIQUE DU POINT.
	 * 
	 * @param newPk
	 *            Nouveau point kilom�trique du point de type 'double'.
	 * @return Aucun.
	 */
	public void setPK(double newPk) {
		pk = newPk;
	}

	/**
	 * RENVOIE LE POINT KILOMETRIQUE DU POINT.
	 * 
	 * @param Aucun.
	 * @return pk Point kilom�trique du point de type 'double'.
	 */
	public double getPK() {
		return pk;
	}

	/**
	 * MODIFIE L'ALTITUDE DU POINT.
	 * 
	 * @param newAltitude
	 *            Nouvelle altitude du point de type 'int'.
	 * @return Aucun.
	 */
	public void setAltitude(int newAltitude) {
		altitude = newAltitude;
	}

	/**
	 * RENVOIE L'ALTITUDE DU POINT.
	 * 
	 * @param Aucun.
	 * @return altitude Altitude du point de type 'int'.
	 */
	public int getAltitude() {
		return altitude;
	}

	/**
	 * MODIFIE LE TEMPS DE PAUSE DU POINT.
	 * 
	 * @param newPause
	 *            Nouveau temps de pause au point de type 'double'.
	 * @return Aucun.
	 */
	public void setPause(Pause newPause) {
		pause = newPause;
	}

	/**
	 * RENVOIE LE TEMPS DE PAUSE DU POINT.
	 * 
	 * @param Aucun.
	 * @return pause Temps de pause au point de type 'double'.
	 */
	public Pause getPause() {
		return pause;
	}

	/**
	 * MODIFIE L'HEURE DU POINT.
	 * 
	 * @param newHeure
	 *            Nouvel horaire de type 'double'.
	 * @return Aucun.
	 */
	public void setHeure(double newHeure) {
		heure = newHeure;
	}

	/**
	 * RENVOIE L'HEURE DE PASSAGE AU POINT.
	 * 
	 * @param Aucun.
	 * @return heure Heure de passage de type 'double'.
	 */
	public double getHeure() {
		return heure;
	}

	/**
	 * MODIFIE LE RAVITAILLEMENT DU POINT.
	 * 
	 * @param newRavitaillement
	 *            Nouvelle information de ravitaillement de type 'boolean'.
	 * @return Aucun.
	 */
	public void setRavitaillement(boolean newRavitaillement) {
		ravitaillement = newRavitaillement;
	}

	/**
	 * RENVOIE L'INFORMATION DE RAVITAILLEMENT.
	 * 
	 * @param Aucun.
	 * @return ravitaillement Bool�en indiquant si un ravitaillement est pr�vu
	 *         au point.
	 */
	public boolean getRavitaillement() {
		return ravitaillement;
	}
}
