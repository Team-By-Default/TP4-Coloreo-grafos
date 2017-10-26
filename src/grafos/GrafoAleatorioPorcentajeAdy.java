package grafos;

import java.util.Arrays;
import java.util.Random;

public class GrafoAleatorioPorcentajeAdy extends Grafo {
	
	public GrafoAleatorioPorcentajeAdy (int cantNodos, double porcentaje) {
		super(cantNodos);
		
		Random rnd = new Random();
		PosicionConRandom [] posis = new PosicionConRandom[ super.cantNodos*(super.cantNodos-1)/2 ];
		int k = 0;
		for(int i = 0; i<super.cantNodos-1; i++){
			for(int j = i+1; j<super.cantNodos; j++){
				posis[k] = new PosicionConRandom(i,j,rnd.nextDouble());
				k++;
			}
		}
		
		Arrays.sort(posis);
		
		final int cantAristas = (int) (k * porcentaje);
		for(int i=0; i < cantAristas; i++) {
			super.matAdy.setAdyacencia(posis[i].getF(), 
					posis[i].getC());
		}
	}
}
