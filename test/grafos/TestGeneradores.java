package grafos;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGeneradores {
	Grafo grafito;
	
	@Before
	public void before(){
		System.out.println("% ady pedida\t% ady real");
	}
	
	@Test
	public void regularPorAdyacencia1() throws PorcentajeException, NodosException, IOException, GradoException{
		
		GeneradorGrafos.regularPorAdyacencia(5, 0.5, "./regularPorAdy.in");
		grafito = new Grafo("./regularPorAdy.in");
		Assert.assertEquals(5, grafito.getCantNodos());
		int grado = grafito.getGradoNodo(0);
		for(int i = 1; i < grafito.getCantNodos(); i++)
			Assert.assertEquals(grado, grafito.getGradoNodo(i));
		System.out.println("0.5\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void regularPorAdyacencia2() throws PorcentajeException, NodosException, IOException, GradoException{
		
		GeneradorGrafos.regularPorAdyacencia(10, 0, "./regularPorAdy.in");
		grafito = new Grafo("./regularPorAdy.in");
		Assert.assertEquals(10, grafito.getCantNodos());
		int grado = grafito.getGradoNodo(0);
		for(int i = 1; i < grafito.getCantNodos(); i++)
			Assert.assertEquals(grado, grafito.getGradoNodo(i));
		System.out.println("0\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void regularPorAdyacencia3() throws PorcentajeException, NodosException, IOException, GradoException{
		
		GeneradorGrafos.regularPorAdyacencia(9, 0.9, "./regularPorAdy.in");
		grafito = new Grafo("./regularPorAdy.in");
		Assert.assertEquals(9, grafito.getCantNodos());
		int grado = grafito.getGradoNodo(0);
		for(int i = 1; i < grafito.getCantNodos(); i++)
			Assert.assertEquals(grado, grafito.getGradoNodo(i));
		System.out.println("0.9\t" + grafito.getPorcentajeAdyReal());
	}
	
	@Test
	public void regularPorAdyacencia4() throws PorcentajeException, NodosException, IOException, GradoException{
		
		GeneradorGrafos.regularPorAdyacencia(10, 0.1, "./regularPorAdy.in");
		grafito = new Grafo("./regularPorAdy.in");
		Assert.assertEquals(10, grafito.getCantNodos());
		int grado = grafito.getGradoNodo(0);
		for(int i = 1; i < grafito.getCantNodos(); i++)
			Assert.assertEquals(grado, grafito.getGradoNodo(i));
		System.out.println("0.1\t" + grafito.getPorcentajeAdyReal());
	}
	
	
	@Test
	public void regularPorGrado1() throws PorcentajeException, NodosException, IOException, GradoException{
		
		GeneradorGrafos.regularGrado(5, 2, "./regularGrado.in");
		grafito = new Grafo("./regularGrado.in");
		Assert.assertEquals(5, grafito.getCantNodos());
		int grado = 2;
		for(int i = 0; i < grafito.getCantNodos(); i++)
			Assert.assertEquals(grado, grafito.getGradoNodo(i));
	}
	
	@Test
	public void regularPorGrado3() throws PorcentajeException, NodosException, IOException, GradoException{
		
		GeneradorGrafos.regularGrado(4, 3, "./regularGrado.in");
		grafito = new Grafo("./regularGrado.in");
		Assert.assertEquals(4, grafito.getCantNodos());
		int grado = 3;
		for(int i = 0; i < grafito.getCantNodos(); i++)
			Assert.assertEquals(grado, grafito.getGradoNodo(i));
	}
	
	@Test
	public void regularException1() throws NodosException, IOException{
		boolean exc = false;
		try {
			GeneradorGrafos.regularGrado(5, 3, "./regularGrado.in");
		} catch (GradoException e) {
			exc = true;
		}
		Assert.assertEquals(true, exc);
	}
	
	@Test
	public void regularException2() throws NodosException, IOException{
		boolean exc = false;
		try {
			GeneradorGrafos.regularGrado(5, -3, "./regularGrado.in");
		} catch (GradoException e) {
			exc = true;
		}
		Assert.assertEquals(true, exc);
	}
	
	@Test
	public void regularException3() throws NodosException, IOException{
		boolean exc = false;
		try {
			GeneradorGrafos.regularGrado(5, 5, "./regularGrado.in");
		} catch (GradoException e) {
			exc = true;
		}
		Assert.assertEquals(true, exc);
	}
	
	@Test
	public void regularAdyException0() throws NodosException, IOException, GradoException{
		boolean exc = false;
		try {
			GeneradorGrafos.regularPorAdyacencia(5, -0.5, "./regularPorAdy.in");
		} catch (PorcentajeException e) {
			exc = true;
		}
		Assert.assertEquals(true, exc);
	}
	
	@Test
	public void regularAdyException1() throws NodosException, IOException, GradoException{
		boolean exc = false;
		try {
			GeneradorGrafos.regularPorAdyacencia(5, 1.5, "./regularPorAdy.in");
		} catch (PorcentajeException e) {
			exc = true;
		}
		Assert.assertEquals(true, exc);
	}
	
	@Test
	public void profe() throws NodosException, GradoException, IOException, PorcentajeException{
		GeneradorGrafos.regularGrado(500, 80, "./profe.in");
		GeneradorGrafos.regularPorAdyacencia(500, 0.16, "./profe2.in");
	}
}
