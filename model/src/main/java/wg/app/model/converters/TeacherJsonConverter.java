package wg.app.model.converters;


import wg.app.model.dto.TeacherDto;

public class TeacherJsonConverter extends JsonConverter<TeacherDto> {
    public TeacherJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
