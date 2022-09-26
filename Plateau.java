import java.util.Scanner; 
import java.lang.*;


public class Plateau{
	
	private Case [][] g = new Case[15][15];

	public Plateau(){
		int[][] plateau = {{5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5},
						   {1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1},
						   {1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1},
						   {2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2},
						   {1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1},
						   {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
						   {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
						   {5, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 5},
						   {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
						   {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
						   {1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1},
						   {2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2},
						   {1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1},
						   {1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1},
						   {5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5}};

		for(int i=0;i<15;i++){
			for (int j=0;j<15;j++){
				this.g[i][j]= new Case(plateau[i][j]);

			}
		}
	}




	/*public Plateau (Case[][] plateau) {
		this.g = plateau;
	}*/

	public String toString(){
		String affichage;
		// TESTS :
		//this.g[3][5].setLettre('A');
		//this.g[3][4].setLettre('A');

		int a=0;
		affichage="\n";
		for (int i = 0; i <8; i++){
        	affichage += " ";
        }
		for (int c = 0; c <= 14; c++) {
        	if (c < 10) {
            	affichage = affichage + c + "     ";
        	}
        	else {    
        		affichage = affichage + c +"    ";     
        	}
		}
		affichage+="\n      _________________________________________________________________________________________  \n";

		for (int i=0; i<15; i++) {
			if(a<15){
				affichage+=(Ut.indexToMaj(a)+"    |  ");}
			a++;
			

			
			for (int j=0; j<15; j++) {
				if(this.g[i][j].estRecouverte()==true){
					affichage+=(this.g[i][j].getLettre() + "  |  ");
				}

				else if (this.g[i][j].getCouleur()==1){
					affichage+=(" "+ "  |  ");
				}
				else{
					affichage+=(this.g[i][j].getCouleur() + "  |  ");
				}


				if (j==14){
					affichage+=("\n     |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|\n");
				}

			}
		}

		return affichage;
	}

	public boolean placementValide(String mot, int numLig, int numCol, char sens, MEE e) {
			/*
			* pré-requis : mot est un mot accepté par CapeloDico,
			0 <= numLig <= 14, 0 <= numCol <= 14, sens est un élément
			de {’h’,’v’} et l’entier maximum prévu pour e est au moins 25
			* résultat : retourne vrai ssi le placement de mot sur this à partir
			de la case (numLig, numCol) dans le sens donné par sens à l’aide
			des jetons de e est valide.
			*/
			boolean check=false;
			boolean cptt=false, cptf=false;
			int cptplat=0, cpt=0;
			char a;

			/*** SI LE PLATEAU EST VIDE VERFIEZ LES CONDITIONS : ***/

			for(int i=0;i<15;i++){
				for (int j=0;j<15;j++){
					if((this.g[i][j].estRecouverte()==true) || (mot.length()<=2)){
						cptplat++;
					}
				}
			}
			if(cptplat==0){
				for(int x=0; x<(mot.length()); x++){
					if(sens=='h'){				
						if(numCol==7 && numLig==7){
							check=true;
						}
					numCol++;
					}
					if(sens=='v'){
						if(numCol==7 && numLig==7){
							check=true;
						}
					numLig++;
					}	
				}
				if(check==true){	
					
					for(int z=0; z<mot.length(); z++){
						a=mot.charAt(z);
						if(e.getTabFreq()[Ut.majToIndex(a)]>0){
									cpt++;
							}
						}
										
					check=(cpt==mot.length());
				}
			}



			/*** SI LE PLATEAU N'EST PAS VIDE VERFIEZ LES CONDITIONS : ***/

			if(cptplat>0){
				// TEST : Ut.afficher(cptplat + " " + numLig + " " + numCol);
						if(sens=='h'){				
							if((this.g[numLig][numCol].estRecouverte()==false) && (this.g[numLig][numCol+mot.length()].estRecouverte()==false)){							
								for(int l=0; (l<mot.length()); l++){
									if(this.g[numLig][numCol].estRecouverte()==true){
										for (int lettre=0;lettre<mot.length();lettre++){
											if(this.g[numLig][numCol].getLettre()==mot.charAt(lettre)){
												cptt=true;
											}
										}
									}
									if(this.g[numLig][numCol].estRecouverte()==false){
										cptf=true;
									}
									numCol++;
								}
								check=(cptf==cptt);
							}
							else{
								check=false;
							}
						}
						if(sens=='v'){
							if((this.g[numLig][numCol].estRecouverte()==false) && (this.g[numLig+mot.length()][numCol].estRecouverte()==false)){
								for(int l=0; l<(mot.length()); l++){
									if(this.g[numLig][numCol].estRecouverte()==true){
										for (int lettre=0;lettre<mot.length();lettre++){
											if(this.g[numLig][numCol].getLettre()==mot.charAt(lettre)){
												cptt=true;
											}
										}
									}
									if(this.g[numLig][numCol].estRecouverte()==false){
										cptf=true;
									}
									numLig++;
								}
								check=(cptf==cptt);
							}
							else{
								check=false;
							}
						}
						if(check==true){	
							for(int z=0; z<mot.length(); z++){
								a=mot.charAt(z);
								if(e.getTabFreq()[Ut.majToIndex(a)]>0){
											cpt++;
									}
								}
													
							check=(cpt==(mot.length()));
						
					}
				
		}
		return check;
	}

	public int nbPointsPlacement(String mot, int numLig, int numCol, char sens, int[] nbPointsJet) {
		/**
		* pré-requis : le placement de mot sur this à partir de la case
		* (numLig, numCol) dans le sens donné par sens est valide
		* résultat : retourne le nombre de points rapportés par ce placement, le
		* nombre de points de chaque jeton étant donné par le tableau nbPointsJet.
		*/
		//POUR TEST : int [] tab = {0,0,0,3,4,5,6};

		int cptPoints=0, x=0, p=0;

		for(int i=0;i<mot.length();i++){
			if(sens=='v'){
				numLig++;
					if((this.g[numLig][numCol].getCouleur())==5 || (this.g[numLig][numCol].getCouleur())==4){
						x=(this.g[numLig][numCol].getCouleur());
					}
					
				}
			else if(sens=='h'){
				numCol++;
					if((this.g[numLig][numCol].getCouleur())==5 || (this.g[numLig][numCol].getCouleur())==4){
						x=(this.g[numLig][numCol].getCouleur());
					}
					
				}
		}
		if(x==0){ // SI LA COULEUR EST 1,2,3 -> POINT_LETTRE*COULEUR
			for(p=0; p<mot.length(); p++){
				if(sens=='v'){
					numLig++;
					cptPoints+=(this.g[numLig][numCol].getCouleur())*(nbPointsJet[Ut.majToIndex(mot.charAt(p))]);
					
				}
				else if(sens=='h'){
					numCol++;
					cptPoints+=(this.g[numLig][numCol].getCouleur())*(nbPointsJet[Ut.majToIndex(mot.charAt(p))]);
					
				}

			}
		}
			else if(x==4){ // SI LA COULEUR EST 4 -> MOT COMPTE DOUBLE
				for( p=0; p<mot.length(); p++){
					if(sens=='v'){
						numLig++;
						cptPoints+=(2*(nbPointsJet[Ut.majToIndex(mot.charAt(p))]));

					}
					else if(sens=='h'){
						numCol++;
						cptPoints+=(2*(nbPointsJet[Ut.majToIndex(mot.charAt(p))]));

					}
				}
			}
			else if(x==5){ // SI LA COULEUR EST 5 -> MOT COMPTE TRIPLE
				for( p=0; p<mot.length(); p++){
					if(sens=='v'){
						numLig++;
						cptPoints+=(3*(nbPointsJet[Ut.majToIndex(mot.charAt(p))]));

					}
					else if(sens=='h'){
						numCol++;
						cptPoints+=(3*(nbPointsJet[Ut.majToIndex(mot.charAt(p))]));
					}
				}
			}
		
		return cptPoints;
		
	}
	public int place(String mot, int numLig, int numCol, char sens, MEE e){
		/**
		* pré-requis : le placement de mot sur this à partir de la case
		* (numLig, numCol) dans le sens donné par sens à l’aide des
		* jetons de e est valide.
		* action/résultat : effectue ce placement et retourne le
		* nombre de jetons retirés de e.
		*/
		int cpt=0, i=0;
		char a;
		if ((this.placementValide( mot, numLig, numCol, sens, e)==true)){
			for(int p=0; p<mot.length(); p++){
				a = mot.charAt(p);
				i = Ut.majToIndex(a);
				if(sens=='v'){
					this.g[numLig][numCol].setLettre(a);
					e.retire(i);
					cpt++;
					numLig++;
				}
				else if(sens=='h'){
					this.g[numLig][numCol].setLettre(a);
					e.retire(i);
					cpt++;
					numCol++;
				}
			}
		}

		return cpt; // RETOURNE LE NOMBRE DE JETONS RETIRES DU CHEVALET

	}




		
					



}
