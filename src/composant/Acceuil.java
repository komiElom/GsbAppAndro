package composant;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

/**
 * @author komi elom  heekpo
 * <p>
 * Cette class  "Acceuil" presente  la page d'acceuil ;
 * <p>
 * la page d'acceuil montre à l'utilisateur les principales tâches de l'application
 * <p>
 * Saisir   / consulter les notes de frais
 * 
 */
public class Acceuil extends Activity implements View.OnClickListener {
	
	TextView AfficheurIdVisiteur , afficheurNomVisiteur;
	Button bouttonCreerFiche   , bouttonConsulterFiche ;
/**
 * @see oncCreate
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_acceuil);
		initialiser();
		//
		Bundle recepteurDepaquet = this.getIntent().getExtras();
		String lesIdentites [] = recepteurDepaquet.getStringArray("paquetInfoIdentite") ;
		 afficheurNomVisiteur.setText(lesIdentites[0]);
		AfficheurIdVisiteur.setText(lesIdentites[1]) ;
		
	}
	/**
	 * @see {@link initialiser()}
	 * @return 
	 */

	private void initialiser() {
		// TODO Auto-generated method stub
		AfficheurIdVisiteur  = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurAcceuil);
		afficheurNomVisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurNomVisiteurAcceuil);
		this.bouttonConsulterFiche = (Button) this.findViewById(com.gsb.R.id.bconsulterFAcceuil);
		this.bouttonCreerFiche = (Button) this.findViewById(com.gsb.R.id.bCreerFAcceuil);
		this.bouttonConsulterFiche.setOnClickListener(this);
		this.bouttonCreerFiche.setOnClickListener(this);		
		
	}

	/**
	 * {@link onClick(View v) }
	 * <p>
	 * @param v View : une vue 
	 * <p> 
	 * s'execute si le bouton bCreerFAcceuil a été cliqué
	 * <p> les identités de l'utilisateur sont  affectés dans un paquet (intend )
	 * <p>  l'actvité SaisirFiche est démarrée avec le paquet des identités utilisateur
	 * <p> ou sinon si le bouton bconsulterFAcceuil est cliqué   alors  
	 * <p>  c'est l'actvité SaisirConsulFiche  démarre 
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case com.gsb.R.id.bCreerFAcceuil:
			Bundle envoiPaquet = new Bundle () ;
			String lesIdentites [] = {AfficheurIdVisiteur.getText().toString(),afficheurNomVisiteur.getText().toString() }; 
			envoiPaquet.putStringArray("paquetInfoIdentite", lesIdentites) ;
			Intent object_sur_act = new Intent (Acceuil.this, composant.SaisirFiche.class) ;
			object_sur_act.putExtras(envoiPaquet);
			this.startActivity(object_sur_act);
			
			break;
		case com.gsb.R.id.bconsulterFAcceuil:;
		   Bundle envoiPaquet_b = new Bundle () ;
		   String lesIdentites_b [] = {AfficheurIdVisiteur.getText().toString(),afficheurNomVisiteur.getText().toString() }; 
		   envoiPaquet_b.putStringArray("paquetInfoIdentite", lesIdentites_b) ;
		   Intent objectif_sur_act_b = new Intent (Acceuil.this ,composant.SaisirConsulFiche.class) ;
		   objectif_sur_act_b.putExtras(envoiPaquet_b);
		   this.startActivity(objectif_sur_act_b);
		   break;
		
		
		}
	}
	
	

}
