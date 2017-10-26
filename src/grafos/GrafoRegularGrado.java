package grafos;

public class GrafoRegularGrado extends Grafo {

	public GrafoRegularGrado(int cantNodos, int grado) throws NodosException, GradoException {
		super(cantNodos);
		
		if(grado<0 || cantNodos<=grado || ( cantNodos%2==1 && grado%2==1 ) )
			throw new GradoException("Grafo imposible de crear.");

		if(grado%2==1) {
			for(int n=0 ; n < cantNodos/2 ; n++)
				super.matAdy.setAdyacencia(n, n+cantNodos/2);
			grado--;
		}
		int aux=grado/2;
		for(int n=1 ; n <= aux ; n++) {
			for(int i=0 ; i < cantNodos ; i++) {
				this.matAdy.setAdyacencia( i , controlDeVuelta(i+n) );
			}
		}
	}

	private int controlDeVuelta(int n) {
		if(n >= super.cantNodos)
			return n-super.cantNodos;
		return n;
	}
}
