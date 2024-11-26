/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author abelc
 */
public class PatolliServerException extends Exception{

    public PatolliServerException() {
    }

    public PatolliServerException(String message) {
        super(message);
    }

    public PatolliServerException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
