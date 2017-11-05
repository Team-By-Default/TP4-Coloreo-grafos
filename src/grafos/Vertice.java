package grafos;

import java.util.ArrayList;
import java.util.Arrays;

public class Vertice implements Comparable{

	private Integer color;
	private ArrayList<Integer> vecinos;
	private int nroNodo;
	
	public Vertice(int n){
		nroNodo=n;
		color=null;
		vecinos = new ArrayList<Integer>();
	}
	
	public int getIndexVecino(Vertice[] lista,Integer vecino) {
		int i=0;
		while(i<lista.length) {
			if(lista[i].getNroNodo()== this.getVecino(vecino))
				return i;
			i++;
		}
		return 0;
	}
	
	public int getNroNodo(){
		return nroNodo;
	}
	
	public void agregarVecino(int vecino){
		this.vecinos.add(new Integer(vecino));
	}
	
	public void setColor(int color){
		this.color = new Integer(color);
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
