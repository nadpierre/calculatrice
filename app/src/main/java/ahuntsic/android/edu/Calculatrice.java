package ahuntsic.android.edu;

import java.util.ArrayList;
import java.util.List;

public class Calculatrice {

    /**
     * Attributs
     */
    private final int MAX_LONGUEUR = 10;
    private List<Double> nombres;
    private List<Character> operateurs;
    private StringBuilder expression;
    private double resultat;
    private boolean btnEgal;

    /**
     * Constructeur
     */
    public Calculatrice(){
        this.nombres = new ArrayList<Double>();
        this.operateurs = new ArrayList<Character>();
        this.expression = new StringBuilder();
        this.resultat = 0;
        this.btnEgal = false;
    }

    /**
     * Accesseurs
     */
    public String getExpression() {
        return expression.toString();
    }
    public String getResultat() {
        String resultat = String.valueOf(this.resultat);
        return (resultat.length() > MAX_LONGUEUR) ? resultat.substring(0, MAX_LONGUEUR) : resultat;
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
        if(this.btnEgal == true && !caractere.toString().matches("[\\+\\-\\*\\/]")){
            this.effacerTout();
        }
        this.btnEgal = false;
        this.expression.append(caractere);
    }

    /**
     * Supprimer le dernier caractère de l'expression
     */
    public void retourner() {
        if(this.expression.length() > 0){
            this.btnEgal = false;
            this.expression.setLength(this.expression.length() - 1);
        }
    }

    /**
     * Decomposer la chaîne d'opérations en nombres et opérateurs
     */
    public boolean decomposer(){
        String chaine = this.expression.toString();
        this.nombres = new ArrayList<Double>();
        this.operateurs = new ArrayList<Character>();

        for(int i = 0; i < chaine.length(); i++){
            if(String.valueOf(chaine.charAt(i)).matches("[\\+\\-\\*\\/]")){
                this.operateurs.add(chaine.charAt(i));
            }
        }

        String[] lesNombres = chaine.split("[\\+\\-\\*\\/]");
        for(int i = 0; i < lesNombres.length; i++){
            try {
                double valeur = Double.parseDouble(lesNombres[i]);
                this.nombres.add(valeur);
            } catch (NumberFormatException e){
                throw new ArithmeticException("Expression invalide");
            }
        }

        return (this.nombres.size() == this.operateurs.size() + 1);
    }

    /**
     * Éffectuer l'opération
     */
    public void calculer() throws ArithmeticException {

        if(!this.decomposer()){
            throw new ArithmeticException("Expression invalide");
        }

        else {
            this.btnEgal = true;
            this.resultat = this.nombres.get(0);
            for(int i = 1; i < this.nombres.size(); i++){
                char operateur = this.operateurs.get(i - 1);
                double valeur = this.nombres.get(i);
                switch (operateur){
                    case '+' :
                        this.resultat += valeur;
                        break;
                    case '-' :
                        this.resultat -= valeur;
                        break;
                    case '*' :
                        this.resultat *= valeur;
                        break;
                    case '/' :
                        this.resultat /= valeur;
                        break;
                }
            }
            this.enregistrerResultat();
        }
    }

    /**
     * Inverser le signe du nombre (opération ou résultat)
     */
    public void signe() throws ArithmeticException {
        if(this.btnEgal == true){
            this.resultat = -this.resultat;
            this.enregistrerResultat();
        }
        else {
            if(!this.decomposer()){
                throw new ArithmeticException("Expression invalide");
            }
            else {
                Double dernierNombre = this.nombres.remove(this.nombres.size() - 1);
                String nombreChaine = dernierNombre.toString();
                this.expression.setLength(this.expression.length() - nombreChaine.length());
                this.expression.append('0');
                this.expression.append('-');
                this.expression.append(dernierNombre);
            }

        }

    }

    /**
     * Calculer la racine carrée
     */
    public void racineCarree() {
        this.resultat = Math.sqrt(this.resultat);
        this.enregistrerResultat();
    }

    /**
     * Convertir le nombre en pourcentage
     */
    public void pourcentage() {
        this.resultat = this.resultat / 100;
        this.enregistrerResultat();
    }

    /**
     * Obtenir le nombre inverse
     */
    public void inverse() throws ArithmeticException{
        if(this.resultat == 0){
            throw new ArithmeticException("Division par 0.");
        }
        else {
            this.resultat = 1 / this.resultat;
            this.enregistrerResultat();
        }
    }

    /**
     * Enregistrer le dernier résultat
     */
    public void enregistrerResultat(){
        this.nombres.clear();
        this.operateurs.clear();
        this.expression.setLength(0);
        this.expression.append(String.valueOf(this.resultat));
    }

    /**
     * Effacer le dernier nombre
     */
    public void effacer() throws ArithmeticException {
        if(!this.decomposer()){
            throw new ArithmeticException("Expression invalide");
        }
        else {
            Double dernierNombre = this.nombres.get(this.nombres.size() - 1);
            String nombreChaine = dernierNombre.toString();
            this.expression.setLength(this.expression.length() - nombreChaine.length());
        }

    }

    /**
     * Effacer le contenu de l'expression et remettre le résultat à 0
     */
    public void effacerTout() {
        this.nombres.clear();
        this.operateurs.clear();
        setResultat(0);
        this.expression.setLength(0);
    }
}
