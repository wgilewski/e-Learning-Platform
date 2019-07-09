package wg.app.model.converters;


import wg.app.model.user.Teacher.Opinion;

public class OpinionJsonConverter extends JsonConverter<Opinion>
{

    public OpinionJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
