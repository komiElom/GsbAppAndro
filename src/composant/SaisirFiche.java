package composant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View ;

public class SaisirFiche extends Activity  implements View.OnClickListener {
	TextView afficheurDeIdvisiteur , afficheurDeNomvisiteur ;
	Button bouttonSuivant ;
	EditText SaisirMois ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_saisir_fiche);
		initialiser ();
		Bundle recepteurDepaquet = new Bundle ();
	    recepteurDepaquet		= this.getIntent().getExtras();
	    String lesIndentites [] = recepteurDepaquet.getStringArray("paquetInfoIdentite");
	    this.afficheurDeIdvisiteur.setText(lesIndentites [0]) ;
	    this.afficheurDeNomvisiteur.setText(lesIndentites [1] );
	}

	private void initialiser() {
		// TODO Auto-generated method stub
		this.afficheurDeIdvisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurIdVisiteurSaisirFiche);
		this.afficheurDeNomvisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurNomVisiteurSaisirFiche) ;
		this.SaisirMois = (EditText) this.findViewById(com.gsb.R.id.edPeriodeSaisirFiche);
		this.bouttonSuivant =(Button) this.findViewById(com.gsb.R.id.bSuivantSaisirFiche);
		this.bouttonSuivant.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case com.gsb.R.id.bSuivantSaisirFiche:
			String lemoisSaisi = this.SaisirMois.getText().toString();
			Bundle envoiDepaquet = new Bundle ();
			String lesIndentites []  = { afficheurDeIdvisiteur.getText().toString(), afficheurDeNomvisiteur.getText().toString()} ;
			envoiDepaquet.putStringArray("paquetInfoIdentite", lesIndentites);
			//envoiDepaquet.putString("laPeriode", lemoisSaisi);
			Intent objectif_sur_Act = new Intent (SaisirFiche.this, composant.SaisirForfait.class);
			objectif_sur_Act.putExtras(envoiDepaquet);
			this.startActivity(objectif_sur_Act);
			
			break ;
		
		}
		
		
	}

}
