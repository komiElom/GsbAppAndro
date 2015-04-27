package composant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class ModifierHorsForfait extends Activity implements View.OnClickListener {
	TextView afficheurIdvisteur , afficheurNomVisiteur , afficheurMois ;
	EditText saisirDatehorsf , SaisirLibelleHors, SaisirMontanthors ;
	Button  ValiderSaisiHors ;
    String lesIdentites [] ;
	String 	 leMoiSaisi  ;
	String leNumeroSaisi ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_saisir_frais_horsforfai) ;
		initialiser() ;
		initialiserHorsForfait() ;
	}

	

	private void initialiser() {
		// TODO Auto-generated method stub
		
		this.afficheurIdvisteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurIDSaisihorsF);
		this.afficheurNomVisiteur =  (TextView) this.findViewById(com.gsb.R.id.tvAfficheurDuNomSaisihorsF);
		this.afficheurMois = (TextView) this.findViewById(com.gsb.R.id.tvAfficPeriodeHors);
		this.saisirDatehorsf = (EditText) this.findViewById(com.gsb.R.id.edDateSaisihorsF);
		this.SaisirLibelleHors = (EditText) this.findViewById(com.gsb.R.id.ediLibelleSaisihorsF);
		this.SaisirMontanthors = (EditText) this.findViewById(com.gsb.R.id.edmontantSaisihorsF);
		this.ValiderSaisiHors = (Button) this.findViewById(com.gsb.R.id.bValidationSaisihorsF);
		Bundle recepteurDepaquet = this.getIntent().getExtras();
		 lesIdentites = recepteurDepaquet.getStringArray("paquetInfoIdentite") ;
		 leMoiSaisi = recepteurDepaquet.getString("laPeriode") ;
		 this.leNumeroSaisi = recepteurDepaquet.getString("numeroHorsF") ;
		 this.afficheurIdvisteur.setText(lesIdentites [0]);
		 this.afficheurNomVisiteur.setText(lesIdentites [1]);
		 this.afficheurMois.setText(leMoiSaisi);
		 this.ValiderSaisiHors.setOnClickListener(this) ;
		
		
		
	}
	private void initialiserHorsForfait() {
		// TODO Auto-generated method stub
		try {
		GestionnaireBD unGestionnaireBD = new GestionnaireBD (this) ;
		unGestionnaireBD.ouvrir();
		 String lesHorsForfait [] = unGestionnaireBD.afficheLigneHorsForfait(leNumeroSaisi) ;
		 unGestionnaireBD.fermer() ;
		 this.saisirDatehorsf.setText(lesHorsForfait [0]) ;
		 this.SaisirLibelleHors.setText(lesHorsForfait [1]) ;
		 this.SaisirMontanthors.setText(lesHorsForfait [2]) ;
    	
		} catch (Exception e) {
	    	 
	    	 Dialog unDialogue  = new Dialog (this)  ;
	    	 TextView uneVueTexte = new TextView (this);
	    	 unDialogue.setContentView(uneVueTexte);
	    	 unDialogue.setTitle("erreur") ;
		}
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()) {
		
		case com.gsb.R.id.bValidationSaisihorsF :
			     String laDateSaisi = this.saisirDatehorsf.getText().toString();
			     String leLibelleSaisi = this.SaisirLibelleHors.getText().toString();
			     String leMontantSaisi = this.SaisirMontanthors.getText().toString();
			     String  leIdSaisi  = this.afficheurIdvisteur.getText().toString();
			     String leNomSaisi = this.afficheurNomVisiteur.getText().toString();
			     String leMoisSaisi = this.afficheurMois.getText().toString();
			     try {
			    	 GestionnaireBD unGestionnaire = new GestionnaireBD (this);
			    	 unGestionnaire.ouvrir();
			    	long verif = unGestionnaire.miseAJourHorsForfait(leIdSaisi,leMoisSaisi, laDateSaisi,leLibelleSaisi,leMontantSaisi, this.leNumeroSaisi );
			    	 unGestionnaire.fermer();	 
				      this.finish();
			     } catch (Exception e) {
			    	 
			    	 Dialog unDialogue  = new Dialog (this)  ;
			    	 TextView uneVueTexte = new TextView (this);
			    	 unDialogue.setContentView(uneVueTexte);
			    	 unDialogue.setTitle("erreur") ;
			    	 
			     }
			
			break;
		
		}
		
	}
	
	
	
	
}
