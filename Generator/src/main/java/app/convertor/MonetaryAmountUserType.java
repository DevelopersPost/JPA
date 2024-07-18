package app.convertor;

import app.model.MonetaryAmount;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.DynamicParameterizedType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Properties;

public class MonetaryAmountUserType implements CompositeUserType, DynamicParameterizedType {

    private Currency convertTo;

    @Override
    public String[] getPropertyNames() {
        return new String[]{"value", "currency"};
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{
                StandardBasicTypes.BIG_DECIMAL,
                StandardBasicTypes.CURRENCY
        };
    }

    @Override
    public Object getPropertyValue(Object o, int i) throws HibernateException {
        MonetaryAmount monetaryAmount = (MonetaryAmount) o;
        if (i == 0)
            return monetaryAmount.getValue();
        else
            return monetaryAmount.getCurrency();
    }

    @Override
    public void setPropertyValue(Object o, int i, Object o1) throws HibernateException {
        throw new UnsupportedOperationException("MonetaryAmount is immutable");
    }

    @Override
    public Class returnedClass() {
        return MonetaryAmount.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y || !(x == null || y == null) && x.equals(y);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        BigDecimal amount = resultSet.getBigDecimal(strings[0]);
        if (resultSet.wasNull()) {
            return null;
        }

        Currency currency = Currency.getInstance(resultSet.getString(strings[1]));
        return new MonetaryAmount(amount,currency);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (o == null){
            preparedStatement.setNull(i, StandardBasicTypes.BIG_DECIMAL.sqlType());
            preparedStatement.setNull(i + 1,StandardBasicTypes.CURRENCY.sqlType());
        }else {
            MonetaryAmount monetaryAmount = (MonetaryAmount) o;
            MonetaryAmount dbAmount = convert(monetaryAmount, convertTo);
            preparedStatement.setBigDecimal(i,dbAmount.getValue());
            preparedStatement.setString(i+1,convertTo.getCurrencyCode());
        }
    }

    public MonetaryAmount convert(MonetaryAmount amount,Currency toCurrency) {
        return new MonetaryAmount(amount.getValue().multiply(new BigDecimal(2)),toCurrency);
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
        return o.toString();
    }

    @Override
    public Object assemble(Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return MonetaryAmount.fromString((String) serializable);
    }

    @Override
    public Object replace(Object x, Object y, SharedSessionContractImplementor sharedSessionContractImplementor, Object o2) throws HibernateException {
        return  x;
    }

    @Override
    public void setParameterValues(Properties properties) {
        String convertToParameter = properties.getProperty("convertTo");
        this.convertTo = Currency.getInstance(convertToParameter != null ? convertToParameter : "USD");
    }
}
