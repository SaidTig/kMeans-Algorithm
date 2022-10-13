package algorithms;

import java.awt.Point;

import java.util.ArrayList;
import java.util.Random;

public class DefaultTeam {

	 public ArrayList<ArrayList<Point>> calculKMeans(ArrayList<Point> points) {
		  //Les partitions
		    ArrayList<Point> part1 = new ArrayList<Point>();
		    ArrayList<Point> part2 = new ArrayList<Point>();
		    ArrayList<Point> part3 = new ArrayList<Point>();
		    ArrayList<Point> part4 = new ArrayList<Point>();
		    ArrayList<Point> part5 = new ArrayList<Point>();
		    
		    System.out.println("Nombre de points ---> " + points.size());
		    
		    //initialisation des representants
		    ArrayList<Point> list_rep = new ArrayList<Point>();  //liste des representants
		    ArrayList<Point> pot_init = new ArrayList<Point>();  //liste des representants initiaux potentiels
		    for(int i=0; i<points.size(); i++) pot_init.add(points.get(i));
		    Random ran = new Random(); //tirer un indice aleatoire dans le range de la liste des points
		    int r1 = ran.nextInt(1000);
		    part1.add(points.get(r1)); //ajouter le point a la premiere partition
		    list_rep.add(points.get(r1)); //ajouter le point a la liste des representants
		    
		    //----------initialisation avec les points le plus proches du premier point aleatoire----
		    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		    
		    pot_init.remove(points.get(r1)); //eliminer le point des representants initiaux potentiels
		    int r = 0;
		    //trouver les 4 points les plus proches du premier point
		    for(int i = 0; i<4; i++) {
		    	double di = 99999; //distance infinie comme reference
		    	for(int j = 0; j<pot_init.size(); j++) {
		    		if(Point.distance(points.get(r1).getX(), points.get(r1).getY(), pot_init.get(j).getX(), pot_init.get(j).getY()) < di) {
		    			r = j; //indice du point le plus proche au premier point
		    			di = Point.distance(points.get(r1).getX(), points.get(r1).getY(), pot_init.get(j).getX(), pot_init.get(j).getY());
		    		}
		    	}
		    	list_rep.add(pot_init.get(r)); //ajouter le point a la liste des representants
		    	pot_init.remove(list_rep.get(i+1)); //eliminer le point de la liste des representants potentiels
		    }
		    part2.add(list_rep.get(1));
		    part3.add(list_rep.get(2));
		    part4.add(list_rep.get(3));
		    part5.add(list_rep.get(4));
		    
		    //----------initialisation avec 5 aleatoires----
		    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		    /*
		    int r2 = ran.nextInt(1000);
		    list_rep.add(points.get(r2));
		    int r3 = ran.nextInt(1000);
		    list_rep.add(points.get(r3));
		    int r4 = ran.nextInt(1000);
		    list_rep.add(points.get(r4));
		    int r5 = ran.nextInt(1000);
		    list_rep.add(points.get(r5));
		    part2.add(list_rep.get(1));
		    part3.add(list_rep.get(2));
		    part4.add(list_rep.get(3));
		    part5.add(list_rep.get(4));
		    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		    */
		    for(int i=0; i< 5;i++) {
		    	System.out.println("Le representant de la partition " + (i+1) + " est le point: " + list_rep.get(i));
		    }
		    //creer la liste des partitions (a ce niveau, chaque partitions ne contient que le representant initial)
		    ArrayList<ArrayList<Point>> kmeans = new ArrayList<ArrayList<Point>>();
		    kmeans.add(part1);
		    kmeans.add(part2);
		    kmeans.add(part3);
		    kmeans.add(part4);
		    kmeans.add(part5);
		    
		    //ArrayList<ArrayList<Point>> kmeans2 = new ArrayList<ArrayList<Point>>();
		    
		    for(int k = 0; k<100; k++) { //ou bien en utilisant une boucle while (kmeans different au kmeans precedant)
		    	//sauvegarder la liste des partitions de l'iteration precedante
		    	double d;
		    	//affecter chaque point a la partition avec le representant le plus proche:
		    	for (int u = 0;u < 5;u++) kmeans.get(u).clear(); //reinitialiser les points des partitions
		    	for(int i=0; i<points.size(); i++) {
		    		d = 99999;	//une distance infinie comme reference pour trouver le point le plus proche
		    		int m = 0;
		    		for(int j=0; j<5; j++) {
		    			if(Point.distance(points.get(i).getX(),points.get(i).getY(),list_rep.get(j).getX(),list_rep.get(j).getY()) < d) {
		    				m = j; //affectation a la partitions j
		    				d = Point.distance(points.get(i).getX(),points.get(i).getY(),list_rep.get(j).getX(),list_rep.get(j).getY());
		    				}
		    			}
		    		kmeans.get(m).add(points.get(i)); //ajouter le point a la partition dans la liste des partitions
		    		}
		    
		    	//coordonnees des 5 centroides des partitions
		    	double c1x = 0;
		    	double c1y = 0;
		    	for(int i=0; i<part1.size();i++) {
		    		c1x = c1x + part1.get(i).getX();
		    		c1y = c1y + part1.get(i).getY();
		    		}
		    		//centre de masse:
		    	c1x = c1x / part1.size();
		    	c1y = c1y / part1.size();
		    	Point c1 = new Point();
		    	c1.setLocation(c1x, c1y);
		    	list_rep.set(0,c1);
		    	double c2x = 0;
		    	double c2y = 0;
		    	for(int i=0; i<part2.size();i++) {
		    		c2x = c2x + part2.get(i).getX();
		    		c2y = c2y + part2.get(i).getY();
		    	}
		    	c2x = c2x / part2.size();
		    	c2y = c2y / part2.size();
		    	Point c2 = new Point();
		    	c2.setLocation(c2x, c2y);
		    	list_rep.set(1,c2);
		    	double c3x = 0;
		    	double c3y = 0;
		    	for(int i=0; i<part3.size();i++) {
		    		c3x = c3x + part3.get(i).getX();
		    		c3y = c3y + part3.get(i).getY();
		    	}
		    	c3x = c3x / part3.size();
		    	c3y = c3y / part3.size();
		    	Point c3 = new Point();
		    	c3.setLocation(c3x, c3y);
		    	list_rep.set(2,c3);
		    	double c4x = 0;
		    	double c4y = 0;
		    	for(int i=0; i<part4.size();i++) {
		    		c4x = c4x + part4.get(i).getX();
		    		c4y = c4y + part4.get(i).getY();
		    	}
		    	c4x = c4x / part4.size();
		    	c4y = c4y / part4.size();
		    	Point c4 = new Point();
		    	c4.setLocation(c4x, c4y);
		    	list_rep.set(3,c4);
		    	double c5x = 0;
		    	double c5y = 0;
		    	for(int i=0; i<part5.size();i++) {
		    		c5x = c5x + part5.get(i).getX();
		    		c5y = c5y + part5.get(i).getY();
		    	}
		    	c5x = c5x / part5.size();
		    	c5y = c5y / part5.size();
		    	Point c5 = new Point();
		    	c5.setLocation(c5x, c5y);
		    	list_rep.set(4,c5);
		    	}
		    	
		    return kmeans;
		  	}
  
  public ArrayList<ArrayList<Point>> calculKMeansBudget(ArrayList<Point> points) {
    ArrayList<Point> rouge = new ArrayList<Point>();
    ArrayList<Point> verte = new ArrayList<Point>();

    for (int i=0;i<points.size()/2;i++){
      rouge.add(points.get(i));
      verte.add(points.get(points.size()-i-1));
    }
    if (points.size()%2==1) rouge.add(points.get(points.size()/2));

    ArrayList<ArrayList<Point>> kmeans = new ArrayList<ArrayList<Point>>();
    kmeans.add(rouge);
    kmeans.add(verte);

    /*******************
     * PARTIE A ECRIRE *
     *******************/
    return kmeans;
  }
}