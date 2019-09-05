package ahuntsic.android.edu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Attributs
     */

    TextView txtExpression, txtResultat;
    GridLayout grille;
    Calculatrice calculatrice;

    /**
     * Constructeur
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtExpression = (TextView) findViewById(R.id.txtExpression);
        txtResultat = (TextView) findViewById(R.id.txtResultat);
        grille = (GridLayout) findViewById(R.id.grille);
        calculatrice = new Calculatrice();
        ajouterListener();
        afficherResultat();
    }

    /**
     * Afficher l'expression dans le premier écran
     */
    public void afficherExpression(){
        txtExpression.setText(calculatrice.getExpression());
    }

    /**
     * Afficher le résultat dans le deuxième écran
     */
    public void afficherResultat(){
        txtResultat.setText(calculatrice.getResultat());
    }


    /**
     * Ajouter le listener de boutons
     */
    public void ajouterListener(){
        for(int i = 0; i < grille.getChildCount(); i++) {
            Button bouton = (Button) grille.getChildAt(i);
            bouton.setOnClickListener(this);
        }
    }

    /**
     * Listener de boutons
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnZero:
                calculatrice.saisir('0');
                afficherExpression();
                break;
            case R.id.btnUn:
                calculatrice.saisir('1');
                afficherExpression();
                break;
            case R.id.btnDeux:
                calculatrice.saisir('2');
                afficherExpression();
                break;
            case R.id.btnTrois:
                calculatrice.saisir('3');
                afficherExpression();
                break;
            case R.id.btnQuatre:
                calculatrice.saisir('4');
                afficherExpression();
                break;
            case R.id.btnCinq:
                calculatrice.saisir('5');
                afficherExpression();
                break;
            case R.id.btnSix:
                calculatrice.saisir('6');
                afficherExpression();
                break;
            case R.id.btnSept:
                calculatrice.saisir('7');
                afficherExpression();
                break;
            case R.id.btnHuit:
                calculatrice.saisir('8');
                afficherExpression();
                break;
            case R.id.btnNeuf:
                calculatrice.saisir('9');
                afficherExpression();
                break;
            case R.id.btnVirgule:
                calculatrice.saisir('.');
                afficherExpression();
                break;
            case R.id.btnSigne:
                try {
                    calculatrice.signe();
                    afficherExpression();
                    afficherResultat();
                } catch (ArithmeticException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnPlus:
                calculatrice.saisir('+');
                afficherExpression();
                break;
            case R.id.btnMoins:
                calculatrice.saisir('-');
                afficherExpression();
                break;
            case R.id.btnMulti:
                calculatrice.saisir('*');
                afficherExpression();
                break;
            case R.id.btnDivi:
                calculatrice.saisir('/');
                afficherExpression();
                break;
            case R.id.btnEgal:
                try {
                    calculatrice.calculer();
                    afficherResultat();
                } catch (ArithmeticException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRacine:
                calculatrice.racineCarree();
                afficherExpression();
                afficherResultat();
                break;
            case R.id.btnPourcent:
                calculatrice.pourcentage();
                afficherExpression();
                afficherResultat();
                break;
            case R.id.btnInverse:
                try {
                    calculatrice.inverse();
                    afficherExpression();
                    afficherResultat();
                } catch (ArithmeticException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRetour:
                calculatrice.retourner();
                afficherExpression();
               break;
            case R.id.btnEffacer:
                try {
                    calculatrice.effacer();
                    afficherExpression();
                } catch (ArithmeticException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEffacerTout:
                calculatrice.effacerTout();
                afficherExpression();
                afficherResultat();
                break;
            default:
                Toast.makeText(getApplicationContext(),"Touche invalide",Toast.LENGTH_SHORT).show();
        }
    }
}
