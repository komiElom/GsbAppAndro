package composant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * 
 * @author komi elom heekpo
 * <p> cette classe permet de presenter le détail des frais hors forfaitisés
 *
 */
public class DetailHorsForfait  extends Activity {

	TextView  afficheurIdVisiteur , afficheurNom , afficheurDateHorsFrais, afficheurLibelle, afficheurMontant , afficheurListe ;
	String leMoisSaisi ;
	String lesIdentites [] ;
    
	
	/**
	 * @see onCreate
	 * <p> appel de la methode initialiserHorsForfait 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_consultation_fors_forfait) ;
		initialiser() ;
		initialiserHorsForfait () ;
		}

/**
 * @see initialiser
 */
	private void initialiser() {
		// TODO Auto-generated method stub
		this.afficheurListe =  (TextView) this.findViewById(com.gsb.R.id.tVlisteHorsFrais) ;
		Bundle  unrecepteurDePaquet = new Bundle () ;
		unrecepteurDePaquet = this.getIntent().getExtras();
		this.lesIdentites = unrecepteurDePaquet.getStringArray("paquetInfoIdentite") ;
		this.leMoisSaisi = unrecepteurDePaquet.getString("laPeriode");
		
		
		
	}
	
	/**
	 * appel de la methode afficherHorsForfait
	 * @param this.lesIdentites[0] : le id utilisateur
	 * @param leMoisSaisi : le mois saisi
	 * @return void 
	 */
	private void initialiserHorsForfait() {
		// TODO Auto-generated method stub
		try {
		GestionnaireBD unGestionnaireBD = new GestionnaireBD (this) ;
		unGestionnaireBD.ouvrir();
	    String lesHorsFrfait = unGestionnaireBD.afficherHorsForfait(this.lesIdentites[0] , leMoisSaisi) ;
	 	this.afficheurListe.setText(lesHorsFrfait ) ;
		unGestionnaireBD.fermer();
		if(lesHorsFrfait.contentEquals("")) {
			this.finish() ;
		}
		} catch (Exception e) {
			 Dialog unDialogue = new Dialog(this);
			 TextView uneVueTexte = new TextView(this) ;
			 uneVueTexte.setText("neant");
			 unDialogue.setContentView(uneVueTexte) ;
			 unDialogue.setTitle("aucun frais hors forfait") ;
			 this.finish() ;
		}
	}
	/**
	 * @param menu Menu 
	 * <p> appel de la methode inflate
	 * @param modifierhorsfra : menu
	 * @return true
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		  MenuInflater propulseurDeMenu = this.getMenuInflater() ;
		  propulseurDeMenu.inflate(com.gsb.R.menu.modifierhorsfra, menu);
		  
		return true ;
	}
/**
 * @param item MenuItem 
 * <p> si le menu MenuModifierHors est choisi 
 * <p> l'activité ChoixDuHorsForfait est démarrée 
 * <p> l'activité en cours est arretée
 * <p> si le menu MenSupprimerHors  choisi
 * <p> l'activité est démarrée SuprimerHorsForfait
 * <p> l'activité en cours est arretée
 * 
 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub,
		   Bundle envoiDePaquet = new Bundle () ;
		   envoiDePaquet.putStringArray("paquetInfoIdentite", this.lesIdentites) ;
		   envoiDePaquet.putString("laPeriode", this.leMoisSaisi) ;
		   
		switch (item.getItemId()) {
		case com.gsb.R.id.MenuModifierHors :
			   Intent objectif_sur = new Intent (DetailHorsForfait.this , composant.ChoixDuHorsForfait.class ) ;
			   objectif_sur.putExtras(envoiDePaquet);
			   this.startActivity(objectif_sur) ;
			   this.finish();
			   
			break;
			
		case com.gsb.R.id.MenSupprimerHors :
			  Intent objectif_sur_b = new  Intent (DetailHorsForfait.this, composant.SuprimerHorsForfait.class) ;
			   objectif_sur_b.putExtras(envoiDePaquet) ;
			   this.startActivity(objectif_sur_b);
			   this.finish();			    
			break;
		
		
		}
		return false ;
	}

}
