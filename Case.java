import java.util.Scanner; 
import java.lang.*;


public class Case {
    private int couleur;
    private char lettre;
    //En plus de la couleur, vous pourrez définir les attributs de votre choix, mais vous devrez coder les méthodes suivantes.


    public Case (int uneCouleur){
        /*** pré-requis : uneCouleur est un entier entre 1 et 5* 
         * action : constructeur de Case
        * */
        if ((uneCouleur>=1)&&(uneCouleur<=5)) {
            this.couleur=uneCouleur;
        }
    }

    public int getCouleur (){
        /*** résultat : la couleur de this, un nombre entre 1 et 5
        * */
        return (this.couleur);
     }

    public char getLettre (){
        /*** pré-requis : cette case est recouverte*/
        char a;
        a=' ';
        if (this.estRecouverte()==true){
            a = this.lettre;
        }
        return a;
    }

    public void setLettre (char let){
        /*** pré-requis : let est une lettre majuscule
        * */
        if (Ut.estUneMajuscule(let)){
            this.lettre=let;
        }
    }

    public boolean estRecouverte (){
        /*** résultat : vrai ssi la case est recouverte par une lettre
        */
        return (Ut.estUneMajuscule(this.lettre));
    }

    public String toString(){
        return ("Couleur = " + this.couleur + ", Lettre = " + this.lettre);
    }

}
