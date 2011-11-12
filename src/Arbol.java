/* 
 * Clase para manejar un arbol binario
 */
package arbol;

import java.util.*;

/**
 *
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
    class Nodo<E extends Comparable>{
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
        
         public int compareTo(E valor){
            return compareTo(valor);
        }
    }
    
    /*------------METODOS IMPLEMENTADOS------------*/
    @Override
    public boolean hijo(E e) {
    	boolean b = false;
    	
    	if(!this.isEmpty()){
    		if(hijoDer(e) != null || hijoIzq(e) != null) b = true;
                if(hijoDer(e) != null && hijoIzq(e) != null) b = true;
    	}
    	
        return b;
    } //B

    @Override
    public E hijoDer(E e) {
        Nodo<E> n = (Nodo) e;
         return (E) n.getHd(); 
    }   //B

    @Override
    public E hijoIzq(E e) {
    	Nodo<E> n = (Nodo) e;
         return (E) n.getHi(); 
    }   //B

    @Override
    public Iterator<E> hojas() {   	
        return new HojasIter(this.raiz);
    }   //B

    @Override
    public int gradoNodo(E e) {
        int grado=-1;
        
       if(!this.isEmpty()){
           Iterator<E> it = this.iterator();
           grado = 0;
           
           while(it.hasNext()){
               Nodo<E> nodo = (Nodo<E>) it.next();
               if(nodo.equals(e)){
                   if(nodo.getHd() != null)
                       grado++;
                   if(nodo.getHi() != null)
                       grado++;
               }
           }
       }
        
        return grado;
    }   //B

    @Override
    public int gradoArbol(){
        int grado = 0;
        int max = -1;
        Iterator<E> it = this.iterator();
        
        if (!this.isEmpty()){
        	while(it.hasNext() && max < 2){
                    grado = 0;
                    Nodo<E> nodo = (Nodo<E>) it.next();
                    if(nodo.getHd() != null)
                       grado++;
                    if(nodo.getHi() != null)
                       grado++;
                    if(grado > max)
                        max = grado;
        	}
        }
        
        return max;
    }   //B
    
    public int nivel(E e){
    	int nivel = 0;
        
        Iterator it = this.iterator();
        
        Nodo<E> nodo = new Nodo<E>();
        if(this.raiz.getInfo()!=null){
            
            while(it.hasNext()){
                
             nodo = (Nodo<E>) it.next();// = (Nodo<E>) it.next();
                 
                if(nodo.getInfo().compareTo(e)==0)
                    nivel=nodo.getNiv();
            }
        }
        return nivel;
    }   //B
    
    public int altura(){
    	int altura;
    	int max = -1;
        Iterator it = this.iterator();
        
        Nodo<E> nodo = new Nodo<E>();
        if(this.raiz.getInfo()!=null){
            
            while(it.hasNext()){
                nodo = (Nodo<E>) it.next();// = (Nodo<E>) it.next();
                altura = nodo.getNiv(); 
                if(altura > max)
                    max = altura;
            }
        }
        
    	return max;
    }   //B
    
    @Override
    public MiArbol subArbolIz(E e) {
        return new SubArb(1, e, this);
    }   //B
    
    @Override
    public MiArbol subArbolDe(E e) {
        return new SubArb(2, e, this);
    }   //B
    
    @Override
    public Iterator<E> preOrden() {
    	return new RecorridoIter<E>(this.raiz,1);
    }   //B

    @Override
    public Iterator<E> inOrden() {
    	return new RecorridoIter<E>(this.raiz,2);
    }   //B

    @Override
    public Iterator<E> posOrden() {
        return new RecorridoIter<E>(this.raiz,3);
    }   //B

    @Override
    public E nodoRaiz() {
        return (E) raiz.getInfo();
    }   //B

    @Override
    public boolean add(E e) {
    	boolean b = false;
        Nodo<E> n = new Nodo<E>();
        
    	if(this.raiz==null/*this.isEmpty()*/){
    		//Si el árbol está vacío lo guarda en la raíz 
                n.setInfo(e); // new Nodo<E>(e);
                n.setNiv(0);
                n.setNum(size()+1);
    		this.raiz = n;
                this.setSize(1);
                b = true;
    	}
    	else{
    		//Si el árbol no está vacío empiezo a comparar desde la raíz
    		n = this.raiz;
    		while((b == false) && (n.getInfo().compareTo(e) != 0)){
    			//Si es menor y el nodo no tiene hijo IZQ lo carga
                    
    			if(n.getInfo().compareTo(e) > 0){
                            if(n.getHi() == null)
                            {           
        			Nodo<E> n1 = new Nodo(e);
        			n1.setNiv(n.getNiv()+1);
                                n1.setNum(size()+1);
                                n.setHi(n1);
                                this.setSize(size()+1);
                                b = true;
                            }
        		else{
                            n = n.getHi();
                           }
                        }else{
        		
                       	//Si es mayor y el nodo no tiene hijo DER lo carga
        		if((n.getInfo().compareTo(e) < 0) && (n.getHd() == null)){
                            
        			Nodo<E> n2 = new Nodo(e);
        			n2.setNiv((n.getNiv())+1);
                                n2.setNum(size()+1);
                                n.setHd(n2);
                                this.setSize(size()+1);
                                b = true;
        		}
        		else{
                            n = n.getHd(); //(Nodo<E>)hijoDer((E)n);
                        }
                        }
    		}
    	}
    	
    	return b;
    }   //B
    
    @Override
    public boolean addAll(Collection<? extends E> c) {
    	boolean b = false;
        E[] o =  (E[]) c.toArray();
    	if(o.length == 0)
            return b;
    	for(int i = 0 ; i < o.length; i++){
            if(this.raiz == null){
                Nodo<E> n =  new Nodo(o[i]);
                n.setNiv(0);
                n.setNum(size()+1);
    		this.raiz = n;
                this.setSize(1);
            }
            else{
                //Si el árbol no está vacío empiezo a comparar desde la raíz
                Nodo<E> n = this.raiz;
               
    		while((b == false) || (o[i].compareTo(n) != 0)){
    			//Si es menor y el nodo no tiene hijo IZQ lo carga
    			if((o[i].compareTo(n) < 0) && (hijoIzq((E)n) == null)){
        			Nodo<E> n1 = new Nodo(o[i]);
        			n1.setNiv(nivel((E)n)+1);
                                n1.setNum(size()+1);
                                n.setHi(n1);
                                this.setSize(size()+1);
                                b = true;
        		}
        		else{
                            n =(Nodo<E>) hijoIzq((E)n);
                        }
        		
    			//Si es mayor y el nodo no tiene hijo DER lo carga
        		if((o[i].compareTo(n) > 0) && (hijoDer((E)n) == null)){
        			Nodo<E> n2 = new Nodo(o[i]);
        			n2.setNiv(nivel((E)n)+1);
                                n2.setNum(size()+1);
                                n.setHd(n2);
                                this.setSize(size()+1);
                                b = true;
        		}
        		else{
                            n = (Nodo<E>)hijoDer((E)n);
                        }
    		}
            }
        }
        
        
    	return b;
    }   //B
    
    @Override
    public void clear() {
        this.raiz = null;
        this.setSize(0);
    }   //B
    
    @Override
     public boolean contains(Object o) {
    	boolean b=false;
        
    	if(o == null)
            throw new NullPointerException();
        else{
            RecorridoIter<E> it = new RecorridoIter<E>(this.raiz,1);
            while(it.hasNext()){
                Object comp = it.next();
                if(o.equals(comp)) return b = true;
            }
        }
    	return b;
    }   //B
     
    @Override
    public boolean isEmpty() {
        boolean b;
        if (nodoRaiz() == null)  b = true;
        else b = false;
        return b;
    }   //B
   
    @Override
    public Iterator<E> iterator() {
        return new RecorridoIter<E>(this.raiz,4);
    }   //B
    
    @Override
    public int size() {
        return this.size;
    }   //B
    
    /*-CLASES ITERATOR-*/
    class RecorridoIter<E> implements Iterator<E> {
        private int indice;
        private Object[] arr; //= new Object[size()+1];
        Nodo  nodo = new Nodo ();
        
        public RecorridoIter(Nodo n,int i){
            this.nodo = n;
            switch(i){
                case 1:
                    arr = new Object[size()+1]; 
                    indice = 0;
                    recPreOr(nodo);//preOrden
                indice = 0;
                    break;
                case 2:
                    arr = new Object[size()+1];
                    indice = 0; 
                    recInOr(nodo);//inOrden
                    indice = 0;
                    break;
                case 3: 
                    arr = new Object[size()+1];
                    indice = 0;
                    recPosOr(nodo);//posOrden
                    indice = 0;
                    break;
                case 4: 
                    arr = new Object[size()+1];
                    indice = 0;
                    recIter(nodo);//posOrden
                    indice = 0;
                    break;
                default:try {
                            throw new NoSuchMethodException();
                        } catch (NoSuchMethodException ex) {
                            System.out.println(ex);
                        }
            }
        }
        
        private void recPreOr(Nodo nodo){
            if (nodo == null)
                return;
            else{
                arr[indice] = nodo.getInfo();
                indice++;
                recPreOr(nodo.getHi());
                recPreOr(nodo.getHd());
            }
        }   //B
        
        private void recInOr(Nodo nodo){
            if (nodo == null)
                return;
            else{
                recInOr(nodo.getHi());
                arr[indice] = nodo.getInfo();
                indice++;
                recInOr(nodo.getHd());
            }
        }   //B
        
        private void recPosOr(Nodo nodo){
            if (nodo == null)
                return;
            else{
                recPosOr(nodo.getHi());
                recPosOr(nodo.getHd());
                arr[indice] = nodo.getInfo();
                indice++;
            }
        }   //B
        
        private void recIter(Nodo nodo){
            if (nodo == null)
                return;
            else{
                arr[indice] = nodo;
                indice++;
                
                recIter(nodo.getHi());
                recIter(nodo.getHd());
            }
        }   //B
        
            @Override
            public boolean hasNext() {       
                return arr[indice] != null;
            }   //B
            
            
            @Override
            public E next() {
                Object ob = arr[indice];
                if(ob == null)
                    throw new NoSuchElementException();
                indice++;
                return (E)ob;
            }   //B

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }   //B
    }   //B
    
    class SubArb<E extends Comparable> extends Arbol<E>{
        Arbol sub_a;
        
        public SubArb(int tipo, E e, Arbol ar){
            this.sub_a = new Arbol();
            switch(tipo){
                case 1: izq(e,ar);
                    break;
                case 2: der(e,ar);
                    break;
                default:try {
                           throw new NoSuchMethodException();
                        }catch (NoSuchMethodException ex) {
                            System.out.println(ex);
                        }
            }
        }
        
        private void izq(E e, Arbol ar){
            boolean b=false;
            Nodo<E> n = new Nodo<E>();
            
            while(!b){
                n=(Nodo<E>) ar.nodoRaiz();
                if(n.getHi() == null && n.getHd() == null)
                    b = true;
                if(e.compareTo(n) == 0){
                    addS(n.getHi());
                    b = true;
                }
                else{
                    if(e.compareTo(n) < 0)
                        n = n.getHi();
                    if(e.compareTo(n) > 0)
                        n = n.getHd();
                }
            }
        }
        
        private void der(E e, Arbol ar){
            boolean b=false;
            Nodo<E> n = (Nodo<E>) ar.nodoRaiz();
            
            while(!b){
                if(n.getHi() == null && n.getHd() == null)
                    b = true;
                if(e.compareTo(n) == 0){
                    addS(n.getHd());
                    b = true;
                }
                else{
                    if(e.compareTo(n) < 0)
                        n = n.getHi();
                    if(e.compareTo(n) > 0)
                        n = n.getHd();
                }
            }
        }
        
        public void addS(Nodo nod){
            if(nod == null)
                return;
            else{
                this.add((E)nod);
                addS(nod.getHi());
                addS(nod.getHd());
            }
        }
    }   //B
    
    class HojasIter implements Iterator<E> {
        private int indice = 0;
        Object[] arr = new Object[size()+1];
        Nodo <E> n = new Nodo <E>();
        
        public HojasIter(Nodo <E> raiz) {
            this.n = raiz;
            System.out.println("Array "+n.getInfo());
            buscaHojas(n);
            indice=0;
        }
        
        private void buscaHojas(Nodo<E> nodo){
            if(nodo.getHi() != null && nodo.getHd() == null){
                buscaHojas(nodo.getHi());                
            }
            else{
                if(nodo.getHi() == null && nodo.getHd() != null){
                    buscaHojas(nodo.getHd());                    
                }
                else{
                    if(nodo.getHi() != null && nodo.getHd() != null){
                        buscaHojas(nodo.getHi());
                        buscaHojas(nodo.getHd());
                    }else{
                        if(nodo.getHi() == null && nodo.getHd() == null){
                            arr[indice] = nodo.getInfo();
                            indice++;
                            return;
                        }
                    }
                }
            }
        }   //B
        
        @Override
        public boolean hasNext() {       
                return arr[indice] != null;
            }   //B

        @Override
        public E next() {
            Object ob = arr[indice];
            if(ob == null)
                throw new NoSuchElementException();
            indice++;
            return (E)ob;
        }   //B

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }   //B
    }   //B
    
    /*------------NO IMPLEMENTADOS------------*/
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}