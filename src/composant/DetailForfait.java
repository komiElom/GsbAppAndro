package composant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
/**
 * 
 * @author  komi elom heekpo 
 * cette classe présente le détail des frais forfaitisés
 *
 */
public class DetailForfait  extends Activity {

	
	TextView afficheurIdVisiteur , afficheurNomVisiteur, afficheurMois , afficheurNombreEtape, afficheurNombreSejour,
				afficheurNombreRepas, afficheurNombreKm ;
	
	
	String leMoiSaisi ;
	String lesIdentites  [] ;
	
/**
 * @see onCreate
 * appel de la methode initialiserforfait
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_consultat_forfait);
		initialiser() ;
		initialiserforfait();
	}

	/**
	 * @see initialiser
	 * implementation des variables  de la vue de l'objet de mise en page: page_consultat_forfait
	 * <p>
	 * @return void 
	 */
	private void initialiser() {
		// TODO Auto-generated method stub
		 this.afficheurIdVisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheuriDVisteurDetailForfait) ;
		 this.afficheurNomVisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurNomVisiteurDetailForfait) ;
		 this.afficheurMois = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurPeriodeDetailForfait) ;
		 this.afficheurNombreEtape = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurEtapeDetailForfait);
		 this.afficheurNombreKm = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurDekmDetailForfait) ;
		 this.afficheurNombreRepas = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurRepasDetailForfait);
		 this.afficheurNombreSejour = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurSejourDetailForfait) ;
		 Bundle recepteurDepaquet = new Bundle () ;
		 recepteurDepaquet =   this.getIntent().getExtras();
		 this.lesIdentites = recepteurDepaquet.getStringArray("paquetInfoIdentite") ;
		 this.leMoiSaisi = recepteurDepaquet.getString("laPeriode") ;
		 this.afficheurIdVisiteur.setText(lesIdentites[0]) ;
		 this.afficheurNomVisiteur.setText(lesIdentites[1]);
		 this.afficheurMois.setText(leMoiSaisi);
		 
		 
		
	}
	/**
	 * appel de la methode  afficheForfait ()
	 * {@link afficheForfait } 
	 * <p> 
	 * @param this.afficheurIdVisiteur.getText().toString() : id du visiteur 
	 * @param leMoiSaisi : le moi saisi
	 * @return tableau ligneForfait[] 
	 */
	private void initialiserforfait() {
		// TODO Auto-generated method stub
			
		GestionnaireBD unGestionBD = new GestionnaireBD (this) ;
	    unGestionBD.ouvrir() ;
	   String ligneForfait[] = unGestionBD.afficheForfait(this.afficheurIdVisiteur.getText().toString(), leMoiSaisi) ;
	    unGestionBD.fermer();     
	     this.afficheurNombreEtape.setText(ligneForfait[0]);
	     this.afficheurNombreSejour.setText(ligneForfait[1]) ;
	     this.afficheurNombreRepas.setText(ligneForfait[2])  ;
	     this.afficheurNombreKm.setText(ligneForfait[3]) ;
	}
	/**
	 * @param menu Menu 
	 * <p> appel methode inflate () 
	 * @param modifierforfait : menu
	 * @return true
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater  propulseurDeMenue =   this.getMenuInflater () ;
		propulseurDeMenue.inflate(com.gsb.R.menu.modifierforfait, menu);
		return true ;
		
	}

	/**
	 *  @param item MenuItem 
	 *  si le menu MenModierForfait est selectionné
	 *  <p> recuperation des elements de  frais forfait dans un tableau : listeModifier []
	 *  <p> mise en paquet du tableau listeModifier []
	 *  <p> mis en paquet des identités de l'utilisateur 
	 *  <p> l'activité ModifierForfait est démarréé avec le  paquet 
	 *  <p>  l'actvitév en cours s'arrete
	 *  
	 * @return false 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		    switch (item.getItemId()) {
		    case com.gsb.R.id.MenModierForfait :
		    	String etap = this.afficheurNombreEtape.getText().toString();
		    	String km = this.afficheurNombreKm.getText().toString();
		    	String sejour = this.afficheurNombreSejour.getText().toString();
		    	String repas = this.afficheurNombreRepas.getText().toString();
		    	String listeModifier [] = { etap, km, sejour, repas } ;
		    	Bundle envoiDePaquet = new Bundle () ;
		    	envoiDePaquet.putStringArray("paquetInfoIdentite", this.lesIdentites) ;
		    	envoiDePaquet.putString("laPeriode", this.leMoiSaisi);
		    	envoiDePaquet .putStringArray("listeForfait", listeModifier) ;
		    	Intent  objectif_sur_act =  new Intent ("composant.MODIFIERFORFAIT") ;
		    	objectif_sur_act.putExtras(envoiDePaquet) ;
		    	this.startActivity(objectif_sur_act) ;
		    	this.finish();
		    	break;
		    
		    
		    }
		return  false ;
		
	}
	
	


}
