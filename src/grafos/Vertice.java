package grafos;

import java.util.ArrayList;

public class Vertice implements Comparable<Vertice>{

	private Integer color;
	private ArrayList<Integer> vecinos;
	private int nroNodo;
	
	public Vertice(int n){
		nroNodo=n;
		color=null;
		//vecinos = new ArrayList<Integer>();
	}
	
	/*public int getIndexVecino(Vertice[] lista,Integer vecino) {
		int i=0;
		while(i<lista.length) {
			if(lista[i].getNroNodo()== this.getVecino(vecino))
				return i;
			i++;
		}
		return 0;
	}*/
	
	public int getNroNodo(){
		return nroNodo;
	}
	/*
	public void agregarVecino(int vecino){
		this.vecinos.add(new Integer(vecino));
	}
	*/
	public void setColor(Integer color){
		this.color = color;
	}
	
	public Integer getColor(){
		return this.color;
	}
	/*
	public int getCantVecinos(){
		return this.vecinos.size();
	}
	
	public int getVecino(int index){
		return this.vecinos.get(index);
	}
	*/
	@Override
	public String toString() {
		String string=new String("Nodo: "+this.nroNodo+", color: "+this.color+", vecinos: ");
		for(int i=0;i<this.vecinos.size();i++) {
			string.concat(this.vecinos.get(i)+" ");
		}
		return string;
	}

	@Override
	public int compareTo(Vertice that) {
		return ( that.vecinos.size()-this.vecinos.size() );
	}
}
