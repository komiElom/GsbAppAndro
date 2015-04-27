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

public class ConsulFiche extends Activity implements View.OnClickListener {
	


	TextView afficheuridVisiteur , afficheurNomVisiteur , afficheurMois , afficheurMontantTotal, afficheurEtatFiche ;
	Button bouttonDetailForfait , bouttonDetailHors ;
	String leMoiSaisi ;
	String lesIdentites [] ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_consulfiche) ;
		initialiser () ;
	     initialiserFiche ();
		
	}

	
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
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater propulseurDeMenu = this.getMenuInflater();
		propulseurDeMenu.inflate(com.gsb.R.menu.supprim_fiche, menu) ;
		return true ;
	}
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
