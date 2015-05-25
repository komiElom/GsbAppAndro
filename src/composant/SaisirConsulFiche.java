package composant;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 
 * @author  komi elom heekpo
 * <p>  cette classe permet la saisie de la période de la fiche a consulter 
 *
 */
public class SaisirConsulFiche extends Activity implements  View.OnClickListener {
	TextView afficheurDeIdvisiteur , afficheurDeNomvisiteur ;
	Button bouttonSuivant ;
	EditText SaisirMois ;
/**
 * @see onCreate 
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_saisir_fiche) ;
		initialiser () ;
		Bundle recepteurDepaquet = new Bundle ();
	    recepteurDepaquet		= this.getIntent().getExtras();
	    String lesIndentites [] = recepteurDepaquet.getStringArray("paquetInfoIdentite");
	    this.afficheurDeIdvisiteur.setText(lesIndentites[0]) ;
	    this.afficheurDeNomvisiteur.setText(lesIndentites[1] );
	}

	/**
	 * @see initialiser() 
	 */
	private void initialiser() {
		// TODO Auto-generated method stub
		this.afficheurDeIdvisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurIdVisiteurSaisirFiche);
		this.afficheurDeNomvisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurNomVisiteurSaisirFiche) ;
		this.SaisirMois = (EditText) this.findViewById(com.gsb.R.id.edPeriodeSaisirFiche);
		this.bouttonSuivant =(Button) this.findViewById(com.gsb.R.id.bSuivantSaisirFiche);
		this.bouttonSuivant.setOnClickListener(this);
		
		
	}

	/**
	 * @param v View : vue bouton ;
	 * <p> lors du clique du bouton bSuivantSaisirFiche 
	 * <p> les données sont mises en paquet (intent) , démarrage de l'actvité  ConsulFiche
	 * <p> pour la consultation proprement dite
	 * 
	 * 
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case com.gsb.R.id.bSuivantSaisirFiche :
			  String leMoiSaisi = this.SaisirMois.getText().toString();
			  String lesIndentites [] = {this.afficheurDeIdvisiteur.getText().toString(), this.afficheurDeNomvisiteur.getText().toString() } ;
			  Bundle envoiDepaquet = new Bundle () ;
			  envoiDepaquet.putStringArray("paquetInfoIdentite", lesIndentites);
			  envoiDepaquet.putString("laPeriode", leMoiSaisi);
			  Intent object_act = new Intent (SaisirConsulFiche.this , composant.ConsulFiche.class) ;
			  object_act.putExtras(envoiDepaquet);
			  this.startActivity(object_act);
			  
			 
			   
			break ;
		
		
		}
		
	}
	
	
	

}
