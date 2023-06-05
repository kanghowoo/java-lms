package nextstep.courses.domain.registration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static nextstep.Fixtures.aSessionRegistrationBuilder;
import static nextstep.Fixtures.aStudentsBuilder;
import static nextstep.courses.domain.registration.SessionRegistrationMother.aSessionRegistration;
import static nextstep.courses.domain.registration.StudentMother.aStudent;
import static nextstep.courses.domain.registration.StudentMother.anotherStudent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionRegistrationTest {
    @DisplayName("강의는 강의 최대 수강 인원을 초과할 수 없다.")
    @Test
    void 수강신청_수강인원초과_불가능() {
        SessionRegistration sessionRegistration = aSessionRegistration().build();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> sessionRegistration.enroll(anotherStudent().build(), sessionRegistration.getStudents()))
                .withMessageMatching("최대 수강 인원을 초과했습니다.");
    }

    @DisplayName("중복 신청된 경우 예외처리")
    @Test
    void 수강신청_중복신청된_경우() {
        SessionRegistration sessionRegistration = aSessionRegistrationBuilder()
                .withStudents(aStudentsBuilder()
                        .withMaxUserCount(2)
                        .withStudent(aStudent().build())
                        .build())
                .build();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> sessionRegistration.enroll(aStudent().build(), sessionRegistration.getStudents()))
                .withMessageMatching("이미 등록 되었습니다.");
    }

    @DisplayName("강의 상태가 모집중이 아니면, 수강신청이 불가능")
    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "CLOSED"})
    void 수강신청_모집중아닌경우_불가능(SessionStatus sessionStatus) {
        SessionRegistration sessionRegistration = aSessionRegistrationBuilder()
                .withSessionStatus(sessionStatus)
                .build();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> sessionRegistration.enroll(anotherStudent().build(), sessionRegistration.getStudents()))
                .withMessageMatching("해당 강의는 모집중이 아닙니다.");
    }

    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"RECRUITING"})
    void 수강신청_모집중_가능(SessionStatus sessionStatus) {
        SessionRegistration sessionRegistration = aSessionRegistrationBuilder()
                .withSessionStatus(sessionStatus)
                .withStudents(aStudentsBuilder()
                        .withStudent(aStudent().build())
                        .build())
                .build();

        assertThat(sessionRegistration.getStudents()).containsOnly(aStudent().build());
    }
}
