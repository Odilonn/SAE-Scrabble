
public class MainScrabble {
    public static void main(String args[]){
        int nbJoueurs;
        
        Ut.afficherSL("Saisissez le nombre de joueurs : ");
        nbJoueurs = Ut.saisirEntier();
        String[] tabprenoms=new String[nbJoueurs];
        for (int i = 0; i<nbJoueurs ; i++) {
            Ut.afficherSL("Saisir joueur : ");
            tabprenoms[i]= Ut.saisirChaine();
        }

        Scrabble s1 = new Scrabble(tabprenoms);
        s1.partie();
    }
}