package composant;
import android.app.Activity ;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View ;
public class SaisirForfait extends Activity implements View.OnClickListener{
	
	TextView afficheurDeIdvisiteur , afficheurDeNomvisiteur , afficheurMois;
	EditText  SaisirKm, SaisirSejour, SaisirRepas , SaisirEtape ;
	Button validerficher ;

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
				unDialogue.setTitle("les frais forfaitisés sont bien enregistrés") ;
				unDialogue.show();
				}
				} catch (Exception e) {
					
				}
				this.finish();
			}
			
			
			break;
		
		
		
		}
		
	}

}
