package uz.jl.trelloapprest.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:29 AM 9/5/22 on Monday in September
 */
@Getter
@Setter
public class GenericEvent<T> extends ApplicationEvent {

    private T what;
    protected boolean success;

    public GenericEvent(T what, boolean success){
        super(what);
        this.what = what;
        this.success = success;
    }

}
