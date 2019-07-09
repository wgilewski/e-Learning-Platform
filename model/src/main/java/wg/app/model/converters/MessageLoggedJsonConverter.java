package wg.app.model.converters;


import wg.app.model.message.MessageLogged;

public class MessageLoggedJsonConverter extends JsonConverter<MessageLogged> {
    public MessageLoggedJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
