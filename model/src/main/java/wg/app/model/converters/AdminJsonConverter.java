package wg.app.model.converters;


import wg.app.model.user.Admin;

public class AdminJsonConverter extends JsonConverter<Admin> {
    public AdminJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
