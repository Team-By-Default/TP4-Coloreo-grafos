package grafos;

import java.util.Random;

public class Grafo {

	protected MatrizSimetrica matAdy;
	private static final int CANTNODOS=5;
	protected int cantNodos;
	
	public Grafo(){
		this.cantNodos = CANTNODOS;
		this.matAdy = new MatrizSimetrica(CANTNODOS + 1);
	}
	
	public Grafo(int cant_nodos){
		this.cantNodos = cant_nodos;
		this.matAdy = new MatrizSimetrica(this.cantNodos + 1);
	}
	
	public void mostrarMatrizAdy() {
		matAdy.mostrarMatriz();
	}
	
	/* Grafo aleatorio por probabilidad se genera con GrafoAleatorioProb
	public double randomRandom(double d){
		double value, realAd=0;
		for(int i=0;i<this.cantNodos-1;i++){
			for(int j=i+1;j<this.cantNodos;j++){
				value=Math.random();
				if(value<=d) {
					matAdy.setAdyacencia(i, j);
					realAd++;
				}
			}
		}
		return 2*(realAd/(this.cantNodos*this.cantNodos-1));
	}
	
	public double randomCalculado(double d){
		return matAdy.randomCalculado(d);
	}
	*/
	
	public static void main(String args[]){
		Grafo grafito=new Grafo(10);
		//System.out.println(grafito.randomRandom(0.5));
		//System.out.println(grafito.randomCalculado(0.70));
		grafito.mostrarMatrizAdy();
		System.out.println();
		grafito = new GrafoAleatorioPorcentajeAdy(6, 0.7);
		grafito.mostrarMatrizAdy();
		System.out.println();
		grafito = new GrafoAleatorioProb(6, 0.7);
		grafito.mostrarMatrizAdy();
	}
	
}
