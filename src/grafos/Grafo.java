package grafos;

public class Grafo {

	protected MatrizSimetrica matAdy;
	private static final int CANTNODOS=5;
	protected int cantNodos;
	
	public Grafo(){
		this.cantNodos = CANTNODOS;
		this.matAdy = new MatrizSimetrica(CANTNODOS);
	}
	
	public Grafo(int cantNodos) throws NodosException{
		if(cantNodos<1)
			throw new NodosException("La cantidad de nodos debe ser superior a 0.");
		this.cantNodos = cantNodos;
		this.matAdy = new MatrizSimetrica(this.cantNodos);
	}
	
	public void mostrarMatrizAdy() {
		matAdy.mostrarMatriz();
	}
	
	public double getPorcentajeAdyReal() {
		return (double)this.matAdy.getCantAdyacencias()/(this.cantNodos*(this.cantNodos-1)/2);
	}
	
	//Valeria imponiendo autoridad (?
	
	/* Grafo aleatorio por probabilidad se genera con GrafoAleatorioProb
	public double randomRandom(double d){
		double value, realAd=0;
		for(int i=0;i<this.cantNodos-1;i++){
			for(int j=i+1;j<this.cantNodos;j++){
				value=Math.random();
				if(value<=d) {
					matAdy.setAdyacencia(i, j);
					realAd++;
				}
			}
		}
		return 2*(realAd/(this.cantNodos*this.cantNodos-1));
	}
	
	public double randomCalculado(double d){
		return matAdy.randomCalculado(d);
	}
	*/
	
	public static void main(String args[]) throws NodosException, GradoException, PorcentajeException{
		Grafo grafito=new Grafo(10);
		//System.out.println(grafito.randomRandom(0.5));
		//System.out.println(grafito.randomCalculado(0.70));
		grafito.mostrarMatrizAdy();
		System.out.println();
		grafito = new GrafoAleatorioPorcentajeAdy(6, 0.7);
		grafito.mostrarMatrizAdy();
		System.out.println();
		grafito = new GrafoAleatorioProb(6, 0.7);
		grafito.mostrarMatrizAdy();
		System.out.println();
		grafito = new GrafoRegularGrado(5, 2);
		grafito.mostrarMatrizAdy();
		System.out.println(grafito.getPorcentajeAdyReal());
		grafito = new GrafoRegularAdy(5, 0.5);
		grafito.mostrarMatrizAdy();
		System.out.println(grafito.getPorcentajeAdyReal());
		
	}
	
}
