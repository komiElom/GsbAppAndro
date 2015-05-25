package composant;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View ;
/**
 * 
 * @author komi elom heekpo
 * cette classe permet la suppression de ligne de frais  hors forfait 
 *
 */
public class SuprimerHorsForfait extends Activity implements View.OnClickListener {
	 TextView afficheurNumeroHorsFrais ;
	 EditText SaisirNumero ;
	 Button validerSupression ;
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
		this.validerSupression = (Button) this.findViewById(com.gsb.R.id.bValidationHorsSupprimer) ;
		Bundle  recepteurDePaquet = new Bundle () ;
		recepteurDePaquet = this.getIntent().getExtras();
		this.lesIdentites = recepteurDePaquet.getStringArray("paquetInfoIdentite") ;
		this.leMoisSaisi = recepteurDePaquet.getString("laPeriode");
		this.validerSupression.setOnClickListener(this);
		
		
	}

	/**
	 * @param v View 
	 * <p> au declenchement du bouton bValidationHorsSupprimer ,
	 * <p> l'appel de la methode supprimerHorsforfait() 
	 * @param this.SaisirNumero.getText().toString() : le numero de hors de frais saisir
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub	
		switch (v.getId()) {
		
		case com.gsb.R.id.bValidationHorsSupprimer :

			 GestionnaireBD unGestiBD = new GestionnaireBD(this) ;
			 unGestiBD.ouvrir();
			 unGestiBD.supprimerHorsforfait(this.SaisirNumero.getText().toString()) ;
			 unGestiBD.fermer() ; 
		
			 
			 this.finish();
			break;
		
		}
		
	}

}
