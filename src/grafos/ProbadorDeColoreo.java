package grafos;

import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProbadorDeColoreo {

	public boolean probadorDeColoreo(String pathIn, String pathOut) throws FileNotFoundException{
		Grafo grafo = new Grafo(pathIn);
		Scanner sc = new Scanner(new File(pathOut));
		if(sc.nextInt() != grafo.cantNodos){
			System.out.println("ERROR: la cantidad de nodos es diferente en el archivo de entrada y de salida.");
			sc.close();
			return false;
		}
		int cantColores= sc.nextInt();
		if(cantColores>grafo.cantNodos){
			System.out.println("ERROR: hay más colores que nodos");
			sc.close();
			return false;
		}
		sc.nextDouble(); //Porcentaje Ady (N/A)
		sc.nextInt(); //Grado max (N/A)
		sc.nextInt(); //Grado min (N/A)
		HashMap<Integer,Integer> coloreado = new HashMap<Integer,Integer>(grafo.cantNodos);
		Integer colorPrevio,color,nodo;
		while(sc.hasNextLine()){
			nodo=sc.nextInt();
			color=sc.nextInt();
			colorPrevio=coloreado.put(nodo, color);
			if(colorPrevio != null && colorPrevio!=color){
				System.out.println("ERROR: un nodo aparece dos veces con diferentes colores");
				sc.close();
				return false;
			}
		}
		sc.close();
		//chequeo que no haya nodos sin pintar.
		for(int i=0; i<grafo.cantNodos; i++){
			if(!coloreado.containsKey(i+1)){
				System.out.println("ERROR: hay nodo sin color asignado");
				return false;
			}
		}
		
		System.out.println("El coloreo es correcto");
		return true;
	}
	
	public static void main(String[] args) {
		
	}

}
