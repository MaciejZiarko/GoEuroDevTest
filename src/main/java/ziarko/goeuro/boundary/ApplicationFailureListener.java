package ziarko.goeuro.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;

public class ApplicationFailureListener implements ApplicationListener {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationFailureListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (hasApplicationFailed(event)) {
            handleApplicationFailure(event);
        }
    }

    private void handleApplicationFailure(ApplicationEvent event) {
        ApplicationFailedEvent applicationFailedEvent = (ApplicationFailedEvent) event;
        Throwable rootCause = getRootCause(applicationFailedEvent.getException());
        LOG.error("{} occurred", rootCause.getClass().getName());
        LOG.error(rootCause.getMessage());
        System.exit(-1);
    }

    private boolean hasApplicationFailed(ApplicationEvent event) {
        return event instanceof ApplicationFailedEvent;
    }
}
