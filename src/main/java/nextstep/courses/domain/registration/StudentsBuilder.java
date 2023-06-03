package nextstep.courses.domain.registration;

import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class StudentsBuilder {
    public static final int DEFAULT_MAX_USER_COUNT = 30;
    private Set<NsUser> users;
    private int maxUserCount;

    private StudentsBuilder() {
        this.maxUserCount = DEFAULT_MAX_USER_COUNT;
        this.users = new HashSet<>();
    }

    public static StudentsBuilder aStudentsBuilder() {
        return new StudentsBuilder();
    }

    public StudentsBuilder withUsers(NsUser nsUser) {
        this.users.add(nsUser);
        return this;
    }

    public StudentsBuilder withMaxUserCount(int maxUserCount) {
        this.maxUserCount = maxUserCount;
        return this;
    }

    public Students build() {
        Students students = new Students(maxUserCount, users);
        return students;
    }

}
