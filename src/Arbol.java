/* 
 * Clase para manejar un arbol binario
 *
 *Copyright (C) 2011  Federico Ramayo
 *
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation, either version 3 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Arbol;

import java.util.*;

/**
 * @author ThElFlacox
 */
public class Arbol <E extends Comparable> implements MiArbol <E>{

      private Nodo<E> raiz;
      private int size = 0;
		
	/*-CONSTRUCTORES-*/
	public Arbol(){
		this.raiz = null;
	}
	
	/*-METODOS DE ARBOL-*/
	public void setRaiz(E n){
		this.raiz = (Nodo) n;
	}
	
        public void setSize(int s){
            this.size = s;
        }
	/*-CLASE NODO-*/
    class Nodo<E>{
    	private Nodo<E> hD;
        private E inf;
    	private Nodo<E> hI;
    	private int niv;
        private int num;
        
    	public Nodo(){}
        public Nodo(E e){
            this.inf =  e;
            this.hD = null;
            this.hI = null;
        }
        
    	
    	public void setHi(Nodo n){
    		this.hI = n;
    	}
        
    	public void setHd(Nodo n){
    		this.hD = n;
    	}
    	
        
    	public void setNiv(int n){
    		this.niv = n;
    	}
        
        public void setNum(int n){
    		this.num = n;
    	}
        
    	public void setInfo(E n){
    		this.inf = n;
    	}
    	
    	public Nodo getHi(){
    		return this.hI;
    	}
    	
    	public Nodo getHd(){
    		return this.hD;
    	}
    	
    	public E getInfo(){
    		return this.inf;
    	}
        
        public int getNiv(){
    		return this.niv;
    	}
        
        public int getNum(){
    		return this.num;
    	}
    }
    
    /*------------METODOS IMPLEMENTADOS------------*/
    public boolean hijo(E e) {
    	boolean b = false;
    	
    	if(!this.isEmpty()){
    		if(hijoDer(e) != null || hijoIzq(e) != null) b = true;
                if(hijoDer(e) != null && hijoIzq(e) != null) b = true;
    	}
    	
        return b;
    } //B

    public E hijoDer(E e) {
        Nodo<E> n = (Nodo) e;
         return (E) n.getHd(); 
    }   //B

    public E hijoIzq(E e) {
    	Nodo<E> n = (Nodo) e;
         return (E) n.getHi(); 
    }   //B

    public Iterator<E> hojas() {   	
        return new HojasIter();
    }   //B

    public int gradoNodo(E e) {
        int grado=-1;
        
       if(!this.isEmpty()){
           Nodo n = this.raiz;
           grado = 0;
           
           while((grado < 2) || (n.getHi() == null && n.getHd() == null)){
               if(n.equals(e)){
                   
                if(n.getHi() != null && n.getHd() != null) grado = 2;
                if(n.getHi() != null && n.getHd() == null){ 
            		grado = 1;
            		n = n.getHi();
                }
                if(n.getHi() == null && n.getHd() != null){ 
            		grado = 1;
            		n = n.getHd();
                }
               }
               else{
                   if(n.getHi() != null && n.getHd() == null) n = n.getHi();
                   if(n.getHi() == null && n.getHd() != null) n = n.getHd();
              }
           }
       }
        
        return grado;
    } //...-+

    public int gradoArbol(){
        int grado=0;
        Nodo n = this.raiz;
        
        if (!this.isEmpty()){
        	while((grado < 2) || (n.getHi() == null && n.getHd() == null)){
        		if(n.getHi() != null && n.getHd() != null) grado = 2;
            	if(n.getHi() != null && n.getHd() == null){ 
            		grado = 1;
            		n = n.getHi();
            	}
            	if(n.getHi() == null && n.getHd() != null){ 
            		grado = 1;
            		n = n.getHd();
            	}
        	}
        }
        else grado = -1;
        
        return grado;
    } //...+-
    
    public int nivel(E e){
    	int nivel = 0;
    	Nodo<E> n = (Nodo<E>) e;
    	nivel = n.getNiv();
    	return nivel;
    }   //B
    
    public int altura(){
    	int altura = 0;
    	
                
        
    	return altura;
    }   //...

    public MiArbol subArbolDe(E e) {
        Arbol arD = new Arbol();
        
        return arD;
    }   //...

    public MiArbol subArbolIz(E e) {
        Arbol arI = new Arbol();
        
        return arI;
    }   //...

    public Iterator<E> preOrden() {
    	return new RecorridoIter<E>();
    }   //...+-

    public Iterator<E> inOrden() {
    	return new RecorridoIter<E>();
    }   //...+-

    public Iterator<E> posOrden() {
        return new RecorridoIter<E>();
    }   //...+-

    public E nodoRaiz() {
        return (E) this.raiz;
    }   //B

    public boolean add(E e) {
    	boolean b = false;
        Nodo n;
        
    	if(this.isEmpty()){
    		//Si el árbol está vacío lo guarda en la raíz 
                n =  new Nodo(e);
                n.setNiv(0);
    		this.raiz = n;
                this.setSize(1);
    	}
    	else{
    		//Si el árbol no está vacío empiezo a comparar desde la raíz
    		n = this.raiz;
    		
    		while((b == false) || (e.compareTo(n) != 0)){
    			//Si es menor y el nodo no tiene hijo IZQ lo carga
    			if((e.compareTo(n) < 0) && (hijoIzq((E)n) == null)){
        			Nodo n1 = new Nodo(e);
        			n1.setNiv(nivel((E)n)+1);
                                n1.setNum(size()+1);
                                n.setHi(n1);
                                this.setSize(size()+1);
                                b = true;
        		}
        		else{
                            n =(Nodo) hijoIzq((E)n);
                        }
        		
    			//Si es mayor y el nodo no tiene hijo DER lo carga
        		if((e.compareTo(n) > 0) && (hijoDer((E)n) == null)){
        			Nodo n2 = new Nodo(e);
        			n2.setNiv(nivel((E)n)+1);
                                n2.setNum(size()+1);
                                n.setHd(n2);
                                this.setSize(size()+1);
                                b = true;
        		}
        		else{
                            n = (Nodo)hijoDer((E)n);
                        }
    		}
    	}
    	
    	return b;
    }   //B
    
    public boolean addAll(Collection<? extends E> c) {
    	boolean b = false;
    	
    	
    	
    	return b;
    }   //...
    
    public void clear() {
        this.raiz = null;
        this.setSize(0);
    }   //B
    
     public boolean contains(Object o) {
    	boolean b=false;
        
    	if(o == null)
            throw new NullPointerException();
        else{
            RecorridoIter<E> it = new RecorridoIter<E>();
            while(it.hasNext()){
                Object comp = it.next();
                if(o.equals(comp)) return b = true;
            }
        }
    	return b;
    }   //B
     
    public boolean isEmpty() {
        boolean b;
        if (this.nodoRaiz() == null)  b = true;
        else b = false;
        return b;
    }   //B
   
    public Iterator<E> iterator() {
        return new RecorridoIter<E>();
    }   //...-+
    
    public int size() {
        return this.size;
    }   //B
    
    /*-CLASES ITERATOR-*/
    class RecorridoIter<E> implements Iterator<E> {
        private int indice;
        
        public RecorridoIter(){}
        
            @Override
            public boolean hasNext() {       
                return indice < size();
            }

            @Override
            public E next() {
                Nodo<E> n = null;
                Nodo<E> aux = (Nodo<E>) nodoRaiz();
                if (!hasNext())
                    throw new NoSuchElementException();
               
                while((n != null) || (!hasNext())){
                 if(aux.num == indice){
                    n = aux;
                 }
                 else{
                    if(aux.getHi() != null && aux.getHd() == null){
                        aux = aux.getHi();
                    }
                    if(aux.getHi() == null && aux.getHd() != null){
                        aux = aux.getHd();
                    }
                    if(aux.getHi() != null && aux.getHd() != null){
                        aux = aux.getHi();
                    }
                 }
                 this.indice+=1;
                }
                return (E)n;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
    }
    
    class HojasIter implements Iterator<E> {

        public HojasIter() {
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public E next() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    /*------------NO IMPLEMENTADOS------------*/
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
