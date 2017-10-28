package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Grafo {

	private MatrizSimetrica matAdy;
	protected int cantNodos;
	
	public Grafo(int cantNodos) throws NodosException{
		if(cantNodos<1)
			throw new NodosException("La cantidad de nodos debe ser superior a 0.");
		this.cantNodos = cantNodos;
		this.matAdy = new MatrizSimetrica(this.cantNodos);
	}
	/**
	 * Genera un nodo a partir de un archivo, donde el primer nodo es 1.
	 * @param path: ruta del archivo.
	 * @throws FileNotFoundException
	 */
	public Grafo(String path) throws FileNotFoundException{
		Scanner archie = new Scanner(new File(path));
		
		this.cantNodos = archie.nextInt();
		this.matAdy = new MatrizSimetrica(this.cantNodos);
		int cantAristas = archie.nextInt();
		archie.nextDouble(); //Porcentaje Ady
		archie.nextInt(); //Grado max
		archie.nextInt(); //Grado min
		
		for(int i=0; i<cantAristas; i++)
			setArista(archie.nextInt()-1, archie.nextInt()-1);
		
		archie.close();
	}
	
	/**
	 * Graba el grafo en un archivo con el formato que reconoce el
	 * constructor de Grafo a partir de un archivo.
	 * @param path: ruta del archivo
	 * @throws IOException
	 */
	public void grabarArchivo(String path) throws IOException{
		PrintWriter arch = new PrintWriter(new FileWriter(path));
		
		arch.println(this.cantNodos + " " + getCantAristas() + " " + getPorcentajeAdyReal()
			+ " " + calcularGradoMax() + " " + calcularGradoMin());
		
		for(int i=0; i<this.cantNodos-1; i++)
			for(int j=i+1; j<this.cantNodos; j++)
				if(this.matAdy.getAdyacencia(i, j))
					arch.println(i+1 + " " + j+1);

		arch.close();
	}
	
	public void mostrarMatrizAdy() {
		matAdy.mostrarMatriz();
	}
	
	/**
	 * Devuelve el porcentaje de adyacencia actual
	 * @return porcentaje de adyacencia
	 */
	public double getPorcentajeAdyReal() {
		return (double)this.matAdy.getCantAdyacencias()/(this.cantNodos*(this.cantNodos-1)/2);
	}
	
	/**
	 * Devuelve la cantidad de aristas que salen o entran a cierto nodo
	 * @param nodo: numero de nodo
	 * @return cantidad de aristas o grado
	 */
	private int getGradoNodo(int nodo){
		int cont=0;
		for(int i=0; i<this.cantNodos; i++){
			if(this.matAdy.getAdyacencia(nodo, i))
				cont++;
		}
		return cont;
	}
	
	/**
	 * Calcula el grado o cantidad de aristas de cada nodo
	 * @return vector de grados
	 */
	private int[] calcularGrados(){
		int[] grados = new int[cantNodos];
		for(int i=0; i<cantNodos; i++)
			grados[i] = getGradoNodo(i);
		return grados;
	}
	
	/**
	 * Devuelve el grado del nodo con menos aristas
	 * @return grado minimo
	 */
	private int calcularGradoMin(){
		int[] grados = calcularGrados();
		
		int min = grados[0];
		for(int i=1; i<cantNodos; i++){
			if(min > grados[i])
				min=grados[i];
		}
		return min;
		
	}
	
	/**
	 * Devuelve el grado del nodo con mas aristas
	 * @return grado maximo
	 */
	private int calcularGradoMax(){
		int[] grados = calcularGrados();
		
		int max = grados[0];
		for(int i=1; i<cantNodos; i++){
			if(max < grados[i])
				max=grados[i];
		}
		return max;
		
	}
	
	/**
	 * Devuelve la cantidad de aristas
	 * @return cantidad
	 */
	private int getCantAristas(){
		int[] grados = calcularGrados();
		int cant = 0;
		for(int i=0; i<cantNodos; i++)
			cant += grados[i];
		return cant/2;
	}
	
	/**
	 * Setea una arista entre dos nodos. El 1° nodo es el nodo 0
	 * @param nodo1: numero de nodo
	 * @param nodo2: numero de nodo
	 */
	public void setArista(int nodo1, int nodo2){
		this.matAdy.setAdyacencia(nodo1, nodo2);
	}
	
	public int getCantNodos(){
		return this.cantNodos;
	}
	
	public static void main(String args[]) throws NodosException, GradoException, PorcentajeException{
		Grafo grafito=new Grafo(10);
		//System.out.println(grafito.randomRandom(0.5));
		//System.out.println(grafito.randomCalculado(0.70));
		grafito.mostrarMatrizAdy();
		System.out.println();
		
		GeneradorGrafos.aleatorioPorAdy(6, 0.7, "./aleatorioPorAdy.in");
		grafito = new Grafo("./aleatorioPorAdy.in");
		grafito.getCantNodos();
		grafito.getPorcentajeAdyReal();
		System.out.println();
		
		grafito = new GrafoAleatorioProb(6, 0.7);
		grafito.mostrarMatrizAdy();
		System.out.println();
		grafito = new GrafoRegularGrado(5, 2);
		grafito.mostrarMatrizAdy();
		System.out.println(grafito.getPorcentajeAdyReal());
		grafito = new GrafoRegularAdy(5, 0.5);
		grafito.mostrarMatrizAdy();
		System.out.println(grafito.getPorcentajeAdyReal());
		
	}
	
}
