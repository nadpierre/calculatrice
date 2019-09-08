package ahuntsic.android.edu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
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

    final int MAX_LONGUEUR = 11;
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
        String resultat = calculatrice.getResultat();
        String affichage = (resultat.length() > MAX_LONGUEUR) ? resultat.substring(0, MAX_LONGUEUR) : resultat;
        txtResultat.setText(affichage);
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
                calculatrice.saisirChiffre("0");
                afficherExpression();
                break;
            case R.id.btnUn:
                calculatrice.saisirChiffre("1");
                afficherExpression();
                break;
            case R.id.btnDeux:
                calculatrice.saisirChiffre("2");
                afficherExpression();
                break;
            case R.id.btnTrois:
                calculatrice.saisirChiffre("3");
                afficherExpression();
                break;
            case R.id.btnQuatre:
                calculatrice.saisirChiffre("4");
                afficherExpression();
                break;
            case R.id.btnCinq:
                calculatrice.saisirChiffre("5");
                afficherExpression();
                break;
            case R.id.btnSix:
                calculatrice.saisirChiffre("6");
                afficherExpression();
                break;
            case R.id.btnSept:
                calculatrice.saisirChiffre("7");
                afficherExpression();
                break;
            case R.id.btnHuit:
                calculatrice.saisirChiffre("8");
                afficherExpression();
                break;
            case R.id.btnNeuf:
                calculatrice.saisirChiffre("9");
                afficherExpression();
                break;
            case R.id.btnVirgule:
                calculatrice.saisirChiffre(".");
                afficherExpression();
                break;
            case R.id.btnPlus:
                try {
                    calculatrice.saisirOperateur("+");
                    afficherExpression();
                } catch (ArithmeticException e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnMoins:
                try {
                    calculatrice.saisirOperateur("-");
                    afficherExpression();
                } catch (ArithmeticException e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnMulti:
                try {
                    calculatrice.saisirOperateur("*");
                    afficherExpression();
                } catch (ArithmeticException e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDivi:
                try {
                    calculatrice.saisirOperateur("/");
                    afficherExpression();
                } catch (ArithmeticException e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnSigne:
                calculatrice.signe();
                if(calculatrice.getBtnEgal()){
                    afficherResultat();
                }
                else {
                    afficherExpression();
                }
                break;
            case R.id.btnRacine:
                try {
                    calculatrice.racineCarree();
                    if(calculatrice.getBtnEgal()){
                        afficherResultat();
                    }
                    else {
                        afficherExpression();
                    }
                } catch (ArithmeticException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnPourcent:
                calculatrice.pourcentage();
                if(calculatrice.getBtnEgal()){
                    afficherResultat();
                }
                else {
                    afficherExpression();
                }
                break;
            case R.id.btnInverse:
                try {
                    calculatrice.inverse();
                    if(calculatrice.getBtnEgal()){
                        afficherResultat();
                    }
                    else {
                        afficherExpression();
                    }
                } catch (ArithmeticException e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEgal:
                try {
                    calculatrice.evaluer();
                    afficherResultat();
                } catch(ArithmeticException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRetour:
                try {
                    calculatrice.retour();
                    afficherExpression();
                } catch (ArithmeticException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
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
                try {
                    calculatrice.effacerTout();
                    afficherExpression();
                    afficherResultat();
                } catch (ArithmeticException e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(getApplicationContext(),"Touche invalide",Toast.LENGTH_SHORT).show();
        }
    }
}
