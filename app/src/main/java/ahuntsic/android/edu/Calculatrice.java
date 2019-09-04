package ahuntsic.android.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Calculatrice {

    /**
     * Attributs
     */
    private StringBuilder expression;
    private double resultat;
    private double dernierResultat;

    /**
     * Constructeur
     */
    public Calculatrice(){
        expression = new StringBuilder();
        resultat = 0;
    }

    /**
     * Accesseurs
     */
    public String getExpression() {
        return expression.toString();
    }
    public String getResultat() {
        String resultat = String.valueOf(this.resultat);
        return (resultat.length() > 10) ? resultat.substring(0, 10) : resultat;
    }

    /**
     * Mutateur
     */
    public void setResultat(double resultat) {
        this.resultat = resultat;
    }

    /**
     * Former l'opération à évaluer
     * @param caractere
     */
    public void saisir(Character caractere){
        expression.append(caractere);
    }

    /**
     * Supprimer le dernier caractère de l'expression
     */
    public void retourner() {
        if(expression.length() > 0){
            expression.setLength(expression.length() - 1);
        }
    }

    /**
     * Éffectuer l'opération
     */
    public void calculer() throws ArithmeticException, NumberFormatException {
        String chaine = this.expression.toString();
        List<Double> nombres = new ArrayList<Double>();
        List<Character> operateurs = new ArrayList<Character>();

        for(int i = 0; i < chaine.length(); i++){
            if(String.valueOf(chaine.charAt(i)).matches("[\\+\\-\\*\\/]")){
                operateurs.add(chaine.charAt(i));
            }
        }

        String[] lesNombres = chaine.split("[\\+\\-\\*\\/]");
        for(int i = 0; i < lesNombres.length; i++){
            try {
                double valeur = Double.parseDouble(lesNombres[i]);
                nombres.add(valeur);
            } catch (NumberFormatException e){
                throw new ArithmeticException("Expression invalide");
            }
        }

        if(nombres.size() != operateurs.size() + 1){
            throw new ArithmeticException("Expression invalide");
        }

        else {
            this.resultat = nombres.get(0);
            for(int i = 1; i < nombres.size(); i++){
                char operateur = operateurs.get(i - 1);
                double valeur = nombres.get(i);
                switch (operateur){
                    case '+' :
                        this.resultat += valeur;
                        break;
                    case '-' :
                        resultat -= valeur;
                        break;
                    case '*' :
                        resultat *= valeur;
                        break;
                    case '/' :
                        resultat /= valeur;
                        break;
                }
            }
        }
    }

    /**
     * Calculer la racine carrée
     */
    public void racineCarree() {
        this.resultat = Math.sqrt(this.resultat);
    }

    /**
     * Convertir le nombre en pourcentage
     */
    public void pourcentage() {
        this.resultat = this.resultat / 100;
    }

    /**
     * Obtenir le nombre inverse
     */
    public void inverse() {
        this.resultat = 1 / this.resultat;
    }

    /**
     * Effacer le contenu de l'expression et remettre le résultat à 0
     */
    public void effacerTout() {
        setResultat(0);
        expression.setLength(0);
    }
}
