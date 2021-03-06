// Importation des librairies, classes, etc., n�cessaires au jeu
import java.awt.Color; //Utilisation des couleurs
import java.awt.Graphics; //Composants graphiques (rond, rectangle, etc.)
import java.awt.Point; //Gerer la localisation
import java.util.Random; //Cr�er un nombre random pour placer la pomme
import java.util.Vector; //Permet de stocker des objets dans un tableau dont la taille �volue avec les besoins
import javax.swing.JPanel; //Permet de g�rer les interfaces


	/* 
	 * D�finition Runnable : lorsqu'on invoque la m�thode start(), le thread est pr�t � travailler.
	 * 
	 * Extends : cela concerne une classe
	 * Implements : cela concerne une interface
	 */

    public class JeuxPanel2 extends JPanel implements Runnable  {
    	
        Fenetre2 f; // Variable f permet d'obtenir les informations de la fenetre 
        
        int x,y; //D�claration les coordon�es du guerrier du joueur
        
        int x1,y1;  //D�claration des coordon�es de la pomme

        int x2,y2; //D�claration coordon�es du dragon
        
        int x3,y3; //P2
        
        /* 
         * Dans la classe Graphics, il existe la m�thode "fillRec". Cett m�thode permet de
         * 	d�finir la taille des rectangles. Cette m�thode est utilis�e donc pour la 
         * 	r�alisation du dragon et du guerrier. Mais �galement pour la pomme.
         * 
         * Cette m�thode n'est que provisoire et a pour but d'�tre remplac�e par la suite
         * 	par des images.
         */
        
        int h = 15; // h : hauteur des carr�s qui repr�sentent le guerrier, la pomme et le dragon
        

        int l = 15; // l : largeur des carr�s qui repr�sentent le G, la P et le D
        

        int scoreD ,scoreG; //Les scores du D et du G
         
        Point p , p2 ; //p stocke les coordonn�es du G et p2 celles du D
        
        int key ; //Cet entier contient le code de bouton tap� par le clavier

        Vector guerrier; //le G est en vrai un vecteur. Il contient les coordonn�es de toutes les partie du G
        Vector dragon; //le D est en vrai un vecteur. Il contient les coordonn�es de toutes les parties du D
        
         /* 
          * La Class Thread utilis�e dans le programme a pour but de
          * limiter la vitesse du D et du G utile dans certains cas.
          */
        Thread thread = new Thread(this);
        
         /* 
          * La Class Random est utilis�e pour fournir des valeurs al�atoires
          * pour la position de la pomme
          */
        Random random = new Random();
        
         /* 
          * Par la suite, nous allons d�clar�s le constructeur "JeuxPanel".
          * Celui-ci contient les param�tres de la fen�tre.
          */
           
      public JeuxPanel2(Fenetre2 f ){
               
           this.f = f; //Fenetre2 JeuxPanel2.f (ici la classe fenetre2 et la classe JeuxPanel2.
             
           setVisible(true); //Cela permet de voir les composants que l'on va cr�er

           setBounds(f.x, f.y, f.getW(), f.getH()); //Permet de positionner les composants. La taille du JPanel doit �tre la m�me que la fen�tre. C'est pour sa que l'on utilise f

           guerrier = new Vector(); //Cr�ation d'une instance pour la classe vector pour le G
           
           p = new Point(); //Initialisation de la longueur du G

           dragon = new Vector(); //Cr�ation d'une instance pour la classe vector pour le D
          
             /*
              * Intialisation de Vecteur Serpon2 par 5 point
              * autrement dite la Serpon de l'ennemi est intialise 
              * par la longueur 5
              */
           p2 = new Point(); //Initialisation de la longueur du D

      }
         
      /*
       * Par la suite, nous allons d�finir les couleurs, ainsi que g�rer l'affichage du score
       */
      
       public void paint(Graphics g ){

                g.setColor(Color.yellow); //Couleur du fond du jeu
                g.fillRect(0, 0, f.getW() , f.getH()); //Permet de donner les tailles � la fenetre
                
            /*
             * FillArc : C'est comme fillRect mais pour un rond.
             * FillOval : M�me chose pour un oval.
             */
                g.setColor(Color.cyan); //Couleur tete G
                g.fillArc(x, y, l, h, 360, 360); //Coordonn�es et longeur

            for (int i = guerrier.size() - 2 ; i >= 0 ; i-- ){
            	
            	/*
            	 * Dans cette boucle, on fait la lecture de tous les pts du vecteur G
            	 * et la m�thode "paintPart" qui peint dans les parties du G.
            	 */
               
                 p = (Point)guerrier.elementAt(i); //def de p
                 paintPart(g ,p , Color.cyan); //Attribution de la couleur du corps de G
                
            }
            
                  g.setColor(Color.green); //Couleur tete D
                  g.fillOval(x2, y2, l, h); //Coordonn�es et lg du D

             for (int i = dragon.size() - 2 ; i >= 0 ; i-- ){

             	/*
             	 * Dans cette boucle, on fait la lecture de tous les pts du vecteur G
             	 * et la m�thode "paintPart" qui peint dans les parties du G.
             	 */

                 p2 = (Point)dragon.elementAt(i);  //def de p
                 paintPart(g ,p2 , Color.green ); //Attribution de la couleur du corps de G
                
                  }

            g.setColor(Color.red); //Couleur P
            g.fillOval(x1,y1,l,h); //Coordonn�es et lg de la P
            
            g.setColor(Color.red);//Couleur P2
            g.fillOval(x3, y3, l, h);//Coordonn�es et lg de la P2

          g.drawString("Score Guerrier = " + 
               String.valueOf(scoreG),(int)(f.getW()/2), f.getH() - 50); //Avec cela, on affichele score du G
          g.drawString("Score Dragon = " + 
               String.valueOf(scoreD),(int)(f.getW() * 3/4), f.getH() - 50); //Ici, on affiche le score du D
          
    	  g.drawString("Objectif score 15",(int)(f.getW()/10), f.getH() - 50); //Ici,on affiche l'objectif du score � atteindre.
          
          if (scoreG == 15){ //Cr�er la condition. Si le G atteind 15 points, alors ...
       	   
        	  g.drawString("YOU WIN",(int)(f.getW()/6/2), f.getH()/2 - 50); //... Il sera �crit qu'il a gagn�.
        	  
          }
          
          if (scoreD == 15){ //Cr�er la condition. Si le 15 atteind 2 points, alors ...
          	   
        	  g.drawString("YOU LOSE",(int)(f.getW()/6/2), f.getH()/2 - 50); //... Il sera �crit que le joueur a perdu
        	  
          }
      } 
       
       /*
        * Par la suite, on cr�er la m�thode qui rempli un rectangle d'une couleur
        * (l'utilisation de celle-ci se situe plus haut).
        */

      public void  paintPart(Graphics g, Point point, Color c){
           
           g.setColor(c); //R�cup�re la couleur.
           g.fillRect(point.x, point.y, l, h); //Affiche le rectangle de celle-ci
           
      }
      
      /*
       * La m�thode suivante de l'interface Runnable execute des insctructions en utilisant une
       * boucle while infinie. Elle appelle la m�thode sleep() de l'instance thread.
       * 
       * On fait l'ex�cution et on attend 75 milliseondes et ont refais la m�me chose.
       */

    public void run() { 
                 
              try { //Le bloc try, catch permet de v�rifier les erreurs

          while(true){ //Boucle infinie (true)
              
              /* 
               * La variable key (comme expliqu� au d�but) contient les codes du bouton du
               * clavier. Cela permet d'organiser le mouvement du G
               */
              
              switch(key){
                  case 38 : y-= h; // Bas
                      break;
                  case 40 : y+= h; // Haute
                      break;
                  case 37 : x-= l; // Gauche
                      break;
                  case 39 : x+= l; // Droite
                      break;
                      
              }
              
                /*
                 * Le morceau de code qui suit contr�le le mouvement du D en fonction des 
                 * coordonn�es de la P.
                 */
              
              if  ( x2 > x1) 
                  {
            	  x2-= h;
            	  y2+=l;
            	  x2-= h;
            	  }
              else if (x2< x1) 
                  { 
            	  x2+= h;
            	  }
              else if ( y2 < y1)
                  {
            	  y2+= l;
            	  x2-=l;
            	  y2+=l;
                  }
              else if (y2 > y1)
                  {
            	  y2-= l;
            	  }
              
              /*
               * Si la t�te du G est �gale aux coordonn�es de la P, alors il faut :
               * 1 changer de place la P
               * 2 allonger le G
               * 3 Augmenter le score
               */ 

               if ((x == x1) && (y == y1)){ //Analyse des coordonn�es
            	   
                    x1 = random.nextInt((int)(f.getW() / l)) * l ; //Choix x random pour la P
                    y1 = random.nextInt((int)(f.getH() / l)) * l ; //Choix y random pour la P
                    
                    guerrier.add(0 , p); //Allongement taille du G
                    
                    scoreG+= 1; //Augmente le score
               }
               
               if ((x == x3) && (y == y3)){ //Analyse des coordonn�es
            	   
                   x3 = random.nextInt((int)(f.getW() / l)) * l ; //Choix x random pour la P2
                   y3 = random.nextInt((int)(f.getH() / l)) * l ; //Choix y random pour la P2
                   
                   guerrier.add(0 , p); //Allongement taille du G
                   
                   scoreG+= 1; //Augmente le score
              }

               if ((x2 == x1) && (y2 == y1)){ //Analyse des coordonn�es pour le D
            	   
                    x1 = random.nextInt((int)(f.getW() / l)) * l ; //Choix x random pour la P
                    y1 = random.nextInt((int)(f.getH() / l)) * l ; //Choix y random pour la P
                    
                    dragon.add(0 , p2);  //Allongement taille du D
                    
                    scoreD+= 1;
               }
               
               if ((x2 == x3) && (y2 == y3)){ //Analyse des coordonn�es pour le D
            	   
                   x3 = random.nextInt((int)(f.getW() / l)) * l ; //Choix x random pour la P2
                   y3 = random.nextInt((int)(f.getH() / l)) * l ; //Choix y random pour la P2
                   
                   dragon.add(0 , p2);  //Allongement taille du D
                   
                   scoreD+= 1;
              }

              guerrier.add(new Point(x , y)); //MaJ coordonn�es t�te du G
              guerrier.remove(0); //Retire le pr�c�dent carr� 

              dragon.add(new Point(x2 , y2)); //MaJ coordonn�es t�te du D
              dragon.remove(0); //Retire le pr�c�dent carr�

              thread.sleep(75); //Pause de 75 millisecondes. Explications au dessus.
           
                 // actualisation de les dessins
               repaint(); //Actualisation des dessins/graphiques. 

               if (scoreG == 15){ //Si le Guerrier atteind 10 pts
            	   
            	   wait(); //Le jeu est fini, il se met en pause de mani�re d�finitive. Remplasser pas un moyen plus propre de quiter le jeu
            	   System.exit(0); //Pouvoir pas la suite fermer la fen�tre de mani�re correcte.
            	   
               }
               
               if (scoreD == 15){ //Si le D atteind 10 pts
            	   
            	   wait(); //voir description ligne 256
            	   System.exit(0); //voir description ligne 257
            	   
               }
         
          }
       } 
             
             catch (InterruptedException ex) {

           }
     }

     public void start(){ //Ici, on d�marre l'ex�cution
         
         thread.start();
         
    }
 
}