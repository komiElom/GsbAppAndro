package composant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class SaisirHorsF   extends Activity implements View.OnClickListener {
	TextView afficheurIdvisteur , afficheurNomVisiteur , afficheurMois ;
	EditText saisirDatehorsf , SaisirLibelleHors, SaisirMontanthors ;
	Button  ValiderSaisiHors ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_saisir_frais_horsforfai) ;
		initialiser() ;
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
		String lesIdentites [] = recepteurDepaquet.getStringArray("paquetInfoIdentite") ;
		 String leMoiSaisi = recepteurDepaquet.getString("laPeriode") ;
		 this.afficheurIdvisteur.setText(lesIdentites [0]);
		 this.afficheurNomVisiteur.setText(lesIdentites [1]);
		 this.afficheurMois.setText(leMoiSaisi);
		 this.ValiderSaisiHors.setOnClickListener(this) ;
		
		
		
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
			    	long verif = unGestionnaire.enregistrerHorsForfait(leIdSaisi,leMoisSaisi, laDateSaisi,leLibelleSaisi,leMontantSaisi   );
			    	 unGestionnaire.fermer();	 
			    	Dialog unDialogue  = new Dialog (this)  ;
				    TextView uneVueTexte = new TextView (this);
				    uneVueTexte.setText(" les frais hors  sont bien enregistrés") ;
				    unDialogue.setContentView(uneVueTexte);
				    unDialogue.setTitle("confirmation") ;
				    String lesIdentites []  = {this.afficheurIdvisteur.getText().toString(), this.afficheurNomVisiteur.getText().toString()};
				    Bundle envoiDepaquet = new Bundle () ;
				    envoiDepaquet.putStringArray("paquetInfoIdentite",lesIdentites);
				    envoiDepaquet.putString("laPeriode", leMoisSaisi);
				    Intent objectif_sur_Act = new Intent (SaisirHorsF.this, composant.AjoutHorsF.class) ;
				    objectif_sur_Act.putExtras(envoiDepaquet);
				    this.startActivity(objectif_sur_Act);		
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
