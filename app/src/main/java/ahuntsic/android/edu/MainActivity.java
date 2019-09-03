package ahuntsic.android.edu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Attributs
     */

    TextView txtOperation, txtResultat;
    GridLayout grille;
    StringBuilder operation;
    List<Double> nombres;
    List<Character> operateurs;
    Double resultat;


    /**
     * Constructeur
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOperation = (TextView) findViewById(R.id.txtOperation);
        txtResultat = (TextView) findViewById(R.id.txtResultat);
        grille = (GridLayout) findViewById(R.id.grille);
        operation = new StringBuilder();
        nombres = new ArrayList<Double>();
        operateurs = new ArrayList<Character>();
        resultat = 0.0;

        for(int i = 0; i < grille.getChildCount(); i++) {
            Button bouton = (Button) grille.getChildAt(i);
            bouton.setOnClickListener(this);
        }
    }

    /**
     * Afficher la chaîne d'opérations
     */
    public void affOperation() {
        StringBuilder sb = new StringBuilder();
        txtOperation.setText(operation.toString());
    }

    /**
     * Afficher le résultat du calcul
     */
    public void affResultat() {
        if (resultat == 0.0){
            txtResultat.setText(String.format("%.0f", resultat));
        }
        else {
            txtResultat.setText(String.format("%.6f", resultat));
        }
    }

    /**
     * Effacer la chaîne d'opérations
     */
    public void effacerOperation(){
        if(operation.length() > 0){
            operation = new StringBuilder();
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
                operation.append("0");
                if(txtResultat.getText().toString().equals("0")){
                    txtResultat.setText("0");
                }
                else {
                    txtResultat.append("0");
                }
                affOperation();
                break;
            case R.id.btnUn:
                operation.append("1");
                if(txtResultat.getText().toString().equals("0")){
                    txtResultat.setText("1");
                }
                else {
                    txtResultat.append("1");
                }
                affOperation();
                break;
            case R.id.btnDeux:
                operation.append("2");
                if(txtResultat.getText().toString().equals("0")){
                    txtResultat.setText("2");
                }
                else {
                    txtResultat.append("2");
                }
                affOperation();
                break;
            case R.id.btnTrois:
                operation.append("3");
                if(txtResultat.getText().toString().equals("0")){
                    txtResultat.setText("3");
                }
                else {
                    txtResultat.append("3");
                }
                affOperation();
                break;
            case R.id.btnQuatre:
                operation.append("4");
                if(txtResultat.getText().toString().equals("0")){
                    txtResultat.setText("4");
                }
                else {
                    txtResultat.append("4");
                }
                affOperation();
                break;
            case R.id.btnCinq:
                operation.append("5");
                if(txtResultat.getText().toString().equals("0")){
                    txtResultat.setText("5");
                }
                else {
                    txtResultat.append("5");
                }
                affOperation();
                break;
            case R.id.btnSix:
                operation.append("6");
                if(txtResultat.getText().toString().equals("0")){
                    txtResultat.setText("6");
                }
                else {
                    txtResultat.append("6");
                }
                affOperation();
                break;
            case R.id.btnSept:
                operation.append("7");
                if(txtResultat.getText().toString().equals("0")){
                    txtResultat.setText("7");
                }
                else {
                    txtResultat.append("7");
                }
                affOperation();
                break;
            case R.id.btnHuit:
                operation.append("8");
                if(txtResultat.getText().toString().equals("0")){
                    txtResultat.setText("8");
                }
                else {
                    txtResultat.append("8");
                }
                affOperation();
                break;
            case R.id.btnNeuf:
                operation.append("9");
                if(txtResultat.getText().toString().equals("0")){
                    txtResultat.setText("9");
                }
                else {
                    txtResultat.append("9");
                }
                affOperation();
                break;
            case R.id.btnPlus:
                operation.append("+");
                operateurs.add('+');
                nombres.add(Double.parseDouble(txtResultat.getText().toString()));
                txtResultat.setText("0");
                affOperation();
                break;
            case R.id.btnMoins:
                operation.append("-");
                operateurs.add('-');
                nombres.add(Double.parseDouble(txtResultat.getText().toString()));
                txtResultat.setText("0");
                affOperation();
                break;
            case R.id.btnMulti:
                operation.append("*");
                operateurs.add('*');
                nombres.add(Double.parseDouble(txtResultat.getText().toString()));
                txtResultat.setText("0");
                affOperation();
                break;
            case R.id.btnDivi:
                operation.append("/");
                operateurs.add('/');
                nombres.add(Double.parseDouble(txtResultat.getText().toString()));
                txtResultat.setText("0");
                affOperation();
                break;
            case R.id.btnEgal:
                effacerOperation();
                nombres.add(Double.parseDouble(txtResultat.getText().toString()));
                switch(operateurs.get(0)){
                    case '+':
                        resultat = nombres.get(0) + nombres.get(1);
                        break;
                    case '-':
                        resultat = nombres.get(0) - nombres.get(1);
                        break;
                    case '*':
                        resultat = nombres.get(0) * nombres.get(1);
                        break;
                    case '/':
                        resultat = nombres.get(0) / nombres.get(1);
                        break;
                    default:
                        resultat = 0.0;
                }
                if(operateurs.size() > 1){
                    for(int i = 1; i < operateurs.size(); i++){
                        switch(operateurs.get(i)){
                            case '+':
                                resultat = resultat + nombres.get(i + 1);
                                break;
                            case '-':
                                resultat = resultat - nombres.get(i + 1);
                                break;
                            case '*':
                                resultat = resultat * nombres.get(i + 1);
                                break;
                            case '/':
                                resultat = resultat / nombres.get(i + 1);
                                break;
                            default:
                                resultat = 0.0;
                        }
                    }
                }
                nombres.clear();
                operateurs.clear();
                nombres.add(resultat);
                operation = new StringBuilder();
                operation.append(resultat.toString());
                affResultat();
                break;
            case R.id.btnRetour:
                if(operation.length() > 0){
                    operation.setLength(operation.length() - 1);
                    affOperation();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Erreur",Toast.LENGTH_SHORT).show();
                }
               break;
            case R.id.btnEffacer:

                break;
            case R.id.btnEffacerTout:
                effacerOperation();
                resultat = 0.0;
                affOperation();
                affResultat();
                break;
            case R.id.btnSigne:

                break;

            default:
                Toast.makeText(getApplicationContext(),"Touche invalide",Toast.LENGTH_SHORT).show();
        }
    }
}
