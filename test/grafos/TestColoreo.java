package grafos;

import java.io.FileNotFoundException;

import org.junit.Test;

import org.junit.Assert;

public class TestColoreo {
	private String path = "./Pruebas probador coloreo/";
	
	@Test
	public void casoTrue1() throws FileNotFoundException{
		String nombre = path + "CasoTrue1";
		Assert.assertEquals(true, ProbadorDeColoreo.probar(nombre + ".in", nombre + ".out"));
	}
	
	@Test
	public void casoTrue2() throws FileNotFoundException{
		String nombre = path + "CasoTrue2";
		Assert.assertEquals(true, ProbadorDeColoreo.probar(nombre + ".in", nombre + ".out"));
	}
	
	@Test
	public void casoTrue3() throws FileNotFoundException{
		String nombre = path + "CasoTrue3";
		Assert.assertEquals(true, ProbadorDeColoreo.probar(nombre + ".in", nombre + ".out"));
	}
	
	@Test
	public void coloresSeguidos() throws FileNotFoundException{
		String nombre = path + "ColoresSeguidos";
		Assert.assertEquals(false, ProbadorDeColoreo.probar(nombre + ".in", nombre + ".out"));
	}
}
