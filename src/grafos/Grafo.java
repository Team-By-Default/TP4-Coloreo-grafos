package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Arrays;
import java.util.Scanner;

public class Grafo{

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
		archie.useLocale(Locale.ENGLISH);
		
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
					arch.println(i+1 + " " + (j+1));

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
	public int getGradoNodo(int nodo){
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
	 * Setea una arista entre dos nodos. El 1� nodo es el nodo 0
	 * @param nodo1: numero de nodo
	 * @param nodo2: numero de nodo
	 */
	public void setArista(int nodo1, int nodo2){
		this.matAdy.setAdyacencia(nodo1, nodo2);
	}
	
	public int getCantNodos(){
		return this.cantNodos;
	}
	
	public double randomCalculado(double d){
		return matAdy.randomCalculado(d);
	}
	*/
	/**
	 * Algoritmo de Coloraci�n de Welsh y Powell 
	 * @throws IOException 
	 */
	public void ColoreoWelshPowell(String path) throws IOException{
		/**
		 * se usa para los algoritmos de coloreo, 
		 * vertice contiene color y vecinos de cada nodo.
		 */
		Vertice[] vertices = new Vertice[this.cantNodos];
		/**
		 * cargo los vecinos para cada vertice
		 */
		for(int i=0; i<this.cantNodos; i++){
			vertices[i] = new Vertice(i+1);
			for(int j=0; j<this.cantNodos; i++){
				if(this.matAdy.getAdyacencia(i, j))
					vertices[i].agregarVecino(j);
			}
		}
		/**
		 * ordena el vector de vertices por grado de adyacencia de mayor a menor.
		 */
		Arrays.sort(vertices);
		/**
		 * coloreo.
		 */
		int cantColores=colorear(vertices);
		imprimir(vertices,path,cantColores);
	}
	
	
	
	public int colorear(Vertice[] vertices){
		int coloreados=0,color=0,j;
		//mientras haya nodos sin color sigue coloreando.
		while(coloreados < this.cantNodos){
			//tomo el menor color y coloreo todos los nodos que pueda con �l.
			for(int i=0; i<this.cantNodos; i++){
				//si no esta coloreado, me fijo si lo puedo colorear con el color actual.
				if(vertices[i].getColor() == null){
					j=0;
					while(j < vertices[i].getCantVecinos() && color!=vertices[vertices[i].getVecino(j)].getColor())
						j++;
					//si ningun vecino tiene este color lo colorea.
					if(j == vertices[i].getCantVecinos()){
						vertices[i].setColor(color);
						coloreados++;
					}
				}
			}
			color++;
		}
		return color+1;
	}
	
	public void imprimir(Vertice[] vertices, String path, int cantColores) throws IOException{
		PrintWriter pw = new PrintWriter(new FileWriter(path));
		pw.println(this.cantNodos + " " + cantColores + " " + this.getCantAristas() + " " + this.getPorcentajeAdyReal() + " " + this.calcularGradoMax() + " " + this.calcularGradoMin());
		for(int i=0; i<this.cantNodos; i++){
			pw.println(vertices[i].getNroNodo() + " " + vertices[i].getColor());
		}
		pw.close();
	}
	
	public static void main(String args[]) throws NodosException, GradoException, PorcentajeException{
		Grafo grafito=new Grafo(10);
		//System.out.println(grafito.randomRandom(0.5));
		//System.out.println(grafito.randomCalculado(0.70));
		grafito.mostrarMatrizAdy();
		System.out.println();
		/*grafito = new GrafoAleatorioPorcentajeAdy(6, 0.7);
		grafito.mostrarMatrizAdy();
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
		*/
	}
	
}
