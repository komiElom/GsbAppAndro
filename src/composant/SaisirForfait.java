package composant;
import android.app.Activity ;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View ;


/**
 * 
 * @author komi elom heekpo
 * <p> Cette classe permet la saisie des quantites frais forfatisés
 *
 */
public class SaisirForfait extends Activity implements View.OnClickListener{
	
	TextView afficheurDeIdvisiteur , afficheurDeNomvisiteur , afficheurMois;
	EditText  SaisirKm, SaisirSejour, SaisirRepas , SaisirEtape ;
	Button validerficher ;
/**
 * @see onCreate
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_saisir_forfait);
		initialiser();
		Bundle recepteurDepaquet = this.getIntent().getExtras();
		String lesIdentites [] = recepteurDepaquet.getStringArray("paquetInfoIdentite") ;
		String leMoisSaisi = recepteurDepaquet.getString("laPeriode") ;
		this.afficheurDeIdvisiteur.setText(lesIdentites [0]) ;
		this.afficheurDeNomvisiteur.setText(lesIdentites [1]);
		this.afficheurMois.setText(leMoisSaisi);
	
		
		
		
		
		
	}

	/**
	 * @see initialiser ()
	 * <p> implemente la variable des vues de l'objet de la mise en page
	 * 
	 * 
	 * 	 */
	private void initialiser() {
		// TODO Auto-generated method stub
		this.afficheurDeIdvisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurIDSaisirforfait);
		this.afficheurDeNomvisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurNomVisiteurSaisirforfait) ;
		this.afficheurMois = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurPeriodeSaisirforfait );
		this.SaisirKm = (EditText) this.findViewById(com.gsb.R.id.edKmSaisirforfait);
		this.SaisirSejour = (EditText) this.findViewById(com.gsb.R.id.edHotelSaisirforfait);
		this.SaisirRepas = (EditText) this.findViewById(com.gsb.R.id.edRepasSaisirforfait) ;
		this.SaisirEtape = (EditText) this.findViewById(com.gsb.R.id.edEtapeSaisirforfait);
		this.validerficher = (Button)  this.findViewById(com.gsb.R.id.bValidSaisirforfait) ;
		this.validerficher.setOnClickListener(this);
		
		
		
		
	}

	/**
	 * 
	 * <p> 
	 * @param v View : une vue 
	 * <p> s'il s'agit d'une du bouton bValidSaisirforfait :
	 * <p> une verification est faite concernant les saisies puis , les élements sont envoyés à la base de donnée
	 * <p> l'activite encours s'arrete : 
	 * <p> les identités utilisateurs sont mises dans un paquet (intent)
	 * <p> l'activité en cours s'arrete ,l'activité  AjoutHorsF est démarrée pour la saisies des frais hors forfaits
	 * 
	 * 	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case com.gsb.R.id.bValidSaisirforfait :
			String leKmSaisi = this.SaisirKm.getText().toString();
			String leSejourSaisi = this.SaisirSejour.getText().toString() ;
			String leRepasSaisi  = this.SaisirRepas.getText().toString() ;
			String leIDSaisi  = this.afficheurDeIdvisiteur.getText().toString();
			String leMoisSaisi = this.afficheurMois.getText().toString();
			String leEtapeSaisi = this.SaisirEtape.getText().toString();
			String lesIdentites [] = {this.afficheurDeIdvisiteur.getText().toString() , this.afficheurDeNomvisiteur.getText().toString()};
			
			boolean saisieUser = true ;
			if (leKmSaisi.contentEquals("")) {
				Dialog unDialogue = new Dialog(this)  ;
				TextView unVueDeTexte = new TextView (this) ;
				unDialogue.setContentView(unVueDeTexte);
				unDialogue.setTitle("le nombre de km n'est pas saisi");
				saisieUser = false ;
				unDialogue.show();
			}
			if (leSejourSaisi.contentEquals("")) {
				Dialog unDialogue = new Dialog(this)  ;
				TextView unVueDeTexte = new TextView (this) ;
				unDialogue.setContentView(unVueDeTexte);
				unDialogue.setTitle("le nombre de Sejour n'est pas saisi");
				saisieUser = false ;
				unDialogue.show();
				
			}
			if (leRepasSaisi.contentEquals("")) {
				Dialog unDialogue = new Dialog(this)  ;
				TextView unVueDeTexte = new TextView (this) ;
				unDialogue.setContentView(unVueDeTexte);
				unDialogue.setTitle("le nombre de Repas n'est pas saisi");
				saisieUser = false ;
				unDialogue.show();
				
			} 
			if (leEtapeSaisi.contentEquals("")) {
				Dialog unDialogue = new Dialog(this)  ;
				TextView unVueDeTexte = new TextView (this) ;
				unDialogue.setContentView(unVueDeTexte);
				unDialogue.setTitle("le nombre de d'étape n'est pas saisi");
				saisieUser = false ;
				unDialogue.show();
				
			}
			if( saisieUser != false ) {
				try {
					
			    GestionnaireBD unGestionnaireBD = new GestionnaireBD(this) ;
			    unGestionnaireBD.ouvrir();
			    long  verif =  unGestionnaireBD.enregistrerForfait (leIDSaisi, leMoisSaisi,leKmSaisi, leSejourSaisi, leRepasSaisi, leEtapeSaisi ) ;
			    unGestionnaireBD.fermer();
				if (verif != 0) {
				Dialog unDialogue = new Dialog(this) ;
				TextView uneVueDeTexte = new TextView(this) ;
				unDialogue.setContentView(uneVueDeTexte);
				unDialogue.setTitle("forfait " + verif + "" +  "fiche" ) ;
				unDialogue.show();
				  }
				
				
				} catch (Exception e) {
					
				}
				
				Bundle expediteurDepaquet = new Bundle () ;
				expediteurDepaquet.putStringArray("paquetInfoIdentite", lesIdentites);
				expediteurDepaquet.putString("laPeriode", leMoisSaisi);
				Intent object_sur_act = new Intent (SaisirForfait.this , composant.AjoutHorsF.class) ;
				object_sur_act.putExtras(expediteurDepaquet) ;
				this.startActivity(object_sur_act) ;		
				this.finish();
			}
			
			
			break;
		
		
		
		}
		
	}

}
