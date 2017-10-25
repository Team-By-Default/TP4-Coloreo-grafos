package grafos;

/**
 * Es una estructura donde guarda una celda
 * (fila y columna o i y j) con un random asociado
 * @author laboratorios
 *
 */
public class PosicionConRandom {
	private int f,c;
	private double random;
	
	public PosicionConRandom(int f,int c){
		this.f=f;
		this.c=c;
		this.random=Math.random();
	}
	
	public boolean mayorA(PosicionConRandom that){
		return this.random > that.random; 
	}
	
	public int getF(){
		return this.f;
	}
	
	public int getC(){
		return this.c;
	}
	
	public void copiar(PosicionConRandom that){
		this.f=that.f;
		this.c=that.c;
		this.random=that.random;
	}
}
