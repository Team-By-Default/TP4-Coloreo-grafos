package grafos;

import java.util.HashMap;
import java.util.Locale;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProbadorDeColoreo {

	public static boolean probar(String pathIn, String pathOut) throws FileNotFoundException{
		Grafo grafo = new Grafo(pathIn);
		Scanner sc = new Scanner(new File(pathOut));
		sc.useLocale(Locale.ENGLISH);
		int cantNodosColoreados = sc.nextInt();
		int cantColores= sc.nextInt();
		if(cantColores>grafo.cantNodos){
			System.out.println("ERROR: hay más colores que nodos");
			sc.close();
			return false;
		}
		sc.nextInt(); //Cant. aristas
		sc.nextDouble(); //Porcentaje Ady (N/A)
		sc.nextInt(); //Grado max (N/A)
		sc.nextInt(); //Grado min (N/A)
		HashMap<Integer,Integer> coloreado = new HashMap<Integer,Integer>(grafo.cantNodos);
		Integer colorPrevio,color,nodo;
		for(int i = 0; i < cantNodosColoreados; i++){
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
