package composant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * une Classe  de  gestion de base de donnée qui comprends
 * une classe d'implentation de base de donnée
 * des methodes d'implementation  des interrogation de la base 
 * 
 * 
 * @author komi Elom Heekpo
 * @version 2014
 * @since 1.0
 */
public class SystemeDeBaseDonnee {
	//déclaration des variables des champs, des métadonnées de la base de données
	public static final String NOM_DE_BASE_DE_DONNEE = "base_D_Gsb" ;
	public static final int VERSION_BASE_DONNEE = 1 ;
	private static final String TABLE_VISITEUR = "Visiteur";
	private static final String COL_NOM_VISITEUR = "nom_visiteur" ;
	private static final String COL_PRENOM_VISITEUR = "prenom_visiteur" ;
	private static final String COL_ID_VISITEUR = "id_visiteur";
	private static final String COL_MOT_DE_PASS = "mot_de_pass";
	private static final String TABLE_FICHE_DE_FRAIS = "fiche_de_frais";
	private static final String  COL_ID_VISITEUR_FICHE_FRAIS= "id_visiteur_fiche_frais" ;
	private static final String COL_MOIS_FICHE_DE_FRAIS ="mois_fiche_frais";
	private static final String COL_ID_ETAT_FICHE_FRAIS = "id_etat_fiche_frais";
	private static final String COL_MONTANT_FICHE_FRAIS = "MONTANT_fiche_frais";
	private static final String  TABLE_LIGNE_FORFAIT = "ligne_forfait";
	private static final String COL_ID_VISITEUR_LIGNE_FORFAIT = "id_visiteur_ligne_forfait";
	private static final String  COL_MOIS_LIGNE_FORFAIT = "mois_frais_forfait" ;
	private static final String COL_ID_LIGNE_FORFAIT ="id__ligne_forfait";
	private static final String  COL_QTE_LIGNE_FRAIS ="qte_ligne_frais" ;
	private static final String TABLE_HORS_FORFAIT ="hors_forfait" ;
	private static final String COL_ID_VISITEUR_HORS_FORFAIT = "id_visiteur_hors_forfait";
	private static final String  COL_MOIS_HORS_FORFAIT = "mois_hors_forfait" ;
	private static final String COL_LIBELLE_HORS_FORFAIT="libelle_hors_forfait";
	private static final String COL_MONTANT_HORS_FORFAIT="montant_hors_forfait";
	private static final String COL_DATE_HORS_FORFAIT ="date_hors_forfait";
	private static final String TABLE_ETAT ="ETAT";
	private static final String COL_ID_ETAT = "id_etat";
	private static final String TABLE_FORFAIT ="FORFAIT";
	//
	//
	//Déclaration d'objet du contexte d'execution
	private Context ContexteDExecution ;
	//Déclaration de l'objet base de donnée
	private SQLiteDatabase gSbBaseDeDonnee ;
	
	 /**
     * contruction l'environnement d'execution  de la classe SystemeDeBaseDonnee
     * @param C :  le contexte  faisant appel  au systeme de base de  donnée(Class appelant) 
     * @return le contexte d'éxécution du système de base de donnée
     */
	public SystemeDeBaseDonnee(Context c) {
		ContexteDExecution  = c ;	
	}
	/**
	 * Implementation d'une Classe  de conception de la base de donée
	 * creation des tables et ysteme de base de donnée
	 * 
	 *	 * 	 *  */
	
	
	 private static class OutilDeCreationDeBaseDeDonnee
	
	

}
