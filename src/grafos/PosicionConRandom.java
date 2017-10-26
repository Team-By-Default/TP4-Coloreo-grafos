package grafos;

/**
 * Es una estructura donde guarda una celda
 * (fila y columna o i y j) con un random asociado
 * @author laboratorios
 *
 */
public class PosicionConRandom implements Comparable<PosicionConRandom>{
	private int fila;
	private int columna;
	private Double random;
	
	public PosicionConRandom(int fila, int columna, double rnd){
		this.fila = fila;
		this.columna = columna;
		this.random = new Double(rnd);
	}
	//Innecesario gracais al compareTo
	/*
	public boolean mayorA(PosicionConRandom that){
		return this.random > that.random; 
	}
	*/
	public int getF(){
		return this.fila;
	}
	
	public int getC(){
		return this.columna;
	}
	
	/*
	public void copiar(PosicionConRandom that){
		this.fila=that.fila;
		this.columna=that.columna;
		this.random=that.random;
	}
	*/

	@Override
	/**
	 * Compara pesos o random asociados a la posicion
	 * @param o es la Posicion contra la cual comparar
	 * @return
	 */
	public int compareTo(PosicionConRandom o) {
		return this.random.compareTo(o.random);
	}
}
