package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ArrayList;
//import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
//import java.util.concurrent.ThreadLocalRandom;
//import static.Ge

public class Grafo{

	private MatrizSimetrica matAdy;
	protected int cantNodos;
	ArrayList<Vertice> vertices;
	
	public Grafo(int cantNodos) throws NodosException{
		if(cantNodos<1)
			throw new NodosException("La cantidad de nodos debe ser superior a 0.");
		this.cantNodos = cantNodos;
		this.matAdy = new MatrizSimetrica(this.cantNodos);
		this.vertices = new ArrayList<Vertice>();
		//vertices = new int[this.cantNodos];
		for(int i=0; i<this.cantNodos; i++)
			vertices.add(new Vertice(i,0));
	}
	/**
	 * Genera un nodo a partir de un archivo, donde el primer nodo es 1.
	 * @param path: ruta del archivo.
	 * @throws FileNotFoundException
	 * @throws NodosException 
	 */
	public Grafo(String path) throws FileNotFoundException, NodosException{
		Scanner archie = new Scanner(new File(path));
		archie.useLocale(Locale.ENGLISH);
		
		this.cantNodos = archie.nextInt();
		if(cantNodos<1) {
			archie.close();
			throw new NodosException("La cantidad de nodos debe ser superior a 0.");
		}
		this.matAdy = new MatrizSimetrica(this.cantNodos);
		int cantAristas = archie.nextInt();
		archie.nextDouble(); //Porcentaje Ady
		archie.nextInt(); //Grado max
		archie.nextInt(); //Grado min
		
		for(int i=0; i<cantAristas; i++)
			setArista(archie.nextInt()-1, archie.nextInt()-1);
		
		int [] grados = calcularGrados();
		this.vertices = new ArrayList<Vertice>();
		//vertices = new int[this.cantNodos];
		for(int i=0; i<this.cantNodos; i++)
			vertices.add(new Vertice(i, grados[i]));
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
		for(int i=0; i<this.cantNodos-1; i++) {
			for(int j=i+1; j<this.cantNodos; j++)
				if(this.matAdy.getAdyacencia(i, j))
					arch.println(i+1 + " " + (j+1));}

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
	
	public boolean getAdyacencia(int fila, int columna){
		return this.matAdy.getAdyacencia(fila,columna);
	}
	/*
	public double randomCalculado(double d){
		return matAdy.randomCalculado(d);
	}
	*/
	public int coloreoWelshPowell(String path) throws IOException{
		//ordena el vector de vertices por grado de adyacencia de mayor a menor.
		Collections.shuffle(vertices);
		vertices.sort(null);
		//Collections.reverse(vertices);
		//Arrays.sort(vertices);
		//coloreo
		int[] colores= new int[this.cantNodos];//0 significa sin color
		int cantColores=colorear(vertices,colores);
		if(path!=null)
			imprimir(colores,path,cantColores);
		return cantColores;
	}
	
	public int coloreoMatula(String path) throws IOException{
		//ordena el vector de vertices por grado de adyacencia de menor a mayor.
		Collections.shuffle(vertices);
		vertices.sort(null);
		Collections.reverse(vertices);
		//coloreo
		int[] colores= new int[this.cantNodos];//0 significa sin color
		int cantColores=colorear(vertices,colores);
		if(path!=null)
			imprimir(colores,path,cantColores);
		return cantColores;
	}
	
	public int coloreoSecuencialAleatorio(String path) throws IOException{
		//Mescla el array de vertices aleatoriamente
		Collections.shuffle(vertices);
		//coloreo
		int[] colores= new int[this.cantNodos];//0 significa sin color
		int cantColores=colorear(vertices,colores);
		if(path!=null)
			imprimir(colores,path,cantColores);
		return cantColores;
	}

	private int colorear(ArrayList<Vertice> orden, int[] colores){
		int coloreados=0, color = 1, ind, j;
		boolean ban=true;
		//mientras haya nodos sin color sigue coloreando.
		while(coloreados < this.cantNodos){
			//tomo el menor color y coloreo todos los nodos que pueda con �l.
			for(int i=0; i<this.cantNodos; i++){
				ind = orden.get(i).getNroNodo();
				//si no esta coloreado, me fijo si lo puedo colorear con el color actual.
				if(colores[ind] == 0){
					j=0;
					ban = true;
					while(j<this.cantNodos && ban){
						if(getAdyacencia(ind, j) && 
								color == colores[j])
							ban=false;
						j++;
					}
					if(j == this.cantNodos && ban){
						colores[ind]=color;
						coloreados++;
					}
				}
			}
			color++;
		}
		return color-1;
	}
	
	private void imprimir(int[] colores, String path, int cantColores) throws IOException{
		PrintWriter pw = new PrintWriter(new FileWriter(path));
		pw.println(this.cantNodos + " " + cantColores + " " + this.getCantAristas() + " " + this.getPorcentajeAdyReal() + " " + this.calcularGradoMax() + " " + this.calcularGradoMin());
		for(int i=0; i<this.cantNodos; i++){
			pw.println((i+1) + " " + colores[i]);
		}
		pw.close();
	}

}
