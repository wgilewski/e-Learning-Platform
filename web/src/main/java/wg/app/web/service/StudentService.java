package wg.app.web.service;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wg.app.model.dto.StudentDto;
import wg.app.model.dto.mappers.ConverterDto;
import wg.app.model.exceptions.MyException;
import wg.app.model.user.Student;
import wg.app.persistence.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService
{

    private final StudentRepository studentRepository;
    private final ConverterDto converterDto;


    public Optional<StudentDto> getOne(Long id)
    {
        return studentRepository.findById(id).map(converterDto::fromStudentToStudentDto);
    }

    public Optional<StudentDto> update(StudentDto studentDto)
    {
        if (studentDto == null)
        {
            throw new MyException("");
        }
        
        if (!studentDto.getConfirmPassword().equals(studentDto.getPassword()))
        {
            throw new MyException("");
        }

        return Optional.of(converterDto
                .fromStudentToStudentDto(
                        studentRepository.save(converterDto.fromStudentDtoToStudent(studentDto))));
    }

    public List<StudentDto> getAll()
    {
        return studentRepository
                .findAll()
                .stream()
                .map(converterDto::fromStudentToStudentDto)
                .collect(Collectors.toList());
    }

    public void remove(Long id)
    {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent())
        {
            studentRepository.delete(student.get());
        }
        else throw new MyException("THERE IS NO STUDENT WITH SUCH ID");
    }





}
