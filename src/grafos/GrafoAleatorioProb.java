package grafos;

import java.util.Random;

public class GrafoAleatorioProb extends Grafo{
	
	public GrafoAleatorioProb(int cantNodos, double probabilidad) {
		super(cantNodos);
		
		Random rnd = new Random();
		for(int i = 0; i < this.cantNodos-1; i++){
			for(int j = i+1; j < this.cantNodos; j++){
				if(rnd.nextDouble() <= probabilidad)
					mat.setPos(i, j, true);
				else mat.setPos(i, j, false);
			}
		}
	}

}
