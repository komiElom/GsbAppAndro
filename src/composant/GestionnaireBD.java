package composant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *  * 
 * @author komi Elom Heekpo
 * * @since 1.0
 * <p> une Classe  de  gestion de base de donnée qui comprends:
 * <p>  - une classe d'implentation de base de donnée
 * <p> -  des methodes d'implementation  des interrogations de la base 
 */
public class GestionnaireBD {
	//déclaration des variables des champs, des métadonnées de la base de données
	public static final String NOM_DE_BASE_DE_DONNEE = "base_D_Gsb" ;
	public static final int VERSION_BASE_DONNEE = 3 ;
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
	private static final String  COL_NUM_HORS_FORFAIT = "numero_hors_forfait" ;
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
			 * <p> definit le nom de la base de donnée,  et la version de la base de donnée
			 *	 * 	 *  */
		public ConnecteurBD(Context context) {
		     super(context, NOM_DE_BASE_DE_DONNEE , null , VERSION_BASE_DONNEE);
			// TODO Auto-generated constructor stub
		}
		/**
	     *  <p> c'est methode d'execution sql de la creation de la base de donnée
	     * <p> cette méthode est la premiere méthode appellée après l'appel du contructeur
	     * <p> lorsqu'une nouvelle version de la base de donnée est indiquée
	     * <p> celle ci  écrase la base existante 
	     * @param db :  un objet SQLiteDatabase fournit à default par la class SQLiteDatabase
	     * @param oldVersion: le numero de l'ancienne version fournit à default par la class SQLiteDatabase,
	     * cette valeur par default null , ou est la valeur d'une version existante
	     * @param newVersion: le numero de la nouvelle version
	     * @return l'objet  db  :  db est  un objet de base base de donnée : 
	     */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			 if(newVersion > oldVersion) {
				 //db.execSQL("ALTER TABLE " +  TABLE_LIGNE_FORFAIT + 		 
					//	 " ADD COLUMN " + COL_ID_LIGNE_FORFAIT + " TEXT NOT NULL  DEFAULT mon_id_default ; " ) ;
				// db.execSQL("ALTER TABLE  " + TABLE_HORS_FORFAIT +
				   //     " ADD COLUMN " +  COL_NUM_HORS_FORFAIT + " INTEGER PRIMARY KEY AUTOINCREMENT ; " ) ;
				// db.execSQL("DROP TABLE IF EXISTS "  + TABLE_HORS_FORFAIT ) ;
				//	db.execSQL(" CREATE TABLE " + TABLE_HORS_FORFAIT +
				 //   "(" + COL_ID_VISITEUR_HORS_FORFAIT + " TEXT NOT NULL , "
						//		 + COL_MOIS_HORS_FORFAIT + " INTEGER NOT NULL , " 
							//	 + COL_LIBELLE_HORS_FORFAIT + " TEXT NOT NULL , "
							// + COL_MONTANT_HORS_FORFAIT + " INTEGER NULL , "
						//		 + COL_DATE_HORS_FORFAIT + " INTEGER NOT NULL , " 
							// + COL_NUM_HORS_FORFAIT + " INTEGER PRIMARY KEY AUTOINCREMENT );" );
			 }
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
			/** <p> sql de creation des tables  avec la méthode execSQL de l'objet db
			 * 
			 */
			/**
			 * <p> la première version créee   ne sera jamais prise en compte 
			 */
			boolean premierVersion = false;
			if (premierVersion  == true ){
			//db.execSQL(" CREATE TABLE " + TABLE_VISITEUR + " (" + COL_ID_VISITEUR + " TEXT NOT NULL , " + COL_PRENOM_VISITEUR + " TEXT NOT NULL , " 
			// + COL_NOM_VISITEUR + " TEXT NOT NULL , " 
		   //  +  COL_MOT_DE_PASS + " TEXT NOT NULL);" );
			 
		//	db.execSQL(" CREATE TABLE " + TABLE_FICHE_DE_FRAIS +
			//		" (" + COL_ID_VISITEUR_FICHE_FRAIS + " TEXT NOT NULL , " + COL_MOIS_FICHE_DE_FRAIS + " INTEGER NOT NULL , "
				//	 + COL_ID_ETAT_FICHE_FRAIS + " TEXT NOT NULL , "
					// + COL_MONTANT_FICHE_FRAIS + " INTEGER NULL);" );
			
			//db.execSQL(" CREATE TABLE " + TABLE_LIGNE_FORFAIT +
			////		"(" + COL_ID_VISITEUR_LIGNE_FORFAIT + " TEXT NOT NULL , "
				//	+ COL_MOIS_LIGNE_FORFAIT + " INTEGER NOT NULL , " 
				//	+ COL_QTE_LIGNE_FRAIS + " NULL);" );
	//	db.execSQL(" CREATE TABLE " + TABLE_HORS_FORFAIT +
		//		  "(" + COL_ID_VISITEUR_HORS_FORFAIT + " TEXT NOT NULL , "
		//			 + COL_MOIS_HORS_FORFAIT + " INTEGER NOT NULL , " 
			//		 + COL_LIBELLE_HORS_FORFAIT + " TEXT NOT NULL , "
			//	 + COL_MONTANT_HORS_FORFAIT + " INTEGER NULL , "
			//		 + COL_DATE_HORS_FORFAIT + " INTEGER NOT NULL , " 
			//	 + COL_NUM_HORS_FORFAIT + " INTEGER PRIMARY KEY AUTOINCREMENT );" );
		             
		//	db.execSQL(" CREATE TABLE " + TABLE_ETAT + "(" + COL_ID_ETAT + " TEXT NOT NULL , "
				//	 + COL_LIBELLE_ETAT + " TEXT NOT NULL);" ) ;	
			//db.execSQL(" CREATE TABLE " + TABLE_FORFAIT + "(" + COL_ID_FORFAIT + " TEXT NOT NULL , "
				//	  + COL_LIBELLE_FORFAIT + " TEXT NOT NULL);" ) ;
			
			}
				}


		 	 
	 }
	
	 /**
	  * <p>  Declaration  'un objet de la class ConnecteurBD 
	  */
	 private ConnecteurBD  NotreOutilBD  ;
	 
	 /**
	  * <p> méthode d'ouverture du système de Base de donnee
	  * <p> instanciation de l'objet ConnecteurBD 
	  *  @return retourne la base de donnée en lecture
	  *  */
	 public GestionnaireBD ouvrir () throws SQLException  {
		 NotreOutilBD = new ConnecteurBD (ContexteDExecution ) ;
		 requeteurBaseGsb =  NotreOutilBD.getWritableDatabase();
		 return  this ;
		 
	 }
	 /**
	  * <p> méthode de fin de connection de avec de système base
	  * @return la  fermeture du systeme de Base de donnée
	  */
	public void fermer () {
		NotreOutilBD.close();
		
	}

	
	/** méthode d'inscription des visiteur
	 * @param id_visit : id du visiteur 
	 * @param String prenom_visit : prenom  du visiteur
	 * @param String nom_visit : le nom du visiteur
	 * @return vrai si  la méthode insert de l'objet requeteurBaseGsb
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
	/**
	 * <p> methode permettant l'authentification de l'utilisateur
	 * @param leIdSaisi : id saisi
	 * @param leMotSaisi: mot de pass saisi
	 * @return tableau lesIdentites [] si requete sql a un succes , sinon null
	 * @throws SQLException
	 */
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
	/**
	 * <p> methode permettant d'enregistrer les elements de frais forfait
	 * @param leIDSaisi
	 * @param leMoisSaisi
	 * @param leKmSaisi
	 * @param leSejourSaisi
	 * @param leRepasSaisi
	 * @param leEtapeSaisi
	 * @return long 1 
	 * @throws SQLException
	 */
	public long enregistrerForfait(String leIDSaisi, String leMoisSaisi,
			String leKmSaisi, String leSejourSaisi, String leRepasSaisi, String leEtapeSaisi) throws SQLException {
     
	   ContentValues conteneurDeValeurFiche = new ContentValues () ;
       conteneurDeValeurFiche.put(COL_ID_VISITEUR_FICHE_FRAIS, leIDSaisi);
       conteneurDeValeurFiche.put(COL_MOIS_FICHE_DE_FRAIS, leMoisSaisi);
       conteneurDeValeurFiche.put(COL_ID_ETAT_FICHE_FRAIS, "CR") ;
      requeteurBaseGsb.insert(TABLE_FICHE_DE_FRAIS, null, conteneurDeValeurFiche);     
    	 ContentValues   conteneurDeValeurFraisKm = new ContentValues ();
    	    conteneurDeValeurFraisKm.put(COL_ID_LIGNE_FORFAIT,  "KM");
    	    conteneurDeValeurFraisKm.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi) ;
    	    conteneurDeValeurFraisKm.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi) ;
    	    conteneurDeValeurFraisKm.put(COL_QTE_LIGNE_FRAIS, leKmSaisi);
   requeteurBaseGsb.insert(TABLE_LIGNE_FORFAIT, null, conteneurDeValeurFraisKm);
    	  ContentValues conteneurDeValeurSejour =  new ContentValues ();
    	  conteneurDeValeurSejour.put(COL_ID_LIGNE_FORFAIT, "NUI") ;
    	  conteneurDeValeurSejour.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi);
    	  conteneurDeValeurSejour.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi);
    	  conteneurDeValeurSejour.put(COL_QTE_LIGNE_FRAIS, leSejourSaisi);
         requeteurBaseGsb.insert(TABLE_LIGNE_FORFAIT, null, conteneurDeValeurSejour);
    	  ContentValues conteneurDeValeurRepas = new ContentValues ();
    	  conteneurDeValeurRepas.put(COL_ID_LIGNE_FORFAIT, "REP");
    	  conteneurDeValeurRepas.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi);
    	  conteneurDeValeurRepas.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi);
    	  conteneurDeValeurRepas.put(COL_QTE_LIGNE_FRAIS, leRepasSaisi);
      requeteurBaseGsb.insert(TABLE_LIGNE_FORFAIT, null, conteneurDeValeurRepas);
    	  ContentValues conteneurDeValeurEtape = new ContentValues () ;
    	  conteneurDeValeurEtape.put(COL_ID_LIGNE_FORFAIT, "ETP");
    	  conteneurDeValeurEtape.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi);
    	  conteneurDeValeurEtape.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi);
    	  conteneurDeValeurEtape.put(COL_QTE_LIGNE_FRAIS, leEtapeSaisi);
     requeteurBaseGsb.insert(TABLE_LIGNE_FORFAIT, null, conteneurDeValeurEtape);	    
    	    return 1;
    	
		// TODO Auto-generated method stub
	
		
	}
	/**
	 * methode permettant de veriifier si une fiche existe pour un mois donnée
	 * @param leIDSaisi
	 * @param leMoisSaisi
	 * @return String retourValeurDemois
	 * @throws SQLException
	 */
	public String existeFrais(String leIDSaisi, String leMoisSaisi) throws SQLException {
		// TODO Auto-generated method stub
		 String selectionColonne [] = new String [] {COL_ID_VISITEUR_FICHE_FRAIS, COL_MOIS_FICHE_DE_FRAIS, COL_ID_ETAT_FICHE_FRAIS} ;
		 Cursor curseurDeLecture = requeteurBaseGsb.query(TABLE_FICHE_DE_FRAIS, selectionColonne, 
		  COL_ID_VISITEUR_FICHE_FRAIS + " = " + "'" + leIDSaisi +"'" , null, null, null, null) ;
				if(curseurDeLecture != null ) {
					curseurDeLecture.moveToFirst() ;
					for( curseurDeLecture.moveToFirst() ; !curseurDeLecture.isAfterLast(); curseurDeLecture.moveToNext()) 
					{
						String retourValeurDemois = curseurDeLecture.getString(1) ;
						if (retourValeurDemois.contentEquals(leMoisSaisi) ) {
							curseurDeLecture.close()		;	
							return retourValeurDemois ;	
						  }
						}
					      
					  }
			curseurDeLecture.close();	
		  return "";
		}
	/**
	 * <p> methode permettant d'enregistrer le frais hors forfait 
	 * @param leIdSaisi
	 * @param leMoisSaisi
	 * @param laDateSaisi
	 * @param leLibelleSaisi
	 * @param leMontantSaisi
	 * @return long 1
	 * @throws SQLException
	 */
	public long enregistrerHorsForfait(String leIdSaisi, String leMoisSaisi,
			String laDateSaisi, String leLibelleSaisi, String leMontantSaisi) throws SQLException {
		// TODO Auto-generated method stub
		 ContentValues conteneurDevaleur = new ContentValues();
		 conteneurDevaleur.put(COL_ID_VISITEUR_HORS_FORFAIT, leIdSaisi);
		 conteneurDevaleur.put(COL_MOIS_HORS_FORFAIT, leMoisSaisi);
		 conteneurDevaleur.put(COL_DATE_HORS_FORFAIT, laDateSaisi);
		 conteneurDevaleur.put(COL_LIBELLE_HORS_FORFAIT, leLibelleSaisi);
		 conteneurDevaleur.put(COL_MONTANT_HORS_FORFAIT, leMontantSaisi);
		 return  requeteurBaseGsb.insert(TABLE_HORS_FORFAIT, null, conteneurDevaleur);
		
	}
	
	
	/**
	 * <p> methode permettant de d'afficher les elements d'une fiche de frais
	 * @param leMoiSaisi
	 * @param leIdSaisi
	 * @return tableau String ficheSelectionne 
	 * @throws SQLException
	 */
	
	public String[] afficherFiche(String leMoiSaisi, String leIdSaisi)  throws SQLException {
		// TODO Auto-generated method stub
		String Colonneselection [] = {COL_ID_VISITEUR_FICHE_FRAIS , COL_MOIS_FICHE_DE_FRAIS ,
				COL_MONTANT_FICHE_FRAIS, COL_ID_ETAT_FICHE_FRAIS } ;
		Cursor  curseurDeLecture =  requeteurBaseGsb.query(TABLE_FICHE_DE_FRAIS, Colonneselection, COL_ID_VISITEUR_FICHE_FRAIS +  
			" = '"	+ leIdSaisi + "'" , null, null, null, null) ;
		
		if (curseurDeLecture != null) {
			curseurDeLecture.moveToFirst() ;
			    for (curseurDeLecture.moveToFirst() ; !curseurDeLecture.isAfterLast(); curseurDeLecture.moveToNext() ) {
			    	 String  retourMois = curseurDeLecture.getString(1) ;
			    	 if ( retourMois.contentEquals(leMoiSaisi)) {
			    		   String montant = curseurDeLecture.getString(2) ;
			    		   String idEtat =  curseurDeLecture.getString(3) ;
			    		   String ficheSelectionne [] = { montant , idEtat }  ;
			    		   curseurDeLecture.close() ;
			    		   return ficheSelectionne  ;
			    	 }
			    	
			    }
			 
		}
		  curseurDeLecture.close() ;
		return null;
	}
	/**
	 * <p> methode permettant d'afficher une ligne de frais forfaitisée
	 * @param leIdSaisi
	 * @param leMoisSaisi
	 * @return String listeLigneforfait [] 
	 * @throws SQLException
	 */
	public String [] afficheForfait (String leIdSaisi, String leMoisSaisi) throws SQLException {
		// TODO Auto-generated method stub
		String colonneSelectionne [] = { COL_ID_VISITEUR_LIGNE_FORFAIT ,COL_MOIS_LIGNE_FORFAIT  ,COL_ID_LIGNE_FORFAIT , COL_QTE_LIGNE_FRAIS } ;
		Cursor curseurDelecture = requeteurBaseGsb.query(TABLE_LIGNE_FORFAIT, colonneSelectionne,
			          COL_ID_VISITEUR_LIGNE_FORFAIT + " = '" + leIdSaisi + "'" , null , null , null , null ) ;
		//Cursor curseurDelecture =  requeteurBaseGsb.query(TABLE_LIGNE_FORFAIT, null, null, null, null, null, null);
		String faux  = "faux" ;
		 
		if (curseurDelecture != null) {
			 String nombreEtape = "" ;
			 String nombreSejour = "" ;
			 String nombreRepas = "";
			 String nombreKm = "" ;
		
			 
			curseurDelecture .moveToFirst() ;
			for(curseurDelecture .moveToFirst() ;  !curseurDelecture.isAfterLast() ; curseurDelecture.moveToNext() ){
				      String retourMois = curseurDelecture.getString(1) ; 
				      faux = retourMois ;
				       if (retourMois.contentEquals(leMoisSaisi) ) {
				    	       String rechercheEtape = curseurDelecture .getString(2) ;
				    	       if(rechercheEtape.contentEquals("ETP")) {
				    	    	    nombreEtape = curseurDelecture.getString(3); 
				    	       }
				    	      String rechercheSejour = curseurDelecture.getString(2);
				    	      if (rechercheSejour.contentEquals("NUI")) {
				    	      nombreSejour  =  	curseurDelecture.getString(3); 	    	    	  
				    	     }
				    	      String rechercheRepas = curseurDelecture.getString(2);
				    	      if (rechercheRepas.contentEquals("REP")) {
				    	      nombreRepas = curseurDelecture.getString(3); 
				    	      }
				    	      String rechercheKm = curseurDelecture.getString(2) ;
				    	      if (rechercheKm .contentEquals("KM")) {
				    	    	  nombreKm = curseurDelecture.getString(3); 
				    	      }
				    	    
				       }
			}
			 String listeLigneforfait []  = { nombreEtape , nombreSejour , nombreRepas ,nombreKm } ;      
			 curseurDelecture.close();
			
			 return listeLigneforfait ;
		}
		curseurDelecture.close();
		String fff [] = {faux ,faux ,faux ,faux} ;
		return fff ;
	}
	/**
	 * <p> methode permettant de proceder à la mise à jour d'une ligne de frais forfaitiser 
	 * @param leIDSaisi
	 * @param leMoisSaisi
	 * @param leEtapeSaisi
	 * @param leSejourSaisi
	 * @param leRepasSaisi
	 * @param leKmSaisi
	 * @return void 
	 */
	public void miseAjourForfait(String leIDSaisi, String leMoisSaisi, String leEtapeSaisi , String leSejourSaisi, String leRepasSaisi , String leKmSaisi) {
		// TODO Auto-generated method stub
		
			   	 ContentValues   conteneurDeValeurFraisKm = new ContentValues ();
				    conteneurDeValeurFraisKm.put(COL_ID_LIGNE_FORFAIT,  "KM");
				    conteneurDeValeurFraisKm.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi) ;
				    conteneurDeValeurFraisKm.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi) ;
				    conteneurDeValeurFraisKm.put(COL_QTE_LIGNE_FRAIS, leKmSaisi);
				    String uneClauseKm [] = {"KM",leIDSaisi, leMoisSaisi } ;
			 requeteurBaseGsb.update(TABLE_LIGNE_FORFAIT, conteneurDeValeurFraisKm,
					COL_ID_LIGNE_FORFAIT + "=?"  +  " AND " + COL_ID_VISITEUR_LIGNE_FORFAIT + "=? "  +  
			        " AND " +  COL_MOIS_LIGNE_FORFAIT + "=?"  , uneClauseKm) ;
				  ContentValues conteneurDeValeurSejour =  new ContentValues ();
				  conteneurDeValeurSejour.put(COL_ID_LIGNE_FORFAIT, "NUI") ;
				  conteneurDeValeurSejour.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi);
				  conteneurDeValeurSejour.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi);
				  conteneurDeValeurSejour.put(COL_QTE_LIGNE_FRAIS, leSejourSaisi);
				  String uneClauseSejour [] = {"NUI",leIDSaisi, leMoisSaisi } ;
			 requeteurBaseGsb.update(TABLE_LIGNE_FORFAIT, conteneurDeValeurSejour,
							COL_ID_LIGNE_FORFAIT + "=?"  +  " AND " + COL_ID_VISITEUR_LIGNE_FORFAIT + "=? "  +  
					        " AND " +  COL_MOIS_LIGNE_FORFAIT + "=?"  ,uneClauseSejour) ;
				  ContentValues conteneurDeValeurRepas = new ContentValues ();
				  conteneurDeValeurRepas.put(COL_ID_LIGNE_FORFAIT, "REP");
				  conteneurDeValeurRepas.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi);
				  conteneurDeValeurRepas.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi);
				  conteneurDeValeurRepas.put(COL_QTE_LIGNE_FRAIS, leRepasSaisi);
				  String uneClauserepas [] = {"REP",leIDSaisi, leMoisSaisi } ;
		     requeteurBaseGsb.update(TABLE_LIGNE_FORFAIT, conteneurDeValeurRepas,
							COL_ID_LIGNE_FORFAIT + "=?"  +  " AND " + COL_ID_VISITEUR_LIGNE_FORFAIT + "=? "  +  
					        " AND " +  COL_MOIS_LIGNE_FORFAIT + "=?"  , uneClauserepas) ;
				  ContentValues conteneurDeValeurEtape = new ContentValues () ;
				  conteneurDeValeurEtape.put(COL_ID_LIGNE_FORFAIT, "ETP");
				  conteneurDeValeurEtape.put(COL_ID_VISITEUR_LIGNE_FORFAIT, leIDSaisi);
				  conteneurDeValeurEtape.put(COL_MOIS_LIGNE_FORFAIT, leMoisSaisi);
				  conteneurDeValeurEtape.put(COL_QTE_LIGNE_FRAIS, leEtapeSaisi);
				  String uneClauseEtape [] = {"ETP",leIDSaisi, leMoisSaisi } ;
		     requeteurBaseGsb.update(TABLE_LIGNE_FORFAIT, conteneurDeValeurEtape,
							COL_ID_LIGNE_FORFAIT + "=?"  +  " AND " + COL_ID_VISITEUR_LIGNE_FORFAIT + "=? "  +  
					        " AND " +  COL_MOIS_LIGNE_FORFAIT + "=?"  , uneClauseEtape) ;
		
	}
	/**
	 * <p> methode permettant d'afficher une ligne de frais hors forfait
	 * @param leIdSaisi
	 * @param leMoiSaisi
	 * @return String resultat
	 * @throws SQLException
	 */
	public String  afficherHorsForfait (String leIdSaisi ,String  leMoiSaisi) throws SQLException  {
		 String SelectionnColonne [] = {COL_NUM_HORS_FORFAIT,COL_DATE_HORS_FORFAIT,COL_LIBELLE_HORS_FORFAIT ,
		    COL_MONTANT_HORS_FORFAIT } ;
		 //  
		  String lesParametreClause [] = {leIdSaisi ,leMoiSaisi } ;
		  Cursor cuseurDeLecture = requeteurBaseGsb.query(TABLE_HORS_FORFAIT, SelectionnColonne, 
				  COL_ID_VISITEUR_HORS_FORFAIT + "=?" + " AND " +  COL_MOIS_HORS_FORFAIT + "=?"
				 , lesParametreClause , null, null, null) ;
		  if(cuseurDeLecture != null ) {
			 String resultat = "" ;
			   for(cuseurDeLecture.moveToFirst(); !cuseurDeLecture.isAfterLast() ;  cuseurDeLecture.moveToNext() ) {
			   resultat = resultat + " N° HorsF " + cuseurDeLecture.getString(0) + " Date " + cuseurDeLecture.getString(1) +
				    " libelle " + cuseurDeLecture.getString(2) +  " montant " + cuseurDeLecture.getString(3)   + "\n" + "\n" ;  
				      
			   }
			   cuseurDeLecture.close() ;
			  return  resultat  ;
		  }
		  cuseurDeLecture.close() ;	
		return "rien" ;
		
	}
	/**
	 * methode permettant la supprression d'une ligne de frais forfaitiser 
	 * @param numeroHorsForfait
	 * @throws SQLException
	 */
	public void supprimerHorsforfait(String numeroHorsForfait) throws SQLException  {
		// TODO Auto-generated method stub
	      String parametreDelaClause [] = {numeroHorsForfait};
		requeteurBaseGsb.delete(TABLE_HORS_FORFAIT, COL_NUM_HORS_FORFAIT + "=?", parametreDelaClause  );
		
	}
	/**
	 * methode permettant la supression d'une fiche de frais
	 * @param leIdSaisi
	 * @param leMoiSaisi
	 * @throws SQLException
	 */
	public void suppimerFiche(String leIdSaisi, String leMoiSaisi) throws SQLException  {
		// TODO Auto-generated method stub
		 String parametreDelaClause [] = {leIdSaisi, leMoiSaisi };
		 requeteurBaseGsb.delete(TABLE_LIGNE_FORFAIT, COL_ID_VISITEUR_LIGNE_FORFAIT  + "=?" + "" + " AND " +
		   COL_MOIS_LIGNE_FORFAIT +  "=?" , parametreDelaClause);
		 
		 requeteurBaseGsb.delete(TABLE_HORS_FORFAIT, COL_ID_VISITEUR_HORS_FORFAIT + "=?" + "" + " AND " +
				   COL_MOIS_HORS_FORFAIT +  "=?" , parametreDelaClause);

		 requeteurBaseGsb.delete(TABLE_FICHE_DE_FRAIS, COL_ID_VISITEUR_FICHE_FRAIS + "=?" + "" + " AND " +
				   COL_MOIS_FICHE_DE_FRAIS +  "=?" , parametreDelaClause);
		 
	}
	/**
	 * methode permettant d'afficher une ligne de frais forfaitisé
	 * @param leNumeroSaisi
	 * @return  String  resultat [] 
	 * @throws SQLException
	 */
	public String[] afficheLigneHorsForfait(String leNumeroSaisi)throws SQLException {
		// TODO Auto-generated method stub
		String SelectionnColonne [] = {COL_NUM_HORS_FORFAIT,COL_DATE_HORS_FORFAIT,COL_LIBELLE_HORS_FORFAIT ,
			    COL_MONTANT_HORS_FORFAIT } ;
			 //  
			  String lesParametreClause [] = {leNumeroSaisi} ;
			  Cursor cuseurDeLecture = requeteurBaseGsb.query(TABLE_HORS_FORFAIT, SelectionnColonne, 
					  COL_NUM_HORS_FORFAIT + "=?" , lesParametreClause , null, null, null) ;
			  if(cuseurDeLecture != null ) {
				 String laDate = "" ;
				 String leLibelle = "";
				 String leMontant= "" ;
				 cuseurDeLecture.moveToFirst() ;
				 laDate = cuseurDeLecture.getString(1) ;
				  leLibelle  = cuseurDeLecture.getString(2) ;
				  leMontant = cuseurDeLecture.getString(3)  ;
				   cuseurDeLecture.close() ;
				   String  resultat [] = { laDate, leLibelle, leMontant} ;
				return  resultat  ;
			  }
		cuseurDeLecture.close() ;	
		return null;
	}
	/**
	 * methode permettant la mise à jour de frais hors forfaitisé
	 * @param leIdSaisi
	 * @param leMoisSaisi
	 * @param laDateSaisi
	 * @param leLibelleSaisi
	 * @param leMontantSaisi
	 * @param leNumeroSaisi
	 * @return 1
	 */
	public long miseAJourHorsForfait(String leIdSaisi, String leMoisSaisi,
			String laDateSaisi, String leLibelleSaisi, String leMontantSaisi,
			String leNumeroSaisi)  {
		// TODO Auto-generated method stub
		String parametreDelaClause [] = {leNumeroSaisi};
		 ContentValues conteneurDevaleur = new ContentValues();
		 conteneurDevaleur.put(COL_ID_VISITEUR_HORS_FORFAIT, leIdSaisi);
		 conteneurDevaleur.put(COL_MOIS_HORS_FORFAIT, leMoisSaisi);
		 conteneurDevaleur.put(COL_DATE_HORS_FORFAIT, laDateSaisi);
		 conteneurDevaleur.put(COL_LIBELLE_HORS_FORFAIT, leLibelleSaisi);
		 conteneurDevaleur.put(COL_MONTANT_HORS_FORFAIT, leMontantSaisi);
		return requeteurBaseGsb.update(TABLE_HORS_FORFAIT, conteneurDevaleur,
				COL_NUM_HORS_FORFAIT + "=?", parametreDelaClause) ;
				
	}
	
		
}
