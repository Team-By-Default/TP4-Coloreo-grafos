package grafos;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Run {
	
	public static void generarTablasAle(Grafo grafito, String path) throws IOException{
		System.out.println("Comenzando a generar tablas...\nSecuencial aleatorio");
		
		int []vec = new int[grafito.getCantNodos()+1];
		int min=grafito.coloreoSecuencialAleatorio(null), corrida=0;
		vec[min]++;
		grafito.descolorear();
		System.out.print("0 ");
		for(int i = 1; i<10000; i++){
			int aux=grafito.coloreoSecuencialAleatorio(null);
			grafito.descolorear();
			vec[aux]++;
			if(aux<min){
				min=aux;
				corrida=i;
			}
			System.out.print(i + " ");
		}
		 System.out.println("\nGrabando archivo");
		grabarTabla(path+"A.txt", min, corrida, vec);
	}
	
	public static void	generarTablasWP(Grafo grafito, String path) throws IOException{
		System.out.println("Comenzando a generar tablas...\nWelsh Powell");
		
		int []vec = new int[grafito.getCantNodos()+1];
		int min=grafito.coloreoWelshPowell(null);
		vec[min]++;
		int corrida=0;
		System.out.print("0 ");
		for(int i = 1; i<10000; i++){
			int aux=grafito.coloreoWelshPowell(null);
			vec[aux]++;
			if(aux<min){
				min=aux;
				corrida=i;
			}
			System.out.print(i + " ");
		}
		
		 System.out.println("\nGrabando archivo");
		grabarTabla(path+"WP.txt", min, corrida, vec);
	}
	
	public static void	generarTablasM(Grafo grafito, String path) throws IOException{
		System.out.println("Comenzando a generar tablas...\nMatula");
		int []vec = new int[grafito.getCantNodos()+1];
		int min=grafito.coloreoMatula(null);
		vec[min]++;
		int corrida=0;
		System.out.print("0 ");
		for(int i = 1; i<10000; i++){
			int aux=grafito.coloreoMatula(null);
			vec[aux]++;
			if(aux<min){
				min=aux;
				corrida=i;
			}
			System.out.print(i + " ");
		}

		 System.out.println("\nGrabando archivo");
		grabarTabla(path+"M.txt", min, corrida, vec);
	}

	private static void grabarTabla(String path, int min, int corrida, int vec[]) throws IOException {
		PrintWriter arch = new PrintWriter(new FileWriter(path));
		arch.println(min + " " + corrida);
		arch.println("Cant Colores\tCant Veces");
		
		for(int i=1; i<vec.length; i++){
			arch.println(i + "\t" + vec[i]);
		}
		
		arch.close();
	}
	
	public static void main(String arg[]) throws FileNotFoundException, IOException, NodosException{
		String path = "./Analisis Extadistico/";
		
		System.out.println("GRAFO ALEATORIO 40%");
		Grafo grafito1 = new Grafo(path + "grafoAleat40.in");
		generarTablasAle(grafito1, path + "grafoAleat40");
		generarTablasWP(grafito1, path + "grafoAleat40");
		generarTablasM(grafito1, path + "grafoAleat40");
		
		System.out.println("GRAFO ALEATORIO 60%");
		Grafo grafito2 = new Grafo(path + "grafoAleat60.in");
		generarTablasAle(grafito2, path + "grafoAleat60");
		generarTablasWP(grafito2, path + "grafoAleat60");
		generarTablasM(grafito2, path + "grafoAleat60");
		
		System.out.println("GRAFO ALEATORIO 90%");
		Grafo grafito3 = new Grafo(path + "grafoAleat90.in");
		generarTablasAle(grafito3, path + "grafoAleat90");
		generarTablasWP(grafito3, path + "grafoAleat90");
		generarTablasM(grafito3, path + "grafoAleat90");
		
		System.out.println("GRAFO Regular 50%");
		Grafo grafito4 = new Grafo(path + "grafoRegular50.in");
		generarTablasAle(grafito4, path + "grafoRegular50");
		generarTablasWP(grafito4, path + "grafoRegular50");
		generarTablasM(grafito4, path + "grafoRegular50");
		
		System.out.println("GRAFO Regular 75%");
		Grafo grafito5 = new Grafo(path + "grafoRegular75.in");
		generarTablasAle(grafito5, path + "grafoRegular75");
		generarTablasWP(grafito5, path + "grafoRegular75");
		generarTablasM(grafito5, path + "grafoRegular75");
	}

}
