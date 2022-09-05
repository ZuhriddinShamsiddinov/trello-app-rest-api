package uz.jl.trelloapprest.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:29 AM 9/5/22 on Monday in September
 */
@Component
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public <T> void publishCustomEvent(final GenericEvent<T> genericEvent) {
        System.out.println("Publishing custom event. ");
        if (genericEvent.success) {
            applicationEventPublisher.publishEvent(genericEvent.getWhat());
        }
    }
}
