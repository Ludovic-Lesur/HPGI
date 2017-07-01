/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 11/04/2016
 */

package data;

import java.io.*;
import java.util.*;

public class Matlab {

	// Chemins absolus des dossiers du projet.
	public static final String OUTPUT_PATH = "C:/Users/Ludovic/Documents/Eclipse/Preparation_Rando/output/";
	public static final String MATLAB_PATH = "C:/Users/Ludovic/Documents/Eclipse/Preparation_Rando/matlab/";
	public static final String MATLAB_RANDO_PATH = "C:/Users/Ludovic/Documents/Eclipse/Preparation_Rando/matlab/randos/";

	/**
	 * CREE UN DOSSIER DE SORTIE POUR UNE NOUVELLE RANDONNEE.
	 * 
	 * @param r
	 *            : Randonn�e pour laquelle un dossier doit �tre cr��.
	 * @return Aucun.
	 */
	public static void creerDossierLatex(Rando r) {
		File dossier = new File(OUTPUT_PATH + r.getNom());
		dossier.mkdir();
	}

	/**
	 * CREE LE FICHIER MATLAB ASSOCIE A UNE RANDONNEE.
	 * 
	 * @param r
	 *            : Randonn�e pour laquelle le fichier Matlab doit �tre cr��.
	 * @return Aucun.
	 */
	public static void creerMatlab(Rando r) throws IOException {

		File txtMatlab = new File(MATLAB_RANDO_PATH + r.getNom() + "_Matlab.txt");
		FileWriter w = new FileWriter(txtMatlab);

		// Informations de la randonn�e.
		w.write(r.getNom() + "\r\n");
		w.write(Integer.toString(r.getNumEtapes()) + "\r\n");

		// Boucle des �tapes...
		Iterator<Etape> i = r.getTrace().iterator();
		while (i.hasNext()) {
			Etape e = i.next();

			// Informations de l'�tape.
			w.write("\\textbf{" + r.getNom() + "} $ \\qquad $ " + e.getNomMatlab() + "\r\n");
			if (e.getNumChemins() == 0) {
				w.write("0 ");
			} else {
				w.write(Integer.toString(e.getNumChemins() + 1) + " ");
			}
			w.write(Double.toString(e.getLongueur()) + " ");
			w.write(Integer.toString(e.getDenivelePos()) + " ");
			w.write(Integer.toString(e.getDeniveleNeg()) + "\r\n");

			int nbPoints = 0;

			// Boucle des points.
			Iterator<PointGeo> j = e.getPoints().iterator();
			while (j.hasNext()) {
				PointGeo p = j.next();
				nbPoints++;
				// Informations du point.
				// En t�te indiquant le lieu du repas de midi.
				if (p.getNom().compareTo(e.getLieuMidi()) == 0) {
					w.write("MIDI_");
				}
				// Toponyme.
				w.write("\\textit{" + p.getNom() + "}");
				// Petit d�jeuner.
				if (nbPoints == 1) {
					w.write("_\\textbf{(" + e.getDejeuner().getSymbol() + ")}");
				}
				// Repas de midi.
				if (p.getNom().compareTo(e.getLieuMidi()) == 0) {
					w.write("_\\textbf{(" + e.getMidi().getSymbol() + ")}");
				}
				// Ravitaillement.
				if (p.getNom().compareTo(e.getRavitaillement()) == 0) {
					w.write("_\\textbf{(RAV)}");
				}
				// Pause
				if (p.getPause().getDuree() > 0.0) {
					w.write("_(\\textbf{" + Temps.afficherHeure(p.getPause().getDuree()) + ")}");
				}
				// Repas du soir.
				if (nbPoints == e.getNumChemins() + 1) {
					w.write("_\\textbf{(" + e.getSoir().getSymbol() + ")_(" + e.getNuit().getSymbol() + ")}");
				}
				// Nuit.
				w.write(" " + Double.toString(p.getPK()) + " ");
				w.write(Integer.toString(p.getAltitude()) + " ");
				w.write(Temps.afficherHeure(p.getHeure()) + "\r\n");
			}
		}
		w.close();
	}
}
