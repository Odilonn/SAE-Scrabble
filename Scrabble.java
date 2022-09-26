public class Scrabble {
    
    private Joueur[] joueurs;
    private Plateau plateau;
    private int numJoueur;
    private MEE sac;
    private static int [] nbJetons = {9, 2, 2, 3, 15, 2, 2, 2, 8, 1, 1, 5, 3, 6, 6, 2, 1, 6, 6, 7, 6, 2, 1, 1, 1, 1, 2};
    //Nombre d'exemplaires des lettres présentes dans le sac
    private static int [] nbPointsJeton = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 10, 1, 2, 1, 1, 3, 8, 1, 1, 1, 1, 4, 10, 10, 10, 10,0};
    //Un jeton est codé par le rang de 0 à 25 de la lettre qu’il contient dans l’ordre alphabétique (0 pour ’A’ et 25 pour ’Z’). On stocke le nombre de points rapporté par chaque jeton (lettre) dans un tableau nbPointsJeton indicé de 0 à 25.

    public Scrabble(String[] nomJoueurs){
        joueurs = new Joueur [nomJoueurs.length];
        for (int i = 0; i < nomJoueurs.length; i++){
          Joueur joueur = new Joueur (nomJoueurs[i]);
          this.joueurs[i] = joueur; // Création des Joueurs
        }     
      this.numJoueur = Ut.randomMinMax(0,nomJoueurs.length-1);
      this.plateau = new Plateau();
      this.sac = new MEE(nbJetons);
      
    }

    public String toString(){
        String affichage="\n ------------------------------------------ Scrabble -------------------------------------------\n";
        affichage+=this.plateau.toString();                      
        affichage+="\n -----------------------------------------------------------------------------------------------\n";
        return affichage;
    }

    public void partie(){
      int a=0, x=0;
      Ut.afficherSL("\n -----------------------------------------------------------------------------------------------\n");
      Ut.afficherSL("\n   " + this.joueurs[numJoueur].getNom() +" commence la partie : \n");
      for (int i = 0; i < this.joueurs.length; i++){
          this.joueurs[i].prendJetons(this.sac, 7);
        }   //Chaque joueur pioche 7 lettres dans le sac.
        Ut.afficherSL(this.toString());
        Ut.afficherSL("\n   C'est au tour de " + this.joueurs[this.numJoueur].toString() +"\n");
      int tourSuiv = 0;

      while (tourSuiv < this.joueurs.length){

          Ut.afficherSL("   Le score de "+this.joueurs[numJoueur].getNom() +" est de "+joueurs[numJoueur].getScore());
          int action = this.joueurs[numJoueur].joue(this.plateau, this.sac, nbPointsJeton);
          if (action == 1 && this.sac.estVide()){
              for (int i = 0; i < this.joueurs.length; i++){
                  this.joueurs[numJoueur].ajouteScore(this.joueurs[i].nbPointsChevalet(nbPointsJeton));
                  this.joueurs[i].ajouteScore(- this.joueurs[i].nbPointsChevalet(nbPointsJeton));
                }
              break;
            }
          else if (action == -1){
              tourSuiv++;
            }
          else{
              tourSuiv = 0;
            }
          numJoueur++;
          if (numJoueur >= joueurs.length){
              numJoueur = 0;
            }
            Ut.afficherSL(this.toString());
            Ut.afficherSL("\n   C'est au tour de " + this.joueurs[this.numJoueur].toString() +"\n");
          }   
      if (tourSuiv == joueurs.length){
            Ut.afficherSL(" Tous les joueurs ont passes leur tour ! ");
            for (int i = 0; i < this.joueurs.length; i++){
                joueurs[i].ajouteScore(- this.sac.sommeValeurs(nbPointsJeton));
              }
          }

      Ut.afficherSL("\n ------------------------------------------ FIN DE PARTIE -------------------------------------------\n");
      Ut.afficherSL("-- Affichages des scores : --\n");
      for (int i = 0; i < joueurs.length; i++){
          Ut.afficher("     "+this.joueurs[i].getNom()+ " a "+this.joueurs[i].getScore()+" points.\n");

        }
      for(int i=0; i < joueurs.length;i++){
          if(this.joueurs[i].getScore() > a){
          a=this.joueurs[i].getScore();
          x=i;
        }

      }
      Ut.afficher("\n-- Felicitations " + this.joueurs[x].getNom() + " d'avoir remporte la partie ! --\n"); 
        
  }
}

