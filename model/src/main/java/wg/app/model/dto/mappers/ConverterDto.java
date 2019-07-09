package wg.app.model.dto.mappers;


import org.springframework.stereotype.Component;
import wg.app.model.dto.StudentDto;
import wg.app.model.dto.TeacherDto;
import wg.app.model.user.Student;
import wg.app.model.user.Teacher.Teacher;

import java.util.HashSet;

@Component
public class ConverterDto
{

    public Student fromStudentDtoToStudent(StudentDto studentDto)
    {
        return studentDto == null ? null :
                Student.builder()
                        .id(studentDto.getId())
                        .name(studentDto.getName())
                        .surname(studentDto.getSurname())
                        .username(studentDto.getUsername())
                        .email(studentDto.getEmail())
                        .password(studentDto.getPassword())
                        .birthDate(studentDto.getBirthDate())
                        .description(studentDto.getDescription())
                        .accountBalance(studentDto.getAccountBalance())
                        .enabled(studentDto.getEnabled())
                        .role(studentDto.getRole())
                        .filename(studentDto.getFilename())
                        .courses(new HashSet<>())
                        .multipartFile(studentDto.getMultipartFile())
                        .opinions(new HashSet<>())
                        .build();
    }

    public StudentDto fromStudentToStudentDto(Student student)
    {

        return student == null ? null :
                StudentDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .surname(student.getSurname())
                        .username(student.getUsername())
                        .email(student.getEmail())
                        //.password(student.getPassword())
                        .birthDate(student.getBirthDate())
                        .description(student.getDescription())
                        .accountBalance(student.getAccountBalance())
                        //.isActive(student.getIsActive())
                        //.role(student.getRole())
                        .filename(student.getFilename())
                        .multipartFile(student.getMultipartFile())
                        .opinions(student.getOpinions())
                        .build();
    }


    public Teacher fromTeacherDtoToTeacher(TeacherDto teacherDto)
    {
        return teacherDto == null ? null :
                Teacher.builder()
                        .id(teacherDto.getId())
                        .name(teacherDto.getName())
                        .surname(teacherDto.getSurname())
                        .username(teacherDto.getUsername())
                        .email(teacherDto.getEmail())
                        .password(teacherDto.getPassword())
                        .birthDate(teacherDto.getBirthDate())
                        .description(teacherDto.getDescription())
                        .accountBalance(teacherDto.getAccountBalance())
                        .enabled(teacherDto.getEnabled())
                        .role(teacherDto.getRole())
                        .filename(teacherDto.getFilename())
                        .multipartFile(teacherDto.getMultipartFile())
                        .advertisement(teacherDto.getAdvertisement())
                        .subjects(teacherDto.getSubjects())
                        .courses(teacherDto.getCourses())
                        .dashboard(teacherDto.getDashboard())
                        .opinions(teacherDto.getOpinions())
                        .build();
    }

    public TeacherDto fromTeacherToTeacherDto(Teacher teacher)
    {
        return teacher == null ? null :
                TeacherDto.builder()
                        .id(teacher.getId())
                        .name(teacher.getName())
                        .surname(teacher.getSurname())
                        .username(teacher.getUsername())
                        .email(teacher.getEmail())
                        .birthDate(teacher.getBirthDate())
                        .description(teacher.getDescription())
                        .accountBalance(teacher.getAccountBalance())
                        .filename(teacher.getFilename())
                        .multipartFile(teacher.getMultipartFile())
                        .advertisement(teacher.getAdvertisement())
                        .courses(teacher.getCourses())
                        .dashboard(teacher.getDashboard())
                        .opinions(teacher.getOpinions())
                        .subjects(teacher.getSubjects())
                        .build();
    }



}
