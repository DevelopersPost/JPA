package app.convertor;

import app.model.GermanZipcode;
import app.model.SwissZipcode;
import app.model.Zipcode;

import javax.persistence.AttributeConverter;

public class ZipcodeConverter implements AttributeConverter<Zipcode,String> {
    @Override
    public String convertToDatabaseColumn(Zipcode attribute) {
        return attribute.getValue();
    }

    @Override
    public Zipcode convertToEntityAttribute(String dbData) {
        if (dbData.length() == 5){
            return new GermanZipcode(dbData);
        } else if (dbData.length() == 4) {
            return new SwissZipcode(dbData);
        }else {
            throw new IllegalArgumentException("unsupported zip code in DB"+dbData);
        }
    }
}
