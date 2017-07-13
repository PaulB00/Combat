public class Main //Déclaration de la classe Main
{
	public static void main (String[] args) //Déclaration du main
	{
		int i = 4; //Choix du niveau
		
		/*
		 * Infos niveaux : 
		 * 
		 * Niveau 1 : Le dragon est affamé et ne passe pas par 4 chemins pour manger la pomme. Il y va directement.
		 * 
		 * Niveau 2 : Le dragon est très très affamé il se déplace en diagonale pour manger la pomme !
		 * 
		 * Niveau 3 : Le printemps est la. C'est la saison des pommes. Le dragon n'a pas très faim et laisse certaines pommes de côté
		 * 
		 * Niveau 4 : La sorcière de Blanche Neige est passée par là ! Si tu mange trop de pommes empoisonnées, tu peux mourir !
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