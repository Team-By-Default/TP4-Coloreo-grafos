package grafos;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TestGeneradorNPartitos {

	Grafo grafito;
	
	public boolean testearNPartitos(int nodos,int particiones) throws NodosException, IOException {
		GeneradorGrafos.kPartito(nodos, particiones, "./kPartito.in");
		grafito = new Grafo("./kPartito.in");
		int colores=grafito.coloreoSecuencialAleatorio("kPartito.out");
		int corridas=0;
		while(colores>particiones && corridas<10_000) {
			colores=grafito.coloreoSecuencialAleatorio("kPartito.out");
			corridas++;
		}
		if(corridas==10_000 && colores!=particiones)//si llego a las 10_000 vueltas, y no hallo
			return false;
		return true;
	}
	
	@Test
	public void nPartitoUnNodo() throws NodosException, IOException{
		Assert.assertTrue(testearNPartitos(1,1));
	}
	
	@Test
	public void nPartito2Nodos2Particiones() throws NodosException, IOException {
		Assert.assertTrue(testearNPartitos(2,2));
	}
	
	@Test
	public void nPartitoMuchosNodosUnaParticion() throws NodosException, IOException {
		Assert.assertTrue(testearNPartitos(100,1));
	}
	
	@Test
	public void nPartitoMuchosNodosMuchasParticiones() throws NodosException, IOException {
		Assert.assertTrue(testearNPartitos(100,50));
	}
	
	@Test
	public void nPartitoMuchosNodosMaxParticiones() throws NodosException, IOException {
		Assert.assertTrue(testearNPartitos(100,100));
	}
	
	@Test
	public void nPartitoFatiga() throws NodosException, IOException {
		Assert.assertTrue(testearNPartitos(600,300));
	}
}
