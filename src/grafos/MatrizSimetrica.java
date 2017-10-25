package grafos;

public class MatrizSimetrica {

	private boolean[] vec;
	private int grado;
	//private int cant_valores;
	
	public MatrizSimetrica(int grado){
		this.grado = grado;
		//this.cant_valores=grado*(grado - 1)/2;
		this.vec= new boolean[grado*(grado - 1)/2];
		/*for(int i=0; i<this.cant_valores;i++)
			this.vec[i]=false;
		*/
	}
	
	private int formulaMagica(int fila, int columna) {
		if(fila>columna)
			return columna*this.grado+fila-(columna*columna+3*columna+2)/2;
		return fila*this.grado+columna-(fila*fila+3*fila+2)/2;
	}
	
	public boolean getAdyacencia(int f,int c){
		return this.vec[formulaMagica(f,c)];
	}
	
	public void setAdyacencia(int f, int c){
		this.vec[formulaMagica(f,c)] = true;
	}
	
	public void unsetAdyacencia(int f, int c){
		this.vec[formulaMagica(f,c)] = false;
	}
	
	public void mostrarMatriz(){
		for(int f=0; f < this.grado-1; f++){
			for(int c=f+1; c < this.grado; c++)
				System.out.print( this.vec[formulaMagica(f,c)] + "\t");
			System.out.println();
		}
	}
	
	/*
	public double randomCalculado(double pAdyacencia){
		PosicionConRandom[] posis=new PosicionConRandom[this.cant_valores];
		int k=0;
		for(int i=0;i<this.grado-1;i++){
			for(int j=i+1;j<this.grado;j++){
				posis[k] = new PosicionConRandom(i,j);
				k++;
			}
		}
		//El siguiente ordenamiento, debe ser optimizado con un compareTo natural, o semejante
		PosicionConRandom aux = new PosicionConRandom(0,0);
		for(int i=0;i<this.cant_valores-1;i++){
			for(int j=i+1;j<this.cant_valores;j++){
				if(posis[i].mayorA(posis[j])){
					aux.copiar(posis[i]);
					posis[i].copiar(posis[j]);
					posis[j].copiar(aux);
				}
			}
		}
		int valCorte=(int)(pAdyacencia*this.cant_valores);
		for(k=0;k<valCorte;k++) {
			this.setAdyacencia(posis[k].getF(), posis[k].getC());
			System.out.println(k);
		}
		return (double)valCorte/this.cant_valores;
	}
	*/
}


