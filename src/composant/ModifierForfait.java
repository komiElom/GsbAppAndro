package composant;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View ;

public class ModifierForfait  extends Activity  implements View.OnClickListener{
	TextView afficheurIdVisiteur, afficheurNom, afficheurMois ;
	EditText  SaisirEtape, SaisirSejour, SaisirRepas , SaisirKm;
	Button ValiderFiche ;
	String leMoisSaisi ;
	String lesIdentites [] ;

	/**
	 * @see onCreate
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_saisir_forfait) ;
		initialiser() ;
	}


/**
 * @see initialiser
 * implementation des variables de vues de l'objet de mise en page : page_saisir_forfait
 */
	private void initialiser() {
		// TODO Auto-generated method stub
		this.afficheurIdVisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurIDSaisirforfait) ;
		this.afficheurNom = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurNomVisiteurSaisirforfait) ;
		this.afficheurMois = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurPeriodeSaisirforfait) ;
		this.SaisirEtape = (EditText) this.findViewById(com.gsb.R.id.edEtapeSaisirforfait) ;
		this.SaisirSejour = (EditText) this.findViewById(com.gsb.R.id.edHotelSaisirforfait) ;
		this.SaisirRepas = (EditText) this.findViewById(com.gsb.R.id.edRepasSaisirforfait) ;
		this.SaisirKm = (EditText) this.findViewById(com.gsb.R.id.edKmSaisirforfait) ;
		this.ValiderFiche = (Button) this.findViewById(com.gsb.R.id.bValidSaisirforfait) ;
		Bundle recepteurDePaquet = new  Bundle () ;
		recepteurDePaquet = this.getIntent().getExtras() ;
		this.lesIdentites = recepteurDePaquet.getStringArray("paquetInfoIdentite") ;
		this.leMoisSaisi = recepteurDePaquet.getString("laPeriode") ;
		String lesFrais [] = recepteurDePaquet.getStringArray("listeForfait");
		this.afficheurIdVisiteur.setText(lesIdentites [0]) ;
		this.afficheurNom.setText(lesIdentites[1]) ;
		this.afficheurMois.setText(leMoisSaisi);
		this.SaisirEtape.setText(lesFrais [0]);
		this.SaisirKm.setText(lesFrais [1]) ;
		this.SaisirSejour.setText(lesFrais [2]) ;
		this.SaisirRepas.setText(lesFrais [3]) ;
		this.ValiderFiche.setOnClickListener(this) ;
		
		
		
		
		
	}

/**
 * @param v View : 
 * <p>  si le bouton bValidSaisirforfait  est actionné 
 * <p> appel de la methode miseAjourForfait 
 * @param  lesIdentites[0],this.leMoisSaisi,etapSaisi ,sejourSaisi ,repasSaisi, kmSaisi
 * @return false 
 * 
 */

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case com.gsb.R.id.bValidSaisirforfait :
			String etapSaisi = this.SaisirEtape.getText().toString();
			String sejourSaisi = this.SaisirSejour.getText().toString();
			String kmSaisi = this.SaisirKm.getText().toString();
			String repasSaisi = this.SaisirRepas.getText().toString();
			
			boolean saisieUser = true ;
			if (kmSaisi.contentEquals("")) {
				Dialog unDialogue = new Dialog(this)  ;
				TextView unVueDeTexte = new TextView (this) ;
				unDialogue.setContentView(unVueDeTexte);
				unDialogue.setTitle("le nombre de km n'est pas saisi");
				saisieUser = false ;
				unDialogue.show();
			}
			if (sejourSaisi.contentEquals("")) {
				Dialog unDialogue = new Dialog(this)  ;
				TextView unVueDeTexte = new TextView (this) ;
				unDialogue.setContentView(unVueDeTexte);
				unDialogue.setTitle("le nombre de Sejour n'est pas saisi");
				saisieUser = false ;
				unDialogue.show();
				
			}
			if (repasSaisi.contentEquals("")) {
				Dialog unDialogue = new Dialog(this)  ;
				TextView unVueDeTexte = new TextView (this) ;
				unDialogue.setContentView(unVueDeTexte);
				unDialogue.setTitle("le nombre de Repas n'est pas saisi");
				saisieUser = false ;
				unDialogue.show();
				
			} 
			if (etapSaisi.contentEquals("")) {
				Dialog unDialogue = new Dialog(this)  ;
				TextView unVueDeTexte = new TextView (this) ;
				unDialogue.setContentView(unVueDeTexte);
				unDialogue.setTitle("le nombre de d'étape n'est pas saisi");
				saisieUser = false ;
				unDialogue.show();
				
			}
			if( saisieUser != false ) {
				   GestionnaireBD unGestionnaireBD = new GestionnaireBD(this) ;
				    unGestionnaireBD.ouvrir();
				    unGestionnaireBD .miseAjourForfait(lesIdentites[0],this.leMoisSaisi,etapSaisi ,sejourSaisi ,repasSaisi, kmSaisi);
				    unGestionnaireBD.fermer();
				    this.finish();
				
			}
			break;
		
		}
		
	}
	
	
}
