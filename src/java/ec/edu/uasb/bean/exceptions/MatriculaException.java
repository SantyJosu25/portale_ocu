/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.bean.exceptions;

/**
 *
 * @author victor.barba
 */
public class MatriculaException extends Exception {

    /**
     * Creates a new instance of
     * <code>MatriculaException</code> without detail message.
     */
    public MatriculaException() {
    }

    /**
     * Constructs an instance of
     * <code>MatriculaException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MatriculaException(String msg) {
        super(msg);
    }
}
