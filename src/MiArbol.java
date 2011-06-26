/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.arbol;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author ThElFlacox
 */
public interface MiArbol <E> extends Collection <E> {
    boolean hijo (E e);
    E hijoDer (E e);
    E hijoIzq (E e);
    Iterator <E> hojas();
    int gradoNodo (E e);
    int gradoArbol();
    MiArbol subArbolDe(E e);
    MiArbol subArbolIz(E e);
    Iterator <E> preOrden();
    Iterator <E> inOrden();
    Iterator <E> posOrden();
    E nodoRaiz();
}
