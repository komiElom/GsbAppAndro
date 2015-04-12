package composant;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
public class Admin extends  Activity implements View.OnClickListener{
private EditText SaisirPrenom ,SaisirNom, SaisirID,SaisirMotDePass , SaisirConfirm ;
private Button SInscrire ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_admin_inscription);
		initialiser ();
		
	}

	private void initialiser() {
		// TODO Auto-generated method stub
	this.SaisirPrenom = (EditText) this.findViewById(com.gsb.R.id.edPrenomInscriAdmin);
	this.SaisirNom = (EditText) this.findViewById(com.gsb.R.id.edNomInscriAdmin);
	this.SaisirID = (EditText) this.findViewById(com.gsb.R.id.edAttibueIDVisiteurAdmin);
	this.SaisirMotDePass = (EditText) this.findViewById(com.gsb.R.id.edAttribueMotDePassAdmin);
	this.SaisirConfirm = (EditText) this.findViewById(com.gsb.R.id.edAttribueConfirmationAdmin);
	this.SInscrire = (Button) this.findViewById(com.gsb.R.id.BValidationInscriAdmin);
	this.SInscrire.setOnClickListener(this);	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
				case com.gsb.R.id.BValidationInscriAdmin :
					String lePrenom = this.SaisirPrenom.getText().toString();
					String  leNom = this.SaisirNom.getText().toString();
					String  leID = this.SaisirID.getText().toString();
					String motPassUn = this.SaisirMotDePass.getText().toString();
					String motPassDeux = this.SaisirConfirm.getText().toString();
					if (lePrenom.contentEquals("")){
						
						Dialog unDialogue = new Dialog(this);
						TextView  petiteVueTexte = new TextView(this);
						unDialogue.setContentView(petiteVueTexte);						
						unDialogue.setTitle("le prenom n'est pas saisi") ;
						unDialogue.show();
					}
		
					if (leNom.contentEquals("")){
						Dialog unDialogue = new Dialog(this);
						TextView  petiteVueTexte = new TextView(this);
						unDialogue.setContentView(petiteVueTexte);						
						unDialogue.setTitle("le nom n'est pas saisi") ;
						unDialogue.show();
						
					}	
					if (leID.contentEquals("")){
						Dialog unDialogue = new Dialog(this);
						TextView  petiteVueTexte = new TextView(this);
						unDialogue.setContentView(petiteVueTexte);						
						unDialogue.setTitle("le ID n'est pas saisi") ;
						unDialogue.show();
						
					}
					if (motPassUn.contentEquals("")){
						Dialog unDialogue = new Dialog(this);
						TextView  petiteVueTexte = new TextView(this);
						unDialogue.setContentView(petiteVueTexte);						
						unDialogue.setTitle("le mot de pass n'est pas saisi") ;
						unDialogue.show();
						
					}
					if(motPassUn.contentEquals(motPassDeux)) {
						
						try {
							GestionnaireBD unSystemeBD = new GestionnaireBD (this);
							unSystemeBD.ouvrir();
							unSystemeBD.inscriptionVisiteur(leID, lePrenom, leNom, motPassUn);
							Dialog unDialogue = new Dialog(this);
						 	TextView  petiteVueTexte = new TextView(this);
							unDialogue.setContentView(petiteVueTexte);						
						    unDialogue.setTitle("visiteur bien enregisté") ;
						    unDialogue.show();
							unSystemeBD.fermer();
							this.SaisirNom.setText("");
							this.SaisirPrenom.setText("");
							this.SaisirID.setText("");
							this.SaisirMotDePass.setText("");
							this.SaisirConfirm.setText("");
						} catch( Exception e){
								String erreur = e.toString();
							    Dialog unDialogue = new Dialog(this);
							 	TextView  petiteVueTexte = new TextView(this);
								unDialogue.setContentView(petiteVueTexte);						
							    unDialogue.setTitle(erreur ) ;
							    unDialogue.show();
							
						}
					
						
						 
					}else{
						Dialog unDialogue = new Dialog(this);
						TextView  petiteVueTexte = new TextView(this);
						unDialogue.setContentView(petiteVueTexte);						
						unDialogue.setTitle("mot de pass non identique") ;
						unDialogue.show();
					}
			break ;
		
		}
		
		
	}
	
	
	
	
	

}
