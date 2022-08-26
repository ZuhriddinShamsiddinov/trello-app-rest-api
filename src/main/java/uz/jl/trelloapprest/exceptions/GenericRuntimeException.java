package uz.jl.trelloapprest.exceptions;

import lombok.Getter;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/15:58 (Friday)
 */
@Getter
public class GenericRuntimeException extends RuntimeException {
    protected final Integer statusCode;

    public GenericRuntimeException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
