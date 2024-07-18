@org.hibernate.annotations.GenericGenerator(
  name = "ID_GENERATOR",
  strategy = "enhanced-sequence",
  parameters = {
     @org.hibernate.annotations.Parameter(
        name = "sequence_name",
        value = "JPWHSD_SEQUENCE"
     ),
     @org.hibernate.annotations.Parameter(
        name = "initial_value",
        value = "1000"
     )
})

@org.hibernate.annotations.TypeDefs({
        @org.hibernate.annotations.TypeDef(
                name = "monetary_amount_usd",
                typeClass = MonetaryAmountUserType.class,
                parameters = {@Parameter(name = "convertTo", value = "USD")}
        ),
        @org.hibernate.annotations.TypeDef(
                name = "monetary_amount_eur",
                typeClass = MonetaryAmountUserType.class,
                parameters = {@Parameter(name = "convertTo", value = "EUR")}
        )
})
package app;

import app.convertor.MonetaryAmountUserType;
import org.hibernate.annotations.Parameter;