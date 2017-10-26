package grafos;

import java.util.Random;

public class GrafoAleatorioProb extends Grafo{
	
	public GrafoAleatorioProb(int cantNodos, double probabilidad) throws NodosException {
		super(cantNodos);
		
		Random rnd = new Random();
		for(int i = 0; i < super.cantNodos-1; i++){
			for(int j = i+1; j < super.cantNodos; j++){
				if(rnd.nextDouble() <= probabilidad)
					super.matAdy.setAdyacencia(i, j);
			}
		}
	}

}
