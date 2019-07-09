package wg.app.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wg.app.model.dto.StudentDto;
import wg.app.model.dto.TeacherDto;
import wg.app.model.dto.mappers.ConverterDto;
import wg.app.model.user.Student;
import wg.app.model.user.Teacher.Teacher;
import wg.app.persistence.repositories.StudentRepository;
import wg.app.persistence.repositories.TeacherRepository;


import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRegistrationService {


    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ConverterDto converterDto;
    private final PasswordEncoder passwordEncoder;


    public TeacherDto registerTeacher(TeacherDto teacherDto) {
        Teacher teacher = converterDto.fromTeacherDtoToTeacher(teacherDto);
        teacher.setEnabled(true);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return converterDto.fromTeacherToTeacherDto(teacherRepository.save(teacher));
    }

    public StudentDto registerStudent(StudentDto studentDto)
    {
        Student student = converterDto.fromStudentDtoToStudent(studentDto);
        student.setEnabled(true);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return converterDto.fromStudentToStudentDto(studentRepository.save(student));
    }
}
