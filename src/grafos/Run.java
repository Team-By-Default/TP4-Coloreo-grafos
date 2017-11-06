package grafos;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Run {
	
	public static void generarTablas(Grafo grafito, String path) throws IOException{
		System.out.println("Comenzando a generar tablas...\nSecuencial aleatorio");
		
		int []vec = new int[grafito.getCantNodos()+1];
		int min=grafito.coloreoSecuencialAleatorio(null), corrida=0;
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
		
		System.out.println("\nWelsh Powell");
		min=grafito.coloreoWelshPowell(null);
		corrida=0;
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
		
		System.out.println("\nMatula");
		min=grafito.coloreoMatula(null);
		corrida=0;
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
		generarTablas(new Grafo(path + "grafoAleat40.in"), path + "grafoAleat40");
		/*generarTablas(new Grafo(path + "grafoAleat60.in"), path + "grafoAleat60");
		generarTablas(new Grafo(path + "grafoAleat90.in"), path + "grafoAleat90");
		generarTablas(new Grafo(path + "grafoRegular50.in"), path + "grafoRegular50");
		generarTablas(new Grafo(path + "grafoRegular75.in"), path + "grafoRegular75");
		*/
	}

}
