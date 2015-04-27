package composant;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class AdminSupress extends Activity  implements  View.OnClickListener{
EditText  SaiSirUneAdmin ;
Button ValiderAdmin ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(com.gsb.R.layout.page_admin_supress) ;
		initialiser() ;
	}

	private void initialiser() {
		// TODO Auto-generated method stub
		this.SaiSirUneAdmin = (EditText) this.findViewById(com.gsb.R.id.edSaisieADMIN) ;	
		this.ValiderAdmin = (Button)   this.findViewById(com.gsb.R.id.bSuprimADMIN ) ;
		this.ValiderAdmin.setOnClickListener(this) ;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		
		case com.gsb.R.id.bSuprimADMIN :
			   GestionnaireBD unGest = new GestionnaireBD (this) ;
			   unGest.ouvrir() ;
			   try {
			   unGest.supprimerHorsforfait(this.SaiSirUneAdmin.getText().toString()) ;
			   } catch (Exception e ){
				   
				   
			   }
			   unGest.fermer();
			break ;
		}
		
	}
	

}
