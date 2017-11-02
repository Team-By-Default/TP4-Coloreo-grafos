package grafos;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGeneradores {
	Grafo grafito;
	
	/*
	@Test
	public void regularPorAdyacencia1() throws PorcentajeException, NodosException, IOException, GradoException{
		
		GeneradorGrafos.regularPorAdyacencia(5, 0.5, "./regularPorAdy.in");
		grafito = new Grafo("./regularPorAdy.in");
		Assert.assertEquals(5, grafito.getCantNodos());
		int grado = grafito.getGradoNodo(0);
		for(int i = 1; i < grafito.getCantNodos(); i++)
			Assert.assertEquals(grado, grafito.getGradoNodo(i));
	}*/
	
	@Test
	public void regularPorGrado1() throws PorcentajeException, NodosException, IOException, GradoException{
		
		GeneradorGrafos.regularGrado(5, 2, "./regularGrado.in");
		grafito = new Grafo("./regularGrado.in");
		Assert.assertEquals(5, grafito.getCantNodos());
		int grado = 2;
		for(int i = 0; i < grafito.getCantNodos(); i++)
			Assert.assertEquals(grado, grafito.getGradoNodo(i));
	}
}
