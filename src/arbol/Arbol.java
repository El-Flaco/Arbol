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
      private Integer size = 0;
      private Integer peso = 0;
		
	/*-CONSTRUCTORES-*/
	public Arbol(){
		this.raiz = null;
	}
	
	/*-METODOS DE ARBOL-*/
	public void setRaiz(Nodo n){
		this.raiz = n;
	}
	
        public void setSize(Integer s){
            this.size = s;
        }
        
        public Integer getPeso()
        {
            peso = -1;
            Iterator it = this.iterator();
            while(it.hasNext()){
                it.next();
                peso++;
            }
            return peso;
        }

        
	/*-CLASE NODO-*/
    class Nodo<E extends Comparable>{
    	private Nodo<E> hD;
        private E inf;
    	private Nodo<E> hI;
    	private Integer niv;
        private Integer num;
        
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
    	
        
    	public void setNiv(Integer n){
    		this.niv = n;
    	}
        
        public void setNum(Integer n){
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
        
        public Integer getNiv(){
    		return this.niv;
    	}
        
        public Integer getNum(){
    		return this.num;
    	}
        
         public Integer compareTo(E valor){
            return compareTo(valor);
        }
    }
    
    /*------------METODOS IMPLEMENTADOS------------*/
    @Override
    public boolean hijo(E e) {
    	boolean b = false;
    	Iterator it = this.iterator();        
        Nodo<E> nodo = new Nodo<E>();
        
    	if(this.raiz!=null){
            while(it.hasNext()){
                nodo = (Nodo<E>) it.next();
                if(nodo.getInfo().compareTo(e) == 0){
                    if(hijoDer(nodo.getInfo()) != null ) b = true;
                    if(hijoIzq(nodo.getInfo()) != null ) b = true;
                }
            }
    		
    	}
    	
        return b;
    } //B

    @Override
    public E hijoDer(E e) {
        Iterator it = this.iterator();        
        Nodo<E> nodo = new Nodo<E>();
        Nodo<E> aux = new Nodo<E>();
        
        while(it.hasNext()){
            nodo = (Nodo<E>) it.next();
            if(nodo.getInfo().compareTo(e) == 0){
                aux = nodo.getHd();   
            }
        }
        
         if(aux != null){
         return (E) aux.getInfo();
         }else{return null;}
    }   //B

    @Override
    public E hijoIzq(E e) {
        Iterator it = this.iterator();        
        Nodo<E> nodo = new Nodo<E>();
        Nodo<E> aux = new Nodo<E>();
        
        while(it.hasNext()){
            nodo = (Nodo<E>) it.next();
            if(nodo.getInfo().compareTo(e) == 0){
                 aux = nodo.getHi();
            }
        }
        
         if(aux!=null){
         return (E) aux.getInfo();
         }else{return null;}
    }   //B

    @Override
    public Iterator<E> hojas() {   	
        return new HojasIter(this.raiz);
    }   //B

    @Override
    public int gradoNodo(E e) {
        Integer grado=-1;
        Iterator it = this.iterator();        
        Nodo<E> nodo = new Nodo<E>();
        
       if(!this.isEmpty()){
          
           grado = 0;
           
           while(it.hasNext()){
               nodo = (Nodo<E>) it.next();
               if(nodo.getInfo().compareTo(e) == 0){
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
        Integer grado = 0;
        Integer max = -1;
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
    	Integer nivel = 0;        
        Iterator it = this.iterator();        
        Nodo<E> nodo = new Nodo<E>();
        
        if(this.raiz.getInfo()!=null){            
            while(it.hasNext()){                
             nodo = (Nodo<E>) it.next();                 
                if(nodo.getInfo().compareTo(e)==0)
                    nivel=nodo.getNiv();
            }
        }else{
            nivel=null;
        }
        return nivel;
    }   //B
    
    public Integer altura(){
    	Integer altura;
    	Integer max = -1;
        Iterator it = this.hojas();
        
           while(it.hasNext()){
                altura=this.nivel((E) it.next());
                if(altura > max){
                    max = altura;
                }
            }
    	return max;
    }   //B
    
    @Override
    public MiArbol subArbolIz(E e) {
        Iterator it = this.iterator();        
        Nodo<E> nodo = new Nodo<E>();
        Arbol sari = new Arbol();
        nodo=this.raiz;
        while(it.hasNext())
        {
            nodo= (Nodo) it.next();
            if(nodo.getInfo().compareTo(e)==0)
            {
                sari = new SubArb(1, nodo);
            }            
        }        
        return sari;
    }   //B
    
    @Override
    public MiArbol subArbolDe(E e) {
        Iterator it = this.iterator();        
        Nodo<E> nodo = new Nodo<E>();
        Arbol sard = new Arbol();
        nodo=this.raiz;
       
        while(it.hasNext())
        {
            nodo= (Nodo) it.next();
            if(nodo.getInfo().compareTo(e)==0)
            {
                sard= new SubArb(2, nodo);
            }
        }
        return sard;
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
        try{
            return (E) this.raiz.getInfo();
        }catch(Exception ex){
            return null;
        }
    }   //B

    @Override
    public boolean add(E e) {
    	boolean b = false;
        Nodo<E> n = new Nodo<E>();
        
    	if(this.raiz==null){
    		//Si el árbol está vacío lo guarda en la raíz 
                n.setInfo(e); 
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
                            n = n.getHd(); 
                        }
                        }
    		}
    	}
    	
    	return b;
    }   //B
    
    @Override
    public boolean addAll(Collection<? extends E> c) {
    	boolean b = false;
        Object o[] = (Object[]) c.toArray();
    	if(o.length == 0)
            return b;
    	for(Integer i = 0 ; i < o.length; i++){
            this.add((E)o[i]);
            b=true;
        }   
    	return b;
    } //B
    
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
    
    /*-CLASES IntegerERNAS-*/
    class RecorridoIter<E> implements Iterator<E> {
        private Integer indice;
        private Object[] arr;
        Nodo  nodo = new Nodo ();
        
        public RecorridoIter(Nodo n,Integer i){
            this.nodo = n;
            this.indice = 0;
            switch(i){
                case 1:
                    arr = new Object[size()+1];
                    recPreOr(nodo);//preOrden
                    this.indice = 0;
                    break;
                case 2:
                    arr = new Object[size()+1];
                    recInOr(nodo);//inOrden
                    this.indice = 0;
                    break;
                case 3: 
                    arr = new Object[size()+1];
                    recPosOr(nodo);//posOrden
                    this.indice = 0;
                    break;
                case 4: 
                    arr = new Object[size()+1];
                    recIter(nodo);
                    this.indice = 0;
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
    
    class HojasIter implements Iterator<E> {
        private Integer indice;
        Object[] arr;
        Nodo <E> n = new Nodo <E>();
        
        public HojasIter(Nodo <E> raiz) {
           this.arr = new Object[size()+1];
           this.indice = 0;
            this.n = raiz;
            buscaHojas(n);
            this.indice=0;
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
    
    class SubArb extends Arbol{
        public SubArb(Integer i, Nodo n) {
            switch (i){
                case 1:
                    subI(n);
                    break;
                case 2:
                    subD(n);
                    break;
            }
        }
        
        public final void subI(Nodo n){
            Integer x = 1;
            super.raiz = n.getHi();
            this.setSize(cuentaNod(n,x)+1);
        }
        
        public final void subD(Nodo n){
            Integer x = 1;
            super.raiz = n.getHd();
            this.setSize(cuentaNod(n,x)+1);
        }
        
        public Integer cuentaNod(Nodo n, Integer x){
            
            
            if(n.getHd()/*.getInfo()*/ != null && n.getHi()/*.getInfo()*/ != null){
                x+=2;
                x = x + cuentaNod(n.getHd(),x);
                x = x + cuentaNod(n.getHi(),x);
            }
            
            if(n.getHd() == null && n.getHi() != null){
                x+=1;
                x = x + cuentaNod(n.getHi(),x);
            }
            
            if(n.getHd() != null && n.getHi() == null){
                x+=1;
                x = x + cuentaNod(n.getHd(),x);
            }
            
            return x;
        }
    }
    
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