package me.rishabh.vspoc.exceptions;

/**
 * This exception occurs when the pattern of marker readings in the input file
 * changes or is wrong.
 * 
 * @author rishabh
 * 
 */
public class UnImplementedInputDataPatternException extends Exception {
    
    public UnImplementedInputDataPatternException(String msg) {
        super(msg);
    }

    private static final long serialVersionUID = -592335316155983574L;

}
