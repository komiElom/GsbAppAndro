package composant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import  android.view.View;

public class AjoutHorsF extends Activity implements View.OnClickListener {
	
	TextView afficheurIdVisiteur , afficheurNom, afficheurDuMois ;
	Button  retourAcceuil, ajouterHors ;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_ajout_hors_forfait) ;
		initialiser ();
	}

	private void initialiser() {
		// TODO Auto-generated method stub
		
		this.afficheurIdVisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurIDAjouthorsF);
		this.afficheurNom = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurDuNomVisiteurAjoutHorsF);
		this.afficheurDuMois = (TextView) this.findViewById(com.gsb.R.id.tvAfficPeriodeAjouthorsF);
		this.retourAcceuil  = (Button) this.findViewById(com.gsb.R.id.bRetourAcceuilAjoutHorsFor) ;
		this.ajouterHors = (Button) this.findViewById(com.gsb.R.id.bAjouterHorsFait);
		this.retourAcceuil.setOnClickListener(this);
		this.ajouterHors.setOnClickListener(this) ;
		Bundle recepteurDePaquet = new Bundle () ;
	    recepteurDePaquet = this.getIntent().getExtras();
	     String lesIdentites [] = recepteurDePaquet.getStringArray("paquetInfoIdentite");
	      String leMoisSaisi = recepteurDePaquet.getString("laPeriode")  ;
	      this.afficheurIdVisiteur.setText(lesIdentites[0]) ;
	       this.afficheurNom.setText(lesIdentites[1]) ;
	       this.afficheurDuMois.setText(leMoisSaisi);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case com.gsb.R.id.bAjouterHorsFait :
		  String lesIdentites[] = {this.afficheurIdVisiteur.getText().toString(), this.afficheurNom.getText().toString() } ;
		  String leMoisSaisi = this.afficheurDuMois.getText().toString();
		  Bundle expediteurDepaquet = new Bundle () ;
			expediteurDepaquet.putStringArray("paquetInfoIdentite", lesIdentites);
			expediteurDepaquet.putString("laPeriode", leMoisSaisi);
			Intent  objectif_sur_Actif = new Intent (AjoutHorsF.this , composant.SaisirHorsF.class) ;
			objectif_sur_Actif.putExtras(expediteurDepaquet);
			this.startActivity(objectif_sur_Actif);
			this.finish();
			break;
			
		case com.gsb.R.id.bRetourAcceuilAjoutHorsFor :
			this.finish();
			break ;
		}
		
	}
	

}
