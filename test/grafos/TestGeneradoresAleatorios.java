package grafos;

import java.io.IOException;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;

public class TestGeneradoresAleatorios {
	Grafo grafito;
	
	@Before
	public void before(){
		System.out.println("Tipo\t% pedido\t%real");
	}
	
	@Test
	public void aleatorioPorAdyacencia1() throws PorcentajeException, NodosException, IOException{
		
		GeneradorGrafos.aleatorioPorAdy(6, 0.7, "./aleatorioPorAdy.in");
		grafito = new Grafo("./aleatorioPorAdy.in");
		Assert.assertEquals(6, grafito.getCantNodos());
		System.out.println("XAdy\t0.7\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void aleatorioPorAdyacencia2() throws PorcentajeException, NodosException, IOException{
		
		GeneradorGrafos.aleatorioPorAdy(11, 0.5, "./aleatorioPorAdy.in");
		grafito = new Grafo("./aleatorioPorAdy.in");
		Assert.assertEquals(11, grafito.getCantNodos());
		System.out.println("XAdy \t 0.5\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void aleatorioPorAdyacencia3() throws PorcentajeException, NodosException, IOException{
		
		GeneradorGrafos.aleatorioPorAdy(2, 0, "./aleatorioPorAdy.in");
		grafito = new Grafo("./aleatorioPorAdy.in");
		Assert.assertEquals(2, grafito.getCantNodos());
		System.out.println("XAdy\t0\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void aleatorioPorAdyacencia4() throws PorcentajeException, NodosException, IOException{
		
		GeneradorGrafos.aleatorioPorAdy(2, 1, "./aleatorioPorAdy.in");
		grafito = new Grafo("./aleatorioPorAdy.in");
		Assert.assertEquals(2, grafito.getCantNodos());
		System.out.println("XAdy\t1\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void aleatorioAdy0Exception() throws NodosException, IOException{
		boolean exc = false;
		try {
			GeneradorGrafos.aleatorioPorAdy(2, -0.7, "./aleatorioPorAdy.in");
		} catch (PorcentajeException e) {
			exc = true;
		}
		Assert.assertEquals(true, exc);
	}
	
	@Test
	public void aleatorioAdy1Exception() throws NodosException, IOException{
		boolean exc = false;
		try {
			GeneradorGrafos.aleatorioPorAdy(2, 1.2, "./aleatorioPorAdy.in");
		} catch (PorcentajeException e) {
			exc = true;
		}
		Assert.assertEquals(true, exc);
	}
	
	@Test
	public void aleatorioPorProbabilidad1() throws PorcentajeException, NodosException, IOException{
		
		GeneradorGrafos.aleatorioPorProb(6, 0.7, "./aleatorioPorProb.in");
		grafito = new Grafo("./aleatorioPorProb.in");
		Assert.assertEquals(6, grafito.getCantNodos());
		System.out.println("XProb\t0.7\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void aleatorioPorProbabilidad2() throws PorcentajeException, NodosException, IOException{
		
		GeneradorGrafos.aleatorioPorProb(11, 0.5, "./aleatorioPorProb.in");
		grafito = new Grafo("./aleatorioPorProb.in");
		Assert.assertEquals(11, grafito.getCantNodos());
		System.out.println("XProb\t0.5\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void aleatorioPorProbabilidad3() throws PorcentajeException, NodosException, IOException{
		
		GeneradorGrafos.aleatorioPorProb(2, 0, "./aleatorioPorProb.in");
		grafito = new Grafo("./aleatorioPorProb.in");
		Assert.assertEquals(2, grafito.getCantNodos());
		System.out.println("XProb\t0\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void aleatorioPorProbabilidad4() throws PorcentajeException, NodosException, IOException{
		
		GeneradorGrafos.aleatorioPorProb(2, 1, "./aleatorioPorProb.in");
		grafito = new Grafo("./aleatorioPorProb.in");
		Assert.assertEquals(2, grafito.getCantNodos());
		System.out.println("XProb\t1\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void aleatorioProb0Exception() throws NodosException, IOException{
		boolean exc = false;
		try {
			GeneradorGrafos.aleatorioPorProb(2, -0.7, "./aleatorioPorProb.in");
		} catch (PorcentajeException e) {
			exc = true;
		}
		Assert.assertEquals(true, exc);
	}
	
	@Test
	public void aleatorioProb1Exception() throws NodosException, IOException{
		boolean exc = false;
		try {
			GeneradorGrafos.aleatorioPorProb(2, 1.2, "./aleatorioPorProb.in");
		} catch (PorcentajeException e) {
			exc = true;
		}
		Assert.assertEquals(true, exc);
	}
	
	@Test
	public void nodosNegativos() throws PorcentajeException, IOException {
		boolean exc = false;
		try {
			GeneradorGrafos.aleatorioPorAdy(-3, 0.5, "./aleatorioPorAdy.in");
		} catch (NodosException e) {
			exc=true;
		}
		Assert.assertEquals(true, exc);
	}
}