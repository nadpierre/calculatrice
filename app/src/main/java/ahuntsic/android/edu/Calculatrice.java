package ahuntsic.android.edu;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Calculatrice {

    /* ATTRIBUTS */
    private String expression;
    private String nombreTemp;
    private Double resultat;
    private boolean btnEgal;

    /* CONSTRUCTEUR */
    public Calculatrice(){
        setExpression("");
        setNombreTemp("");
        setResultat(0.0);
        setBtnEgal(false);
    }

    /* ACCESSEURS */

    public String getExpression() {
        return expression;
    }

    public String getNombreTemp() {
        return nombreTemp;
    }

    public String getResultat() {
        String affichage = resultat.toString();
        if(resultat == Math.floor(resultat)){
            affichage = affichage.substring(0, affichage.length() -2);//retirer le ".0"
        }
        return affichage;
    }

    public boolean getBtnEgal() {
        return btnEgal;
    }

    /* MUTATEURS */

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setNombreTemp(String nombreTemp) {
        this.nombreTemp = nombreTemp;
    }

    public void setResultat(Double resultat) {
        this.resultat = resultat;
    }

    public void setBtnEgal(boolean btnEgal) {
        this.btnEgal = btnEgal;
    }

    /**
     * Saisir un chiffre
     * @param chiffre
     */
    public void saisirChiffre(String chiffre){
        setBtnEgal(false);
        expression += chiffre;
        nombreTemp += chiffre;
    }

    /**
     * Saisir un operateur
     * @param oper
     * @throws ArithmeticException
     */
    public void saisirOperateur(String oper) throws ArithmeticException {
        if(expression.length() > 0) {
            char dernier = expression.charAt(expression.length() - 1);
            if(!String.valueOf(dernier).matches("[\\+\\-\\*\\/]")){
                setBtnEgal(false);
                expression += oper;
                setNombreTemp("");
            }
            else {
                throw new ArithmeticException("Vous ne pouvez pas saisir deux opérateurs de suite.");
            }
        }
        else {
            throw new ArithmeticException("Vous devez saisir un nombre");
        }
    }

    /**
     * Effacer le dernier caractère de l'expression
     */
    public void retour() throws ArithmeticException {
        if(expression.length() > 0){
            setExpression(expression.substring(0, expression.length() -1));
        }
        else {
            throw new ArithmeticException("Chaîne vide");
        }
    }

    /**
     * Inverser le signe d'un nombre
     */
    public void signe(){
        //Inverser le dernier résultat
        if(btnEgal) {
            setResultat(resultat * -1);
            setExpression(getResultat());
        }
        //Inverser le dernier nombre saisi
        else {
            Double negatif = Double.parseDouble(nombreTemp) * -1;
            String chaineNegatif = negatif.toString();
            setExpression(expression.substring(0, expression.length() - nombreTemp.length()));
            if(negatif == Math.floor(negatif)){
                setNombreTemp(chaineNegatif.substring(0, chaineNegatif.length() - 2));
            }
            else {
                setNombreTemp(chaineNegatif);
            }
            expression += nombreTemp;
        }
    }

    /**
     * Obtenir la racine carrée d'un nombre
     * @throws ArithmeticException
     */
    public void racineCarree() throws ArithmeticException {
        //Inverser le dernier résultat
        if(btnEgal) {
            if(resultat >= 0){
                setResultat(Math.sqrt(resultat));
                setExpression(getResultat());
            }
            else {
                throw new ArithmeticException("Racine carrée d'un nombre négatif");
            }
        }
        //Inverser le dernier nombre saisi
        else {
            Double nombre = Double.parseDouble(nombreTemp);
            if(nombre >= 0){
                Double racine = Math.sqrt(nombre);
                String racineChaine = racine.toString();
                setExpression(expression.substring(0, expression.length() - nombreTemp.length()));
                if(racine == Math.floor(racine)){
                    setNombreTemp(racineChaine.substring(0, racineChaine.length() -2));
                }
                else {
                    setNombreTemp(racineChaine);
                }
                expression += nombreTemp;
            }
            else {
                throw new ArithmeticException("Racine carrée d'un nombre négatif");
            }
        }
    }

    /**
     * Convertir un nombre en pourcentage
     */
    public void pourcentage() {
        //Inverser le dernier résultat
        if(btnEgal) {
            setResultat(resultat / 100);
            setExpression(getResultat());
        }
        //Inverser le dernier nombre saisi
        else {
            Double pourcent = Double.parseDouble(nombreTemp) / 100;
            String pourcentChaine = pourcent.toString();
            setExpression(expression.substring(0, expression.length() - nombreTemp.length()));
            if(pourcent == Math.floor(pourcent)) {
                setNombreTemp(pourcentChaine.substring(0, pourcentChaine.length() - 2));
            }
            else {
                setNombreTemp(pourcentChaine);
            }
            expression += nombreTemp;
        }
    }

    /**
     * Obtenir l'inverse d'un nombre
     * @throws ArithmeticException
     */
    public void inverse() throws ArithmeticException {
        //Inverser le dernier résultat
        if(btnEgal) {
            if(resultat != 0){
                setResultat(1 / resultat);
                setExpression(getResultat());
            }
            else {
                throw new ArithmeticException("Division par zéro");
            }
        }
        //Inverser le dernier nombre saisi
        else {
            Double nombre = Double.parseDouble(nombreTemp);
            if(nombre != 0){
                Double inverse = 1 / nombre;
                String inverseChaine = inverse.toString();
                setExpression(expression.substring(0, expression.length() - nombreTemp.length()));
                if(inverse == Math.floor(inverse)){
                    setNombreTemp(inverseChaine.substring(0, inverseChaine.length() -2));
                }
                else {
                    setNombreTemp(inverseChaine);
                }
                expression += nombreTemp;
            }
            else {
                throw new ArithmeticException("Division par zéro");
            }
        }
    }

    /**
     * Évaluer la chaîne d'opérations
     * @throws ArithmeticException
     */
    public void evaluer() throws ArithmeticException {
        setBtnEgal(true);
        List<Double> nombres = new ArrayList<Double>();
        List<String> operateurs = new ArrayList<String>();

        //Extraire les nombres positifs et négatifs de la chaîne de caractères
        try {
            Pattern regex = Pattern.compile("[0-9]+(\\.[0-9]+)?");
            Matcher regexMatcher = regex.matcher(expression);
            while (regexMatcher.find()) {
                nombres.add(Double.parseDouble(regexMatcher.group()));
            }
        } catch (PatternSyntaxException e) {
            throw new ArithmeticException("Expression invalide");
        }

        //Extraire les opérateurs de la chaîne de caractères
        try {
            Pattern regex = Pattern.compile("(?<=[0-9])[\\+\\-\\*\\/]-?");
            Matcher regexMatcher = regex.matcher(expression);
            while (regexMatcher.find()) {
                operateurs.add(regexMatcher.group());
            }
        } catch (PatternSyntaxException e) {
            throw new ArithmeticException("Expression invalide");
        }

        //L'expression doit débuter et terminer par un nombre
        if(nombres.size() != operateurs.size() + 1){
            throw new ArithmeticException("Expression invalide");
        }

        //Effectuer le calcul
        resultat = nombres.get(0);
        for(int i = 1; i < nombres.size(); i++){
            String operateur = operateurs.get(i - 1);
            Double valeur = nombres.get(i);
            switch (operateur){
                case "+":
                    this.resultat += valeur;
                    break;
                case "+-":
                    this.resultat -= valeur;
                    break;
                case "-" :
                    this.resultat -= valeur;
                    break;
                case "--" :
                    this.resultat += valeur;
                    break;
                case "*" :
                    this.resultat *= valeur;
                    break;
                case "*-" :
                    this.resultat *= valeur * -1;
                    break;
                case "/" :
                    if (valeur != 0){
                        this.resultat /= valeur;
                    }
                    else {
                        throw new ArithmeticException("Division par zéro");
                    }
                    break;
                case "/-" :
                    if (valeur != 0){
                        this.resultat /= valeur * -1;
                    }
                    else {
                        throw new ArithmeticException("Division par zéro");
                    }
                    break;
                default:
                    throw new ArithmeticException("Expression invalide");

            }
        }
        setExpression(getResultat());
    }

    /**
     * Effacer le dernier nombre saisi
     * @throws ArithmeticException
     */
    public void effacer() throws ArithmeticException {
        if(!nombreTemp.isEmpty()){
            setExpression(expression.substring(0, expression.length() - nombreTemp.length()));
            setNombreTemp("");
            setBtnEgal(false);
        }
        else {
            throw new ArithmeticException("Dernier nombre déjà effacé");
        }
    }

    /**
     * Effacer la chaîne d'opérations et le résultat
     */
    public void effacerTout() {
        setExpression("");
        setResultat(0.0);
        setNombreTemp("");
        setBtnEgal(false);
    }
}
