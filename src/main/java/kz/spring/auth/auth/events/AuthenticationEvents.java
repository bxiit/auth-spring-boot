package kz.spring.auth.auth.events;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureLockedEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        System.out.println("SUCCESS");
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failure) {
        System.out.println("FAILURE");
    }

    @EventListener
    public void onLocked(AuthenticationFailureLockedEvent locked) {
        System.out.println("LOCKED");
    }
}
