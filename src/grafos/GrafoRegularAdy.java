package grafos;

public class GrafoRegularAdy extends Grafo { //Utiliza una adyacencia superior o igual a la elegida

	public GrafoRegularAdy(int cantNodos,double ady) throws NodosException, PorcentajeException {
		super(cantNodos);

		if(ady < 0 || ady > 1 )
			throw new PorcentajeException("Valor porcentual incorrecto");
		
		double adyReal=0;
		int suma = 0,aSumar,salto,grado=0;
		int maxAristas=cantNodos*(cantNodos-1)/2;
		if(cantNodos%2==0) {
			aSumar=cantNodos/2;
			salto=1;
		}else {
			aSumar=cantNodos;
			salto=2;
		}
		while(adyReal < ady) {
			suma+=aSumar;
			adyReal=(double)suma/maxAristas;
			grado+=salto;
		}
		//Aca podria reutilizar el codigo directamente de GrafoRegularGrado, pero no se me ocurre como
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
	//En especial por esto
	private int controlDeVuelta(int n) {
		if(n >= super.cantNodos)
			return n-super.cantNodos;
		return n;
	}

}
