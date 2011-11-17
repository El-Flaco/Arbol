/*
 * Interfaz para clase que maneje un árbol binario
 */
package arbol;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author ThElFlacox
 */
public interface MiArbol <E> extends Collection <E> {
    boolean hijo (E e);//Devuelve verdadero si el nodo tiene un hijo
    E hijoDer (E e);//Devuelve el hijo derecho del nodo
    E hijoIzq (E e);//Devuelve el hijo izquierdo del nodo
    Iterator <E> hojas();
    int gradoNodo (E e);//Devuelve el grado del nodo
    int gradoArbol();//Devuelve el grado del arbol
    MiArbol subArbolDe(E e);
    MiArbol subArbolIz(E e);
    Iterator <E> preOrden();
    Iterator <E> inOrden();
    Iterator <E> posOrden();
    E nodoRaiz();//Devuelve el nodo raiz del arbol
}
