package wg.app.model.converters;


import wg.app.model.message.MessageAdmin;

public class AdminMessageJsonConverter extends JsonConverter<MessageAdmin> {
    public AdminMessageJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
