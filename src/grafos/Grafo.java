package grafos;

import java.util.Random;

public class Grafo {

	private MatrizSimetrica mat_ady;
	private static final int CANTNODOS=5;
	private int cant_nodos;
	
	public Grafo(){
		cant_nodos=CANTNODOS;
		mat_ady=new MatrizSimetrica(CANTNODOS);
	}
	
	public Grafo(int cant_nodos){
		this.cant_nodos=cant_nodos;
		mat_ady=new MatrizSimetrica(this.cant_nodos);
	}
	
	public static void main(String args[]){
		Grafo grafito=new Grafo();
		
	}
	
	public void randomRandom(float pAdyacencia){
		double value;
		for(int i=0;i<this.cant_nodos-1;i++){
			for(int j=i+1;j<this.cant_nodos;j++){
				value=Math.random();
				if(value<=pAdyacencia)
					mat_ady.setAdyacencia(i, j);
			}
		}
	}
	
	public void randomCalculado(float pAdyacencia){
		
	}
}
