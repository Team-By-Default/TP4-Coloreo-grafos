package grafos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Run {
	
	public void generarTablas(Grafo grafito, String path) throws IOException{
		int []vec = new int[grafito.getCantNodos()+1];
		int min=grafito.coloreoSecuencialAleatorio(null), corrida=0;
		
		for(int i = 1; i<10000; i++){
			int aux=grafito.coloreoSecuencialAleatorio(null);
			vec[aux]++;
			if(aux<min){
				min=aux;
				corrida=i;
			}	
		}
		
		grabarTabla(path+"A.txt", min, corrida, vec);
		
		min=grafito.coloreoWelshPowell(null);
		corrida=0;
		
		for(int i = 1; i<10000; i++){
			int aux=grafito.coloreoWelshPowell(null);
			vec[aux]++;
			if(aux<min){
				min=aux;
				corrida=i;
			}	
		}
		
		grabarTabla(path+"WP.txt", min, corrida, vec);
		
		min=grafito.coloreoMatula(null);
		corrida=0;
		
		for(int i = 1; i<10000; i++){
			int aux=grafito.coloreoMatula(null);
			vec[aux]++;
			if(aux<min){
				min=aux;
				corrida=i;
			}	
		}
		
		grabarTabla(path+"M.txt", min, corrida, vec);
	}

	private void grabarTabla(String path, int min, int corrida, int vec[]) throws IOException {
		PrintWriter arch = new PrintWriter(new FileWriter(path));
		arch.println(min + " " + corrida);
		arch.println("Cant Colores\tCant Veces");
		
		for(int i=1; i<vec.length; i++){
			arch.println(i + "\t" + vec[i]);
		}
		
		arch.close();
	}
	
	

}
