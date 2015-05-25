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
 * @author komi elom heekpo
 * @version 01
 * <p>
 * la class Login estl'activité dédiée à l'interface d'identification  
 * <p>
 *  nécéssaire à l'acces de l'application
 */
public class Login extends Activity implements View.OnClickListener{
	private EditText saisirVisitId , saisirVisiMot ;
	private Button boutonSeconn ;
	/**
	 * la methode onCreate initialise la mise en page (page_login) et tous les variables 
	 * des vues devant figurer cet objet de mise en page
	 * <p>  ceci par l'appel de la methode initialiser ;
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_login);
		initialiser( );
		
		
	}
	/**
	 * methode initialiser () :
	 * <p> implementente toutes les vues Textes, boutton, Edition de Texte
	 * @return void : rien
	 */
	
	private void initialiser() {
		// TODO Auto-generated method stub edIdvisiteurLogin
		this.saisirVisiMot = (EditText) this.findViewById(com.gsb.R.id.edMotDePassLogin) ;
		this.saisirVisitId = (EditText) this.findViewById(com.gsb.R.id.edIdvisiteurLogin);
		this.boutonSeconn = (Button) this.findViewById(com.gsb.R.id.bSeconnecterLogin) ;
	     boutonSeconn.setOnClickListener(this);
	}
/**
 * <p>
 * methode  
 * <p>
 * {@link onClick(View v) }
 * <p>
 * execute les instructions liées au bouton bSeconnecterLogin
 * <p>  verifie les elements saisis envoyant  envoi les données vers la base de donnée qui retoune
 * <p> la confirmation de leur vraissemblance 
 * appel de la methode SeConnecter ()
 * {@link  SeConnecter }
 * @param leIdSaisi,leMotSaisi 
 * @return un tableau : les identites utilisateurs
 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case com.gsb.R.id.bSeconnecterLogin :
			String leIdSaisi = saisirVisitId.getText().toString();
			String leMotSaisi = saisirVisiMot.getText().toString();
			if(leMotSaisi.contentEquals("")) {
			Dialog unDialogue = new Dialog(this);
			unDialogue.setTitle("veiller saisir ID") ;
			TextView  petiteTexte = new TextView(this);
			unDialogue.setContentView(petiteTexte);
			unDialogue.show();
			} if(leMotSaisi.contentEquals("")){
				Dialog unDialogue = new Dialog(this);
				unDialogue.setTitle("veiller saisir le mot de pass") ;
				TextView  petiteTexte = new TextView(this);
				unDialogue.setContentView(petiteTexte);
				unDialogue.show();
			}
			
			else {
				try{
				GestionnaireBD unSystemeBD = new GestionnaireBD(this);
				unSystemeBD.ouvrir();
				String verification [] = unSystemeBD.SeConnecter(leIdSaisi,leMotSaisi );
				unSystemeBD.fermer();
				if (verification != null ) {
				Bundle paquet = new Bundle();
				paquet.putStringArray("paquetInfoIdentite", verification);
				Intent Objecti_sur_act = new Intent(Login.this, composant.Acceuil.class);
				Objecti_sur_act.putExtras(paquet);
				this.startActivity(Objecti_sur_act);
				this.finish();
				}
				} catch(Exception e){
				Dialog unDialogue = new Dialog(this);
				unDialogue.setTitle(e.toString()+" les information ne sont pas correcte"  ) ;
				TextView  petiteVueTexte = new TextView(this);
				unDialogue.setContentView(petiteVueTexte);
				unDialogue.show();
			 }
			}
			break;
		
		}
		
	}
	

}
