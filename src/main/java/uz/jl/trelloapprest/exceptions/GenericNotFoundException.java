package uz.jl.trelloapprest.exceptions;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/15:57 (Friday)
 */
public class GenericNotFoundException extends GenericRuntimeException {
    public GenericNotFoundException(String message, Integer statusCode) {
        super(message, statusCode);
    }
}
