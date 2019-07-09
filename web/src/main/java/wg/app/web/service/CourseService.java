package wg.app.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wg.app.model.course.Certificate;
import wg.app.model.course.IndividualCourse;
import wg.app.model.course.Lesson;
import wg.app.model.lecture.Lecture;
import wg.app.model.user.Student;
import wg.app.model.user.Teacher.Teacher;
import wg.app.persistence.repositories.*;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService
{
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final LectureRepository lectureRepository;



    //----------------------------------------TEACHER-------------------------------------

    public Optional<Lesson> addOrUpdateLesson(Lesson lesson, Long courseId)
    {
        Optional<IndividualCourse> individualCourse = courseRepository.findById(courseId);

        if (individualCourse.isPresent())
        {
            individualCourse.get().getLessons().add(lesson);
        }
        return Optional.of(lessonRepository.save(lesson));
    }

    public Optional<IndividualCourse> addOrUpdateCourse(IndividualCourse individualCourse, Long studentId)
    {
        Optional<Teacher> teacher = teacherRepository.findById(2l);
        Optional<Student> student = studentRepository.findById(studentId);

        if (teacher.isPresent() && student.isPresent())
        {
            individualCourse.setOwner(teacher.get());
            individualCourse.setStudent(student.get());
            individualCourse.setLessons(new HashSet<>());
        }

        return Optional.of(courseRepository.save(individualCourse));
    }

    public Optional<Lecture> addLecture(Lecture lecture)
    {
        return Optional.of(lectureRepository.save(lecture));
    }


    //---------------------------------------STUDENT---------------------------------------------

    public List<Certificate> getAllCertificatesByStudentId(Long id)
    {
        return courseRepository.findAll()
                .stream()
                .filter(course -> course.getStudent().getId().equals(id) && course.getCertificate().isGranted() == false)
                .map(course -> course.getCertificate())
                .collect(Collectors.toList());
    }

}
