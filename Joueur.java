public class Joueur {
    //int [] tab = {18,0,11,20,19,0,0}; // TEST
    private String nom;
    private MEE chevalet = new MEE(27); 
    private int score = 0;

    public Joueur(String unNom){
        this.nom = unNom;
    }

    public String toString(){

        return "Joueur : " + this.nom + ", score : " + this.score + ", chevalet : "+ this.AfficherChevalet();
    }

    public String AfficherChevalet()    {

        String c = "";
        String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int nb;
        for (int i = 0; i < this.chevalet.getTabFreq().length; i++){
                nb = this.chevalet.getTabFreq()[i];
                for (int compteur = 0; compteur < nb; compteur++){
                        c += a.charAt(i) + " ";
                    }
            }

        return c;
    }

    public int getScore(){
        return this.score;
    }
    public String getNom(){
        return this.nom;
    }

    public void ajouteScore(int nb){
        this.score+=nb;
    }

    public int nbPointsChevalet(int[] nbPointsJet){
        return (this.chevalet.sommeValeurs(nbPointsJet));
    }

    
    public void prendJetons(MEE s, int nbJetons){
        s.transfereAleat(this.chevalet, nbJetons);      
    }

    public int joue(Plateau p, MEE s, int[] nbPointsJet){
        int decision, fin = 0;
        boolean decisionbool=true;

            while(decisionbool!=false){
                Ut.afficherSL("Saisissez 2 pour passer votre tour : ");
                Ut.afficherSL("Saisissez 1 pour echanger vos jetons : ");
                Ut.afficherSL("Saisissez 0 pour placer votre mot : ");
                decision = Ut.saisirEntier();

                if (this.chevalet.estVide()==true) {
                    return 1;
                }

                else if(decision==0){
                    if (this.joueMot(p,s,nbPointsJet)==true) {
                        decisionbool=false;
                    }
                } 

                else if(decision == 1){
                    this.echangeJetons(s);
                    Ut.afficherSL("\n          Nouveau Chevalet :\n" + AfficherChevalet() +"\n");
                }

                else if(decision == 2) {
                    return -1;
                }

                else{
                    decisionbool= true;

                }

            }
        return fin;
    }

    public boolean joueMot(Plateau p, MEE s, int[] nbPointsJet){
        /** pré-requis : les éléments de s sont inférieurs à 26
        * et nbPointsJet.length >= 26
        * action : simule le placement d’un mot de this :
        * a) le mot, sa position sur le plateau et sa direction, sont saisis
        * au clavier
        * b) vérifie si le mot est valide
        * c) si le coup est valide, le mot est placé sur le plateau
        * résultat : vrai ssi ce coup est valide, c’est-à-dire accepté par
        * CapeloDico et satisfaisant les règles détaillées plus haut
        * stratégie : utilise la méthode joueMotAux
        */

        String mot="";
        int ligne=15, colonne=15;
        char sens=' ', a= ' ';
        boolean booleen = false;


        Ut.afficher("Saisissez le mot que vous souhaitez jouer : ");
        mot = Ut.saisirChaine();


        //entrer une colonne valide dans le plateau
        while(colonne<0 || colonne>14){
            Ut.afficherSL("Saisir la colonne (entre 0 et 14) : ");
            colonne = Ut.saisirEntier();
        }
        //entrer une ligne valide dans le plateau

            Ut.afficherSL("Saisir la ligne (entre A et 0) : ");
            a = Ut.saisirCaractere();
            ligne=Ut.majToIndex(a);


        //entrer un sens valide dans le plateau
        while(sens != 'v' && sens != 'h'){
            Ut.afficherSL("Choisissez le sens de saisie ('h' ou 'v') : ");
            sens = Ut.saisirCaractere();
        }

        //Vérifie que le placement est possible
        Ut.afficherSL("Placement VALIDE ?" + (p.placementValide(mot, ligne, colonne, sens, this.chevalet)));
        //place le mot et vérifie si le joueur a fait Scrabble, si c'est le cas, il gagne 50 points supplémentaires
        if ((p.placementValide(mot, ligne, colonne, sens, this.chevalet)==true)) {
            this.score += p.nbPointsPlacement(mot, ligne, colonne, sens, nbPointsJet);
            booleen = true;
            if(p.place(mot, ligne, colonne, sens, this.chevalet)==7){
                Ut.afficherSL("SCRABBLE, vous gagnez 50 points supplementaires !");
                this.score+=50;
            }
            s.transfereAleat(this.chevalet, 7-(this.chevalet.getNbtotEx()));
        }
        //
        if (booleen==true){
            Ut.afficherSL("Le score du joueur " + this.nom + " devient de " + this.score + "!!!");
        }

        else if(booleen==false){
            Ut.afficherSL("Placement Non Valide, le score du joueur " + this.nom + " reste de " + this.score + ".");
            Ut.afficherSL("Veuillez reessayer !");
        }
        
        return booleen;
    }

    public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot, int numLig, int numCol, char sens){
        /** pré-requis : cf. joueMot et le placement de mot à partir de la case
        * (numLig, numCol) dans le sens donné par sens est valide
        * action : simule le placement d’un mot de this
        */

        //si le placement est valide, on place et on incrémente le score
        if ((p.placementValide(mot, numLig, numCol, sens, this.chevalet)==true)) {
            p.place(mot, numLig, numCol, sens, this.chevalet);
            this.score+=p.nbPointsPlacement(mot, numLig, numCol, sens, nbPointsJet);
            //si le nombre de lettres placées est de 7, on ajoute 50 points supplémentaires
            if(p.place(mot, numLig, numCol, sens, this.chevalet)==7){
                Ut.afficherSL("SCRABBLE, vous gagnez 50 points supplémentaires !");
                this.score+=50;
            }
        }

            if(s.estVide()==false){
                s.transfereAleat(this.chevalet, 7-(this.chevalet.getNbtotEx()));
            }



    }

    
    public void echangeJetons(MEE sac){
        /**
        * pré-requis : sac peut contenir des entiers de 0 à 25
        * action : simule l’échange de jetons de ce joueur :
        * - saisie de la suite de lettres du chevalet à échanger
        * en vérifiant que la suite soit correcte
        * - échange de jetons entre le chevalet du joueur et le sac
        * stratégie : appelle les méthodes estCorrectPourEchange et echangeJetonsAux
        */

        String sdl;
        int cpt=0;

        Ut.afficherSL("Saisissez à la chaine, les lettres que vous souhaitez echanger : ");
            sdl = Ut.saisirChaine();
        while(estCorrectPourEchange(sdl)==false){
            Ut.afficherSL("Saisissez à la chaine, les lettres que vous souhaitez echanger : ");
            sdl = Ut.saisirChaine();
        }
        for (int i = 0; i<sdl.length(); i++) {
            this.chevalet.transfere(sac, Ut.majToIndex(sdl.charAt(i)));
        }
        sac.transfereAleat(this.chevalet, sdl.length());

    }
    
    public boolean estCorrectPourEchange (String mot) {
        /** résultat : vrai ssi les caractères de mot correspondent tous à des
        * lettres majuscules et l’ensemble de ces caractères est un
        * sous-ensemble des jetons du chevalet de this
        */
        char a;
        boolean check=true;
        int cpt=0;
        int [] tab=this.chevalet.getTabFreq();
        for(int z=0; z<mot.length(); z++){
            a=mot.charAt(z);
                if(this.chevalet.getTabFreq()[Ut.majToIndex(a)]>0){
                                    cpt++;
                }
        
                                        
            else if(Ut.estUneMajuscule(a)==false){
                check=false;
            }
            check=(cpt==mot.length());
        }
        return check;
    }

    public void echangeJetonsAux(MEE sac, String ensJetons){
        /** pré-requis : sac peut contenir des entiers de 0 à 25 et ensJetons
        * est un ensemble de jetons correct pour l’échange
        * action : simule l’échange de jetons de ensJetons avec des
        * jetons du sac tirés aléatoirement.*/
        

        for (int i = 0; i<ensJetons.length(); i++) {
            this.chevalet.transfere(sac, Ut.majToIndex(ensJetons.charAt(i)));
        }
        sac.transfereAleat(this.chevalet, ensJetons.length());

    }




    }