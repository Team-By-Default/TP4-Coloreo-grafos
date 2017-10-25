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
		Grafo grafito=new Grafo(10);
		//System.out.println(grafito.randomRandom(0.5));
		System.out.println(grafito.randomCalculado(0.70));
		grafito.mostrarMatrizAdy();
	}
	
	public void mostrarMatrizAdy() {
		mat_ady.mostrarMatriz();
	}
	
	public double randomRandom(double d){
		double value, realAd=0;
		for(int i=0;i<this.cant_nodos-1;i++){
			for(int j=i+1;j<this.cant_nodos;j++){
				value=Math.random();
				if(value<=d) {
					mat_ady.setAdyacencia(i, j);
					realAd++;
				}
			}
		}
		return 2*(realAd/(this.cant_nodos*this.cant_nodos-1));
	}
	
	public double randomCalculado(double d){
		return mat_ady.randomCalculado(d);
	}
	
	
	
}
