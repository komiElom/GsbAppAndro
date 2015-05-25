package composant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
/**
 * 
 * @author komi  elom heekpo
 * <p> cette classe  permet d 'indiquer le numero de hors forfait à modifier
 *
 */
public class ChoixDuHorsForfait   extends Activity implements View.OnClickListener{
	 TextView afficheurNumeroHorsFrais ;
	 EditText SaisirNumero ;
	 Button validerModification;
	String leMoisSaisi ;
	String lesIdentites  [];
	
	/**
	 * @see onCreate
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_confirmer_hors_forfait) ;
		initialiser () ;
	}

	/**
	 * @see initialiser 
	 */
	private void initialiser() {
		// TODO Auto-generated method stub
		this.SaisirNumero = (EditText) this.findViewById(com.gsb.R.id.edSaisirNumerohorsSupprimer) ;
		this.validerModification = (Button) this.findViewById(com.gsb.R.id.bValidationHorsSupprimer) ;
		Bundle  recepteurDePaquet = new Bundle () ;
		recepteurDePaquet = this.getIntent().getExtras();
		this.lesIdentites = recepteurDePaquet.getStringArray("paquetInfoIdentite") ;
		this.leMoisSaisi = recepteurDePaquet.getString("laPeriode");
		this.validerModification.setOnClickListener(this);
		
		
		
	}
/**
 * @param v View 
 * <p> au declenchement du bouton bValidationHorsSupprimer  qui est la vue de l'objet de mise page 
 * <p> page_confirmer_hors_forfait commune à  l'activité ChoixDuHorsForfait / SupprimerHorsForfait
 * <p> mise en paquet  du numero saisi 
 * <p> l'activite ModifierHorsForfait demarre
 * <p> l'activite en cours esr arrêtée
 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		
		case com.gsb.R.id.bValidationHorsSupprimer :
			String numHorsForfait = this.SaisirNumero.getText().toString();
			if(!numHorsForfait.contentEquals("")) {
			Bundle envoiDePaquet = new Bundle ()  ;
			envoiDePaquet.putStringArray("paquetInfoIdentite", this.lesIdentites) ;
			envoiDePaquet.putString("laPeriode", this.leMoisSaisi) ;
			envoiDePaquet.putString("numeroHorsF", numHorsForfait ) ;
			Intent objectif  = new Intent (ChoixDuHorsForfait.this, composant.ModifierHorsForfait.class) ;
			objectif.putExtras(envoiDePaquet) ;
			this.startActivity(objectif) ;
			this.finish() ;
			} else {
				Dialog unDial = new Dialog(this) ;
				TextView uneVue = new TextView(this) ;
				unDial.setContentView(uneVue);
				unDial.setTitle("veuiler saisir un numero ") ;
				
				
			}
			break;
		}
		
	}

}
