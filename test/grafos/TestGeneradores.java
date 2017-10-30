package grafos;

import java.io.IOException;

import org.junit.Test;

import org.junit.Assert;

public class TestGeneradores {
	
	@Test
	public void aleatorioPorAdyacencia() throws PorcentajeException, NodosException, IOException{
		System.out.println("% pedido\t%real");
		
		GeneradorGrafos.aleatorioPorAdy(6, 0.7, "./aleatorioPorAdy.in");
		Grafo grafito = new Grafo("./aleatorioPorAdy.in");
		Assert.assertEquals(6, grafito.getCantNodos());
		System.out.println("0.7\t" + grafito.getPorcentajeAdyReal());
		
		GeneradorGrafos.aleatorioPorAdy(11, 0.5, "./aleatorioPorAdy.in");
		grafito = new Grafo("./aleatorioPorAdy.in");
		Assert.assertEquals(11, grafito.getCantNodos());
		System.out.println("0.5\t" + grafito.getPorcentajeAdyReal());
		
		GeneradorGrafos.aleatorioPorAdy(2, 0, "./aleatorioPorAdy.in");
		grafito = new Grafo("./aleatorioPorAdy.in");
		Assert.assertEquals(2, grafito.getCantNodos());
		System.out.println("0\t" + grafito.getPorcentajeAdyReal());
	}
}