package app.convertor;

import app.model.MonetaryAmount;

import javax.persistence.AttributeConverter;

public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount,String> {

    @Override
    public String convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
        return monetaryAmount.toString();
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(String dbData) {
        return MonetaryAmount.fromString(dbData);
    }
}
