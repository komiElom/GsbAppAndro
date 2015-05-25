package composant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View ;
/**
 * 
 * @author komi  elom heekpo
 * <p>  la classse SaisirFiche est l'actvité de l'interface 
 * <p> d'une saisie d'une nouvelle fiche 
 *
 */
public class SaisirFiche extends Activity  implements View.OnClickListener {
	TextView afficheurDeIdvisiteur , afficheurDeNomvisiteur ;
	Button bouttonSuivant ;
	EditText SaisirMois ;
/**
 * @see onCreate
 * appel de la méthode initialiser ()  implemente les variables des vues de la mise en pages
 * <p> vue de texte , boutton, Zone de Saisie
 * <p> implementation des paquets reçues de l'activité précédente
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_saisir_fiche);
		initialiser ();
		Bundle recepteurDepaquet = new Bundle ();
	    recepteurDepaquet		= this.getIntent().getExtras();
	    String lesIndentites [] = recepteurDepaquet.getStringArray("paquetInfoIdentite");
	    this.afficheurDeIdvisiteur.setText(lesIndentites[0]) ;
	    this.afficheurDeNomvisiteur.setText(lesIndentites[1] );
	}
	/**
	 *  @see initialiser () 
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
 *{@link onClick(View v) }
 *<p>
 *@param V View : la vue actionnée
 *<p>
 * si le bouton bSuivantSaisirFiche est actionné :
 * <p> soit une fiche du mois existe déjà dans ce cas 
 * <p> une redirection vers l'activité AjoutHorsF est faite 
 * <p> soit aucune fiche n'existe 
 * <p> une redirection vers l'activité est faite vers SaisirForfait pour la saisie  de la période 
 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case com.gsb.R.id.bSuivantSaisirFiche:
			String lemoisSaisi = this.SaisirMois.getText().toString();
		   Bundle envoiDepaquet = new Bundle ();
			String lesIndentites []  = { afficheurDeIdvisiteur.getText().toString(), afficheurDeNomvisiteur.getText().toString()} ;
			envoiDepaquet.putStringArray("paquetInfoIdentite", lesIndentites );
			envoiDepaquet.putString("laPeriode", lemoisSaisi);
			 String dejaSaisi =  "" ;
			try {
			 GestionnaireBD unGestionnaireBD = new GestionnaireBD(this) ;
			 unGestionnaireBD.ouvrir();
			 dejaSaisi = unGestionnaireBD.existeFrais (afficheurDeIdvisiteur.getText().toString() , lemoisSaisi) ;
			 unGestionnaireBD.fermer();
				 if( !dejaSaisi.contentEquals("") ){
					 
						Intent objectif_sur_Act = new Intent (SaisirFiche.this, composant.AjoutHorsF.class);
					   objectif_sur_Act.putExtras(envoiDepaquet);
					    this.startActivity(objectif_sur_Act);
					   this.finish();
			     } else {
			    	 Intent objectif_sur_Act = new Intent (SaisirFiche.this, composant.SaisirForfait.class);
				     objectif_sur_Act.putExtras(envoiDepaquet);
				     this.startActivity(objectif_sur_Act);
				     this.finish();	
			     }
			}catch (Exception e){
			      Intent objectif_sur_Act = new Intent (SaisirFiche.this, composant.SaisirForfait.class);
			     objectif_sur_Act.putExtras(envoiDepaquet);
			     this.startActivity(objectif_sur_Act);
			     this.finish();	
				} 
			 
			 
			break ;
		
		}
		
		
	}

}
