package grafos;

public class MatrizSimetrica {

	private boolean[] vec;
	private int grado;
	private int cant_valores;
	
	public MatrizSimetrica(int grado){
		this.grado=grado;
		this.cant_valores=grado*(grado - 1)/2;
		this.vec= new boolean[this.cant_valores];
	}
	
	public boolean getAdyacencia(int f,int c){
		if(f>c){
			return this.vec[c*this.grado+f-(c*c+3*c+2)/2];
		}
		return this.vec[f*this.grado+c-(f*f+3*f+2)/2];
	}
	
	public void setAdyacencia(int f, int c){
		if(f>c){
			this.vec[c*this.grado+f-(c*c+3*c+2)/2]=true;
		}
		this.vec[f*this.grado+c-(f*f+3*f+2)/2]=true;
	}
	
	public void unsetAdyacencia(int f, int c){
		if(f>c){
			this.vec[c*this.grado+f-(c*c+3*c+2)/2]=false;
		}
		this.vec[f*this.grado+c-(f*f+3*f+2)/2]=false;
	}
	
	public void mostrarMatriz(){
		for(int f=0;f<this.grado-1;f++){
			for(int c=f+1;c<this.grado;c++)
				System.out.println(this.vec[f*this.grado+c-(f*f+3*f+2)/2] + "/t");
			System.out.println("/n");
		}
	}
	public void randomCalculado(float pAdyacencia){
		PosicionConRandom[] posis=new PosicionConRandom[this.cant_valores];
		int k=0;
		for(int i=0;i<this.cant_valores-1;i++){
			for(int j=i+1;j<this.cant_valores;j++){
				posis[k] = new PosicionConRandom(i,j);
				k++;
			}
		}
		k=0;
		PosicionConRandom aux = new PosicionConRandom(0,0);
		for(int i=0;i<this.cant_valores-1;i++){
			for(int j=i+1;j<this.cant_valores;j++){
				if(posis[i].mayorA(posis[j])){
					aux.copiar(posis[i]);
					posis[i].copiar(posis[j]);
					posis[j].copiar(posis[i]);
				}
			}
		}
	}
	
}


