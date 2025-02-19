package nextstep.courses.domain;

import nextstep.courses.domain.registration.SessionRegistration;
import nextstep.courses.domain.registration.SessionStatus;

public class SessionBuilder {
    private Long id;
    private SessionStatus sessionStatus;
    private SessionRegistration sessionRegistration;
    private SessionPeriod sessionPeriod;
    private String sessionCoverImage;
    private SessionCostType sessionCostType;

    private SessionBuilder() {
    }

    public static SessionBuilder aSession() {
        return new SessionBuilder();
    }

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        return this;
    }

    public SessionBuilder withSessionRegistration(SessionRegistration sessionRegistration) {
        this.sessionRegistration = sessionRegistration;
        return this;
    }

    public SessionBuilder withSessionPeriod(SessionPeriod sessionPeriod) {
        this.sessionPeriod = sessionPeriod;
        return this;
    }

    public SessionBuilder withSessionCoverImage(String sessionCoverImage) {
        this.sessionCoverImage = sessionCoverImage;
        return this;
    }

    public SessionBuilder withSessionCostType(SessionCostType sessionCostType) {
        this.sessionCostType = sessionCostType;
        return this;
    }

    public Session build() {
        return new Session(id, sessionStatus, sessionRegistration, sessionPeriod, sessionCoverImage, sessionCostType);
    }




}
