package grafos;

import java.io.IOException;

import org.junit.Test;

import org.junit.Assert;

public class TestGeneradores {
	
	@Test
	public void aleatorioPorAdyacencia() throws PorcentajeException, NodosException, IOException{
		GeneradorGrafos.aleatorioPorAdy(6, 0.7, "./aleatorioPorAdy.in");
		Grafo grafito = new Grafo("./aleatorioPorAdy.in");
		Assert.assertEquals(6, grafito.getCantNodos());
		System.out.println(grafito.getPorcentajeAdyReal());
	}
}