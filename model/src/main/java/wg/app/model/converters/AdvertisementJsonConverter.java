package wg.app.model.converters;


import wg.app.model.advertisement.Advertisement;

public class AdvertisementJsonConverter extends JsonConverter<Advertisement>
{

    public AdvertisementJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
