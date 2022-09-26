import java.util.Scanner; 
import java.lang.*;

class mainTest { // Classe de test de  et contenant la fonction principale

    public static void main(String args[]){
        String[] tab={"odincul","homosexuel","chibre"};
        Scrabble s1= new Scrabble(tab);
        //MEE m1= new MEE(26);
        //int[] tab1={1,5,0};
        //MEE m1=new MEE(tab1);
        //Joueur j1= new Joueur("Montana");
        //Ut.afficher(m1.toString());
        //Ut.afficher(j1.AfficherChevalet());
        //Ut.afficher(j1.toString());
        //int[] tab={0,1,2,3,5,4,8,6,2,1};
        //MEE m2=new MEE(tab);
        //Ut.afficherSL(j1.AfficherChevalet());
        //j1.prendJetons(m1,4);
        //Ut.afficherSL(j1.AfficherChevalet());

        Ut.afficher(s1.toString());
        s1.partie();
        //Ut.afficherSL(j1.AfficherChevalet());

    }
}