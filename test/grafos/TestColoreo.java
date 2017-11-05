package grafos;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TestColoreo {
	
	private String path1 = "./Pruebas coloreo/UnNodo";
	private String path2 = "./Pruebas coloreo/2Nodos2Colores";
	private String path3 = "./Pruebas coloreo/Bipartito";
	private String path4 = "./Pruebas coloreo/Tripartito";
	private String path5 = "./Pruebas coloreo/TodoAislado";
	private String path6 = "./Pruebas coloreo/TodoConexo";
	
	private Grafo grafito;
	
	private boolean testear(String path) throws IOException, NodosException {
		grafito = new Grafo(path + ".in");
		grafito.coloreoSecuencialAleatorio(path + "A.out");
		System.out.println();
		return ProbadorDeColoreo.probar(path + ".in", path + "A.out");
	}

	//Secuencial aleatorio
	@Test
	public void unNodo() throws IOException, NodosException{
		System.out.println("\nTesteando aleatorio con UnNodo");
		Assert.assertEquals(true, testear(path1));
	}
	
	@Test
	public void es2Nodos2Colores() throws IOException, NodosException{
		System.out.println("\nTesteando aleatorio con 2Nodos2Colores");
		Assert.assertEquals(true, testear(path2));
	}
	
	@Test
	public void bipartito() throws IOException, NodosException{
		System.out.println("\nTesteando aleatorio con Bipartito");
		Assert.assertEquals(true, testear(path3));
	}
	
	@Test
	public void tripartito() throws IOException, NodosException{
		System.out.println("\nTesteando aleatorio con Tripartito");
		Assert.assertEquals(true, testear(path4));
	}
	
	@Test
	public void todoAislado() throws IOException, NodosException{
		System.out.println("\nTesteando aleatorio con TodoAislado");
		Assert.assertEquals(true, testear(path5));
	}
	
	@Test
	public void todoConexo() throws IOException, NodosException{
		System.out.println("\nTesteando aleatorio con TodoConexo");
		Assert.assertEquals(true, testear(path6));
	}
	
}