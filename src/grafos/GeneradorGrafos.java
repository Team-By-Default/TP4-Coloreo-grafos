package grafos;

import java.util.Arrays;
import java.util.Random;

public class GeneradorGrafos {
	
	/**
	 * Generador de grafos aleatorios a partir de una
	 * cantidad de nodos y un porcentaje de adyacencia.
	 * Guarda el grafo en un archivo.
	 * La cantidad de aristas es determinista, se respeta
	 * el porcentaje de adyacencia, como mucho se redondea
	 * para tener una cantidad de aristas entera.
	 * @param cantNodos: cantidad de nodos
	 * @param porcentaje: porcentaje de adyacencia
	 * @param path: ruta del archivo
	 * @throws PorcentajeException 
	 * @throws NodosException 
	 */
	public static void aleatorioPorAdy(int cantNodos, double porcentaje, String path) throws PorcentajeException, NodosException{
		Grafo grafo = new Grafo(cantNodos);
		
		if(porcentaje < 0 || porcentaje > 1 )
			throw new PorcentajeException("Valor porcentual incorrecto");
		
		Random rnd = new Random();
		PosicionConRandom [] posis = new PosicionConRandom[ grafo.cantNodos*(grafo.cantNodos-1)/2 ];
		int k = 0;
		for(int i = 0; i < grafo.cantNodos-1; i++){
			for(int j = i+1; j < grafo.cantNodos; j++){
				posis[k] = new PosicionConRandom(i,j,rnd.nextDouble());
				k++;
			}
		}
		
		Arrays.sort(posis);
		
		final int cantAristas = (int) (k * porcentaje);
		for(int i=0; i < cantAristas; i++) 
			grafo.setArista( posis[i].getF(), posis[i].getC());
		
		grafo.grabarArchivo(path);
	}
	
	/**
	 *  Generador de grafos aleatorios a partir de una
	 * cantidad de nodos y una probabilidad de adyacencia.
	 * Guarda el grafo en un archivo.
	 * La cantidad de aristas es probabilística, no se
	 * asegura que se cumpla el porcentaje de adyacencia.
	 * @param cantNodos: cantidad de nodos
	 * @param probabilidad: probabilidad de adyacencia
	 * @param path: ruta del archivo
	 * @throws NodosException
	 * @throws PorcentajeException
	 */
	public static void aleatorioPorProb(int cantNodos, double probabilidad, String path) throws NodosException, PorcentajeException {
		Grafo grafo = new Grafo(cantNodos);
		
		if(probabilidad < 0 || probabilidad > 1 )
			throw new PorcentajeException("Valor porcentual incorrecto");
		
		
		Random rnd = new Random();
		for(int i = 0; i < grafo.cantNodos-1; i++){
			for(int j = i+1; j < grafo.cantNodos; j++){
				if(rnd.nextDouble() <= probabilidad)
					grafo.setArista(i, j);
			}
		}
		
		grafo.grabarArchivo(path);
	}
	
	/**
	 *  Generador de grafos regulares a partir de una
	 * cantidad de nodos y un porcentaje de adyacencia.
	 * Guarda el grafo en un archivo.
	 * @param cantNodos: cantidad de nodos
	 * @param porcentajeAdya: porcentaje de adyacencia
	 * @param path: ruta del archivo
	 * @throws NodosException
	 * @throws PorcentajeException
	 * @throws GradoException 
	 */
	public static void regularPorAdyacencia(int cantNodos, double porcentajeAdya, String path) throws NodosException, PorcentajeException, GradoException {

		if(porcentajeAdya < 0 || porcentajeAdya > 1 )
			throw new PorcentajeException("Valor porcentual incorrecto");
		
		double adyReal=0;
		int suma = 0, aSumar, salto=1, grado = 0;
		int maxAristas = cantNodos*(cantNodos-1)/2;
		
		/*
		 * Si cantNodos es par, el salto es 1 y aSumar es la mitad de cantNodos
		 * Si cantNodos es impar, el salto es 2 y aSumar es cantNodos redondo
		 */
		salto += cantNodos%2;
		aSumar = cantNodos*salto/2;
		
		/* Version anterior
		if(cantNodos%2 == 0) {
			aSumar = cantNodos/2;
			salto = 1;
		}else {
			aSumar = cantNodos;
			salto = 2;
		}
		*/
		
		while(adyReal < porcentajeAdya) {
			suma += aSumar;
			adyReal = (double)suma/maxAristas;
			grado += salto;
		}
		
		//Con lo anterior definimos el grado, ahora es hacer un grafo regular a partir de ese grado
		regularGrado(cantNodos, grado, path);
		
		/* Version anterior
		//Aca podria reutilizar el codigo directamente de GrafoRegularGrado, pero no se me ocurre como
		if(grado%2==1) {
			for(int n=0 ; n < cantNodos/2 ; n++)
				grafo.setArista(n, n+cantNodos/2);
			grado--;
		}
		int aux=grado/2;
		for(int n=1 ; n <= aux ; n++) {
			for(int i=0 ; i < cantNodos ; i++) {
				grafo.setArista( i , n % cantNodos );
			}
		}
		
		grafo.grabarArchivo(path);
		*/
	}
	
	public static void regularGrado(int cantNodos, int grado, String path) throws NodosException, GradoException {
		Grafo grafo = new Grafo(cantNodos);
		
		if(grado<0 || cantNodos<=grado || ( cantNodos%2==1 && grado%2==1 ) )
			throw new GradoException("Grafo imposible de crear.");

		if(grado%2==1) {
			for(int n=0 ; n < cantNodos/2 ; n++)
				grafo.setArista(n, n+cantNodos/2);
			grado--;
		}
		int aux=grado/2;
		for(int n=1 ; n <= aux ; n++) {
			for(int i=0 ; i < cantNodos ; i++) {
				grafo.setArista( i , n % cantNodos );
			}
		}
		
		grafo.grabarArchivo(path);
	}
	
	/**
	 * 
	 * @param cantNodos cantidad de nodos que compondran el grafo.
	 * @param k cantidad de conjuntos de nodos disconjuntos entre si.
	 * @throws NodosException 
	 */
	public static void kPartito(int cantNodos, int k, String path) throws NodosException{
		int[] pertenencia = new int[cantNodos]; //vector que guarda a que conjunto pertenece cada nodo
		Grafo grafo = new Grafo(cantNodos);
		int conj=0;
		for(int i=0; i<cantNodos; i++){
			if(conj==k)
				conj=0;
			pertenencia[i]=conj;
			conj++;
		}
		//recorro cada conjunto posible de nodos
		for(int i=0;i<cantNodos-1;i++){
			for(int j=i+1;j<cantNodos;j++){
				// si no pertenecen al mismo conj, entonces les asigno una arista.
				if(pertenencia[i]!=pertenencia[j]) 
					grafo.setArista(i,j);
			}
		}
		grafo.grabarArchivo(path);
	}
}
