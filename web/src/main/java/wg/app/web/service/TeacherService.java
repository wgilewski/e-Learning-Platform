package wg.app.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wg.app.model.dto.TeacherDto;
import wg.app.model.dto.mappers.ConverterDto;
import wg.app.model.user.Teacher.Teacher;
import wg.app.persistence.repositories.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService
{
    private final TeacherRepository teacherRepository;
    private final ConverterDto converterDto;



    public Optional<TeacherDto> getOne(Long id)
    {
        return teacherRepository.findById(id).map(converterDto::fromTeacherToTeacherDto);
    }

    public Optional<TeacherDto> update(TeacherDto teacherDto)
    {
        return Optional.of(converterDto
                .fromTeacherToTeacherDto(
                        teacherRepository.save(converterDto.fromTeacherDtoToTeacher(teacherDto))));
    }

    public List<TeacherDto> getAllTeachers()
    {
        return teacherRepository
                .findAll()
                .stream()
                .map(converterDto::fromTeacherToTeacherDto)
                .collect(Collectors.toList());
    }

    public void removeTeacher(Long id)
    {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(NullPointerException::new);
        teacherRepository.delete(teacher);
    }








}
