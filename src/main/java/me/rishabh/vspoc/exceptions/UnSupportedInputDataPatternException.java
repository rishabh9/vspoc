package me.rishabh.vspoc.exceptions;

/**
 * This exception occurs when the pattern of marker readings in the input file
 * changes or is wrong.
 * 
 * @author rishabh
 * 
 */
public class UnSupportedInputDataPatternException extends Exception {

    public UnSupportedInputDataPatternException(String msg) {
        super(msg);
    }

    public UnSupportedInputDataPatternException(String msg, Throwable cause) {
        super(msg, cause);
    }

    private static final long serialVersionUID = -592335316155983574L;

}
