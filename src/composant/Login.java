package composant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class Login extends Activity implements View.OnClickListener{
	private EditText saisirVisitId , saisirVisiMot ;
	private Button boutonSeconn ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_login);
		initialiser( );
		
		
	}
	
	private void initialiser() {
		// TODO Auto-generated method stub edIdvisiteurLogin
		this.saisirVisiMot = (EditText) this.findViewById(com.gsb.R.id.edMotDePassLogin) ;
		this.saisirVisitId = (EditText) this.findViewById(com.gsb.R.id.edIdvisiteurLogin);
		this.boutonSeconn = (Button) this.findViewById(com.gsb.R.id.bSeconnecterLogin) ;
	     boutonSeconn.setOnClickListener(this);
	}

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
				unDialogue.setTitle(" les information ne sont pas correcte" ) ;
				TextView  petiteVueTexte = new TextView(this);
				petiteVueTexte.setText(e.toString());
				unDialogue.setContentView(petiteVueTexte);
				unDialogue.show();
				}
			}
			break;
		
		}
		
	}
	

}
