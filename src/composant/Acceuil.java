package composant;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class Acceuil extends Activity implements View.OnClickListener {
	
	TextView AfficheurIdVisiteur , afficheurNomVisiteur;
	Button bouttonCreerFiche   , bouttonConsulterFiche ;

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

	private void initialiser() {
		// TODO Auto-generated method stub
		AfficheurIdVisiteur  = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurAcceuil);
		afficheurNomVisiteur = (TextView) this.findViewById(com.gsb.R.id.tvAfficheurNomVisiteurAcceuil);
		this.bouttonConsulterFiche = (Button) this.findViewById(com.gsb.R.id.bconsulterFAcceuil);
		this.bouttonCreerFiche = (Button) this.findViewById(com.gsb.R.id.bCreerFAcceuil);
		this.bouttonConsulterFiche.setOnClickListener(this);
		this.bouttonCreerFiche.setOnClickListener(this);		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case com.gsb.R.id.bCreerFAcceuil:
			break;
		case com.gsb.R.id.bconsulterFAcceuil:
			break;
		
		
		}
	}
	
	

}
