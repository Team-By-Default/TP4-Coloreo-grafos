package grafos;

import java.util.ArrayList;

public class Vertice implements Comparable{

	private Integer color;
	private ArrayList<Integer> vecinos;
	private int nroNodo;
	
	public Vertice(int n){
		nroNodo=n;
		color=null;
		vecinos = new ArrayList<Integer>();
	}
	
	public int getNroNodo(){
		return nroNodo;
	}
	
	public void agregarVecino(int vecino){
		this.vecinos.add(vecino);
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public Integer getColor(){
		return this.color;
	}
	
	public int getCantVecinos(){
		return this.vecinos.size();
	}
	
	public int getVecino(int index){
		return this.vecinos.get(index);
	}

	@Override
	public int compareTo(Object that) {
		return ( ((Vertice)that).vecinos.size() - this.vecinos.size() );
	}
	

}
