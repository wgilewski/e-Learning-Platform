package wg.app.model.converters;


import wg.app.model.course.IndividualCourse;

public class CourseJsonConverter extends JsonConverter<IndividualCourse>
{

    public CourseJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
