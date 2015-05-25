package composant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * 
 * @author  komi elom heekpo
 * cette classe permet la presentation de la fiche à consulter 
 *
 */
public class ConsulFiche extends Activity implements View.OnClickListener {
	


	TextView afficheuridVisiteur , afficheurNomVisiteur , afficheurMois , afficheurMontantTotal, afficheurEtatFiche ;
	Button bouttonDetailForfait , bouttonDetailHors ;
	String leMoiSaisi ;
	String lesIdentites [] ;
	/**
	 * @see onCreate
	 * appel de la methode initialiserFiche
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_consulfiche) ;
		initialiser () ;
	     initialiserFiche ();
		
	}

	/**
	 * @see initialiser ()
	 */
	private void initialiser() {
		// TODO Auto-generated method stub
		this.afficheuridVisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurIdVisiteurConsultatFiche);
		this.afficheurNomVisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurNomVisiteurConsultatFiche);
		this.afficheurMontantTotal = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurMontantConsultatFiche) ;
		this.afficheurEtatFiche = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurEtatFicheConsultatFiche) ;
		this.afficheurMois = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurPeriodeConsultatFiche);
		this.bouttonDetailForfait = (Button) this.findViewById(com.gsb.R.id.bDetailForfaitiserConsultatFiche) ;
		this.bouttonDetailHors  = (Button) this.findViewById(com.gsb.R.id.bDetailsHorsForfaitConsultatFiche) ;
		Bundle recepteurDePaquet = new  Bundle () ;
		recepteurDePaquet = this.getIntent().getExtras();
	  	lesIdentites  = recepteurDePaquet.getStringArray("paquetInfoIdentite");
	   leMoiSaisi = recepteurDePaquet.getString("laPeriode");
	     this.afficheuridVisiteur.setText(lesIdentites[0]) ;
	   this.afficheurNomVisiteur.setText(lesIdentites[1]);
	    this.afficheurMois.setText(leMoiSaisi);
		this.bouttonDetailForfait.setOnClickListener(this);
	  	this.bouttonDetailHors.setOnClickListener(this);	
		
	}
	/**
	 * appel de la methode afficherFiche
	 * @param leMoiSaisi : le moi saisi 
	 * @param this.afficheuridVisiteur.getText().toString() :le id du visiteur
	 */
	private void initialiserFiche() {
		// TODO Auto-generated method stub
		GestionnaireBD   unGestionnaire = new GestionnaireBD (this) ;
		unGestionnaire.ouvrir();
		try {
	    String uneFiche [] = unGestionnaire.afficherFiche(leMoiSaisi , this.afficheuridVisiteur.getText().toString());
	    unGestionnaire.fermer();
		String montant = uneFiche[0] ;
	     String EtatFiche = uneFiche[1];
	     
	       if( EtatFiche.contentEquals("CR")) {
	    	   EtatFiche  = "Saisie en cours";			     
	       }
	       if( EtatFiche.contentEquals("CL")) {
	    	   EtatFiche  = "Saisie en Cloturé";			     
	       }
	       if( EtatFiche.contentEquals("RB")) {
	    	   EtatFiche  = "remboursé";		
	         }
	       if( EtatFiche.contentEquals("VA")) {
	    	   EtatFiche  = "Saisie Validée";			     
	       }
	      
	        
	        if (montant == null){
	    	 montant = "00";   
	      }
		this.afficheurMontantTotal.setText( montant) ;
        this.afficheurEtatFiche.setText( EtatFiche) ;
		} catch (Exception e) {
			
			this.finish();
		}
		
	}

/**
 * @param v View :vue bouton 
 * <p> si le bouton bDetailForfaitiserConsultatFiche est actionné :
 * <p> l'activité DetailForfait démarre 
 * <p> l'activité en cours s'arrete 
 * <p> au contraire si le bouton  bDetailsHorsForfaitConsultatFiche  est cliqué
 * <p> l'activité en cours s'arrete 
 * <p> l'activité DetailHorsForfait démarre
 * 
 * 
 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		
			case com.gsb.R.id.bDetailForfaitiserConsultatFiche :
				Bundle envoiDePaquet = new Bundle () ;
				 envoiDePaquet.putStringArray("paquetInfoIdentite", lesIdentites) ;
			    envoiDePaquet.putString("laPeriode", this.leMoiSaisi);
			 	Intent object_sur_act = new Intent (ConsulFiche.this , composant.DetailForfait.class) ;
		        object_sur_act.putExtras(envoiDePaquet) ;
			    this.startActivity(object_sur_act) ;
			    this.finish();
				
			break ;
			
			case com.gsb.R.id.bDetailsHorsForfaitConsultatFiche :
				Bundle envoiDePaquet_b = new Bundle () ;
				envoiDePaquet_b.putStringArray("paquetInfoIdentite", lesIdentites) ;
				envoiDePaquet_b.putString("laPeriode", this.leMoiSaisi);
				Intent object_sur_Act_b = new Intent (ConsulFiche.this , composant.DetailHorsForfait.class) ;
				object_sur_Act_b.putExtras(envoiDePaquet_b);
				this.startActivity(object_sur_Act_b);
				this.finish();
				
				break ;
		}
		
	}
	
	
	/**
	 * @param menu Menu
	 * <p> appel de la methode inflate
	 * @param supprim_fiche menu
	 * @return true
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater propulseurDeMenu = this.getMenuInflater();
		propulseurDeMenu.inflate(com.gsb.R.menu.supprim_fiche, menu) ;
		return true ;
	}
	/**
	 * @param item MenuItem 
	 * <p> si le menu MenueSupprimerFiche est actionné
	 * <p> appel de la methode suppimerFiche
	 * @param this.lesIdentites[0] : l'id du visiteur 
	 * <p>
	 * @param this.leMoiSaisi :  le mois choisi 
	 * @return false 
	 * 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		 switch ( item.getItemId()) {
		 case com.gsb.R.id.MenueSupprimerFiche :
			 
			 GestionnaireBD unGest = new GestionnaireBD (this) ;
			 unGest.ouvrir() ;
			 unGest.suppimerFiche(this.lesIdentites[0], this.leMoiSaisi) ;
			 unGest.fermer();
			 this.finish();
		 break; 
		 
		 }
		
		
		return false;
	}

	
}
