package nextstep.courses.domain.registration;

import java.util.Arrays;

public enum SessionRecruitmentStatus {
    RECRUITING("모집중"),
    NOT_RECRUITING("비모집중");

    private final String status;

    SessionRecruitmentStatus(String status) {
        this.status = status;
    }

    public static SessionRecruitmentStatus findByName(String name) {
        return Arrays.stream(values())
                .filter(status -> status.isMatch(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 모집상태 입니다."));
    }

    private boolean isMatch(String name) {
        return this.status.equals(name);
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }

    public boolean isNotRecruiting() {
        return this == NOT_RECRUITING;
    }
}
