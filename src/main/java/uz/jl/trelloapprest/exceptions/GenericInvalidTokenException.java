package uz.jl.trelloapprest.exceptions;

import lombok.Getter;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/15:21 (Friday)
 */

@Getter
public class GenericInvalidTokenException extends GenericNotFoundException {
    public GenericInvalidTokenException(String message, Integer statusCode) {
        super(message, statusCode);
    }
}
