public class Main //D�claration de la classe Main
{
	public static void main (String[] args) //D�claration du main
	{
		int i = 4; //Choix du niveau
		
		/*
		 * Infos niveaux : 
		 * 
		 * Niveau 1 : Le dragon est affam� et ne passe pas par 4 chemins pour manger la pomme. Il y va directement.
		 * 
		 * Niveau 2 : Le dragon est tr�s tr�s affam� il se d�place en diagonale pour manger la pomme !
		 * 
		 * Niveau 3 : Le printemps est la. C'est la saison des pommes. Le dragon n'a pas tr�s faim et laisse certaines pommes de c�t�
		 * 
		 * Niveau 4 : La sorci�re de Blanche Neige est pass�e par l� ! Si tu mange trop de pommes empoisonn�es, tu peux mourir !
		 */
		
		if (i == 1){//Ici, on lance le niveau 1
		Fenetre f = new Fenetre(); //Variable qui instance f de la Classe Fenetre    
		}
		
		if (i == 2){//Ici, on lance le niveau 2
			Fenetre2 f = new Fenetre2();
		}
		
		if (i == 3){//Niveau 3
			Fenetre3 f = new Fenetre3();
		}
		
		if (i == 4){//Niveau 4
			Fenetre4 f = new Fenetre4();
		}
	}
}