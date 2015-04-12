package composant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * une Classe  de  gestion de base de donnée qui comprends
 * une classe d'implentation de base de donnée
 * des methodes d'implementation  des interrogations de la base 
 * 
 * 
 * @author komi Elom Heekpo
 * @version 2014
 * @since 1.0
 */
public class GestionnaireBD {
	//déclaration des variables des champs, des métadonnées de la base de données
	public static final String NOM_DE_BASE_DE_DONNEE = "base_D_Gsb" ;
	public static final int VERSION_BASE_DONNEE = 1 ;
	private static final String TABLE_VISITEUR = "Visiteur";
	private static final String COL_NOM_VISITEUR = "nom_visiteur" ;
	private static final String COL_PRENOM_VISITEUR = "prenom_visiteur" ;
	private static final String COL_ID_VISITEUR = "id_visiteur";
	private static final String COL_MOT_DE_PASS = "mot_de_pass";
	private static final String TABLE_FICHE_DE_FRAIS = "fiche_de_frais";
	private static final String  COL_ID_VISITEUR_FICHE_FRAIS = "id_visiteur_fiche_frais" ;
	private static final String COL_MOIS_FICHE_DE_FRAIS ="mois_fiche_frais";
	private static final String COL_ID_ETAT_FICHE_FRAIS = "id_etat_fiche_frais";
	private static final String COL_MONTANT_FICHE_FRAIS = "MONTANT_fiche_frais";
	private static final String  TABLE_LIGNE_FORFAIT = "ligne_forfait";
	private static final String COL_ID_VISITEUR_LIGNE_FORFAIT = "id_visiteur_ligne_forfait";
	private static final String  COL_MOIS_LIGNE_FORFAIT = "mois_frais_forfait" ;
	private static final String COL_ID_LIGNE_FORFAIT = "id__ligne_forfait";
	private static final String  COL_QTE_LIGNE_FRAIS = "qte_ligne_frais" ;
	private static final String TABLE_HORS_FORFAIT = "hors_forfait" ;
	private static final String COL_ID_VISITEUR_HORS_FORFAIT = "id_visiteur_hors_forfait";
	private static final String  COL_MOIS_HORS_FORFAIT = "mois_hors_forfait" ;
	private static final String COL_LIBELLE_HORS_FORFAIT = "libelle_hors_forfait";
	private static final String COL_MONTANT_HORS_FORFAIT = "montant_hors_forfait";
	private static final String COL_DATE_HORS_FORFAIT = "date_hors_forfait";
	private static final String TABLE_ETAT = "ETAT";
	private static final String COL_ID_ETAT = "id_etat";
	private static final String COL_LIBELLE_ETAT = "libelle_id" ;
	private static final String TABLE_FORFAIT = "FORFAIT";
	private static final String COL_ID_FORFAIT = "id_forfait";
	private static final String COL_LIBELLE_FORFAIT = "libellé_forfait";
	

	//Déclaration d'objet du contexte d'execution
	private Context ContexteDExecution ;
	//Déclaration de l'objet base de donnée
	private SQLiteDatabase requeteurBaseGsb ;
	
	 /**
     * contruction l'environnement d'execution  de la classe SystemeDeBaseDonnee
     * @param C :  un objet  contexte  faisant appel  au systeme de base de  donnée(Class appelant) 
     * @return le contexte d'éxécution du système de base de donnée
     */
	public GestionnaireBD(Context c) {
		ContexteDExecution  = c ;	
	}
	/**
	 * Implementation d'une Classe  de conception  et d'ouverture de la base de donée
	 * creation des tables et systeme de base de donnée
	 * 
	 *	 * 	 *  */
	
	 private static class ConnecteurBD extends SQLiteOpenHelper  {
		 /**
			 * contructeur de l'outil de creation de base de donnée
			 * @param Context :  un objet contexte d'execution faisant appel à la class
			 * definit le nom de la base de donnée,  et la version de la base de donnée
			 *	 * 	 *  */
		public ConnecteurBD(Context context) {
		     super(context, NOM_DE_BASE_DE_DONNEE , null , VERSION_BASE_DONNEE);
			// TODO Auto-generated constructor stub
		}
		/**
	     *  c'est methode d'execution sql de la creation de la base de donnée
	     *  cette méthode est la premiere méthode appellée après l'appel du contructeur
	     *   une nouvelle version est crée et écrase la base existante à chaque ouverture de la base de donnée
	     * @param db :  un objet SQLiteDatabase fournit à default par la class SQLiteDatabase
	     * @param oldVersion: le numero de l'ancienne version fournit à default par la class SQLiteDatabase,
	     * cette valeur par default null , ou est la valeur d'une version existante
	     * @param newVersion: le numero de la nouvelle version
	     * @return l'objet  db  :  db est  un objet de base base de donnée : 
	     */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL ("DROP TABLE IF EXISTS" + TABLE_VISITEUR) ;
			db.execSQL ("DROP TABLE IF EXISTS" + TABLE_FICHE_DE_FRAIS) ;
			db.execSQL ("DROP TABLE IF EXISTS" + TABLE_LIGNE_FORFAIT) ;
			db.execSQL ("DROP TABLE IF EXISTS" + TABLE_HORS_FORFAIT) ;
			db.execSQL ("DROP TABLE IF EXISTS" + TABLE_ETAT) ;
			db.execSQL ("DROP TABLE IF EXISTS" + TABLE_FORFAIT) ;
			this.onCreate(db);			
		}
		/**
	     *  la methode d'execution sql de la creation  des tables de la base de donnée 
	     * @param db :  db est un objet base de donnée  récemment crée
	     * @return return true si l' execution correcte
	     * 
	     */
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			/** sql de creation des tables  avec la méthode execSQL de l'objet db
			 * 
			 */
			db.execSQL(" CREATE TABLE " + TABLE_VISITEUR + " (" + COL_ID_VISITEUR + " TEXT NOT NULL , " + COL_PRENOM_VISITEUR + " TEXT NOT NULL , " 
			 + COL_NOM_VISITEUR + " TEXT NOT NULL , " 
		     +  COL_MOT_DE_PASS + " TEXT NOT NULL);" );
			 
			db.execSQL(" CREATE TABLE " + TABLE_FICHE_DE_FRAIS +
					" (" + COL_ID_VISITEUR_FICHE_FRAIS + " TEXT NOT NULL , " + COL_MOIS_FICHE_DE_FRAIS + " INTEGER NOT NULL , "
					 + COL_ID_ETAT_FICHE_FRAIS + " TEXT NOT NULL , "
					 + COL_MONTANT_FICHE_FRAIS + " INTEGER NULL);" );
			
			db.execSQL(" CREATE TABLE " + TABLE_LIGNE_FORFAIT +
					"(" + COL_ID_VISITEUR_LIGNE_FORFAIT + " TEXT NOT NULL , "
					+ COL_MOIS_LIGNE_FORFAIT + " INTEGER NOT NULL , " 
					+ COL_QTE_LIGNE_FRAIS + " NULL);" );
			db.execSQL(" CREATE TABLE " + TABLE_HORS_FORFAIT +
					  "(" + COL_ID_VISITEUR_HORS_FORFAIT + " TEXT NOT NULL , "
					  + COL_MOIS_HORS_FORFAIT + " INTEGER NOT NULL , " 
					  + COL_LIBELLE_HORS_FORFAIT + " TEXT NOT NULL , "
					  + COL_MONTANT_HORS_FORFAIT + " INTEGER NULL , "
					  + COL_DATE_HORS_FORFAIT + " INTEGER NOT NULL);" );
			db.execSQL(" CREATE TABLE " + TABLE_ETAT + "(" + COL_ID_ETAT + " TEXT NOT NULL , "
					 + COL_LIBELLE_ETAT + " TEXT NOT NULL);" ) ;	
			db.execSQL(" CREATE TABLE " + TABLE_FORFAIT + "(" + COL_ID_FORFAIT + " TEXT NOT NULL , "
					  + COL_LIBELLE_FORFAIT + " TEXT NOT NULL);" ) ;
			db.execSQL("") ;
			
			
		}


		 	 
	 }
	
	 /**
	  *  Declaration  'un objet de la class OutilDeDeBaseDeDonnee
	  */
	 private ConnecteurBD  NotreOutilBD  ;
	 
	 /**
	  * méthode d'ouverture du système de Base de donnee
	  *  instanciation de l'objet NotreOutilBD
	  *  retourne la base de donnée en lecture
	  *  */
	 public GestionnaireBD ouvrir () throws SQLException  {
		 NotreOutilBD = new ConnecteurBD (ContexteDExecution ) ;
		 requeteurBaseGsb =  NotreOutilBD.getWritableDatabase();
		 return  this ;
		 
	 }
	 /**
	  * méthode de fin de connection de avec de système base
	  * @return la  fermeture du systeme de Base de donnée
	  */
	public void fermer () {
		NotreOutilBD.close();
		
	}

	
	/** méthode d'inscription des visiteur
	 * @param id_visit : id du visiteur 
	 * @param String prenom_visit : prenom  du visiteur
	 * @param String nom_visit : le nom du visiteur
	 * return vrai si  la méthode insert de l'objet gSbBaseDeDonnee 
	 */
	public long inscriptionVisiteur (String id_visit , String prenom_visit , 
		String nom_visit,  String mot_de_pass_visit ) {
		ContentValues conteneurDeValeur ;
	    conteneurDeValeur = new ContentValues();
		conteneurDeValeur.put(COL_ID_VISITEUR, id_visit);
		conteneurDeValeur.put(COL_PRENOM_VISITEUR, prenom_visit);
		conteneurDeValeur.put(COL_NOM_VISITEUR, nom_visit);
		conteneurDeValeur.put(COL_MOT_DE_PASS, mot_de_pass_visit);
		
		return 	requeteurBaseGsb.insert(TABLE_VISITEUR,null, conteneurDeValeur);
	}
	public String [] SeConnecter  (String leIdSaisi, String leMotSaisi) throws SQLException {
		// TODO Auto-generated method stub
	 String selectionColonne [] = new String [] {COL_ID_VISITEUR , COL_MOT_DE_PASS , COL_NOM_VISITEUR};
	 Cursor curseurDelecture  = requeteurBaseGsb.query(TABLE_VISITEUR, selectionColonne, COL_ID_VISITEUR + " = '" + leIdSaisi + "'", null, null, null, null);
	if (curseurDelecture  != null ) {	
	     curseurDelecture.moveToFirst();
		String retourpass =  curseurDelecture .getString(1);
		if (retourpass.contentEquals(leMotSaisi)) {
				 String lenomVisteur = curseurDelecture.getString(2);
				 String lesIdentites [] = { lenomVisteur, leIdSaisi};
				 curseurDelecture.close();
			return lesIdentites ;
		 }
	}		
		 curseurDelecture.close();
		return null   ;
		  
	}
	public long enregistrerForfait(String leIDSaisi, String leMoisSaisi,
			String leKmSaisi, String leSejourSaisi, String leRepasSaisi, String leEtapeSaisi) throws SQLException {
       ContentValues conteneurDeValeurFiche = new ContentValues () ;
       conteneurDeValeurFiche.put(COL_ID_VISITEUR_FICHE_FRAIS, leIDSaisi);
       conteneurDeValeurFiche.put(COL_MOIS_FICHE_DE_FRAIS, leMoisSaisi);
       conteneurDeValeurFiche.put(COL_ID_ETAT_FICHE_FRAIS, "CR") ;
    	   // requeteurBaseGsb.insert(TABLE_FICHE_DE_FRAIS, null, conteneurDeValeurFiche);     
    	 ContentValues   conteneurDeValeurFraisKm = new ContentValues ();
    	    conteneurDeValeurFraisKm.put(COL_ID_LIGNE_FORFAIT, "KM");
    	    conteneurDeValeurFraisKm.put(COL_ID_VISITEUR_LIGNE_FORFAIT,leIDSaisi );
    	    conteneurDeValeurFraisKm.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi);
    	    conteneurDeValeurFraisKm.put(COL_QTE_LIGNE_FRAIS, leKmSaisi);
    	 // requeteurBaseGsb.insert(TABLE_LIGNE_FORFAIT, null, conteneurDeValeurFraisKm);
    	  ContentValues conteneurDeValeurSejour =  new ContentValues ();
    	  conteneurDeValeurSejour.put(COL_ID_LIGNE_FORFAIT, "NUI") ;
    	  conteneurDeValeurSejour.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi);
    	  conteneurDeValeurSejour.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi);
    	  conteneurDeValeurSejour.put(COL_QTE_LIGNE_FRAIS, leSejourSaisi);
    	  //requeteurBaseGsb.insert(TABLE_LIGNE_FORFAIT, null, conteneurDeValeurSejour);
    	  ContentValues conteneurDeValeurRepas = new ContentValues ();
    	  conteneurDeValeurRepas.put(COL_ID_LIGNE_FORFAIT, "REP");
    	  conteneurDeValeurRepas.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi);
    	  conteneurDeValeurRepas.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi);
    	  conteneurDeValeurRepas.put(COL_QTE_LIGNE_FRAIS, leRepasSaisi);
    	 // requeteurBaseGsb.insert(TABLE_LIGNE_FORFAIT, null, conteneurDeValeurRepas);
    	  ContentValues conteneurDeValeurEtape = new ContentValues () ;
    	  conteneurDeValeurEtape.put(COL_ID_LIGNE_FORFAIT, "ETP");
    	  conteneurDeValeurEtape.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi);
    	  conteneurDeValeurEtape.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi);
    	  conteneurDeValeurEtape.put(COL_QTE_LIGNE_FRAIS, leEtapeSaisi);
    	 // requeteurBaseGsb.insert(TABLE_LIGNE_FORFAIT, null, conteneurDeValeurEtape);
    	  
    	  
    	    
    
    	    
    	    return 0 ;
		// TODO Auto-generated method stub
	
		
	}

	
}
