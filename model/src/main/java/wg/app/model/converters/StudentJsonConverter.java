package wg.app.model.converters;


import wg.app.model.dto.StudentDto;

public class StudentJsonConverter extends JsonConverter<StudentDto>
{
    public StudentJsonConverter(String jsonFileName)
    {
        super(jsonFileName);
    }
}
