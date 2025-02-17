package nextstep.courses.domain.registration;

import java.util.List;

public interface StudentRepository {
    void save(Student student);
    List<Student> findAllBySessionId(Long sessionId);
    Student findByUserId(Long nsUserId);
    void updateStatus(Student student);
}
