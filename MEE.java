public class MEE  {

    private int [] tabFreq; // tabFreq[i] est le nombre d’exemplaires
                            // (fréquence) de l’élément i
    private int nbTotEx;    // nombre total d’exemplaires



    public MEE (int max){
        int[] tabFreq = new int [max];
        this.tabFreq = tabFreq;
        this.nbTotEx = 0;
    } 

    public MEE (int[] tab){
    //action : crée un multi-ensemble dont le tableau de fréquences est
    //une copie de tab
        this.tabFreq = tab;
        this.nbTotEx = 0;
        for (int i = 0; i < tabFreq.length; i++)
          {
            this.nbTotEx += tabFreq[i];
          }
    }

    public MEE (MEE e){
    //constructeur par copie
        int i;
        this.tabFreq = new int [e.tabFreq.length];
        for (i=0;i<e.tabFreq.length;i++) {
                this.tabFreq[i] = e.tabFreq[i];
            }
        this.nbTotEx = e.nbTotEx;
    }

  
    public String toString(){
    //méthode d'affichage de MEE

        String ens = "{";

        for (int i = 0; i < this.tabFreq.length; i++)
          {
            ens = ens + this.tabFreq[i] + ", ";
          }
        ens = ens + "}\n";

        return ens;
    }

    
    public boolean estVide (){
    //on vérifie si le nombre d'exemplaires est égal à zéro ou non

        boolean reponse = false;
        if (this.nbTotEx == 0)
          {
            reponse = true;
          }

        return reponse;
    }

    public boolean contient(int i){
        boolean bool = false;

        if(this.tabFreq[i] != 0) {
            bool = true;
        }
        return bool;
    }

    public int getNbtotEx(){

        return this.nbTotEx;
    } 


    public int[] getTabFreq(){

        return this.tabFreq;
    }


    public void ajoute (int i){

        this.tabFreq[i] += 1;
        this.nbTotEx += 1;
    } 

    public boolean retire (int i){

    if (tabFreq[i] > 0){
        this.tabFreq[i] -= 1;
        this.nbTotEx -= 1;
        return true;
    }
    else{
        return false;
      }
    }

    public int retireAleat (){
      int nb;
      boolean a;
      do {
        nb = Ut.randomMinMax(0, this.tabFreq.length -1);
        a = this.retire(nb);
      }
      while (a == false);

        return nb;
    } 

    public boolean transfere (MEE e, int i){

    boolean bool = false;
    if (this.retire(i) == true){
        e.ajoute(i);
        bool = true;
    }

    return bool;
    }

    public void transfereAleat (MEE e, int k){
        int qtt;
        for (int i = 0; i < k && ! this.estVide(); i++){

            qtt = this.retireAleat();
            e.ajoute(qtt);
          }
    } 

    public int sommeValeurs (int[] v){
        int somme = 0;
        //parcours de chaque valeur des deux tableaux
        for (int i = 0; i < this.tabFreq.length; i++){
            somme = somme + (tabFreq[i] * v[i]);
          }
        return somme;
    }
  
}