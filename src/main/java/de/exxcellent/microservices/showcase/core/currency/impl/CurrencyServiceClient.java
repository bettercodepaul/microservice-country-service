package de.exxcellent.microservices.showcase.core.currency.impl;

import de.exxcellent.microservices.showcase.core.currency.api.CurrencyESI;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * A client to communicate with the currency service application. Implementation of {@link CurrencyESI}.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public class CurrencyServiceClient implements CurrencyESI {

    @ConfigProperty(name = "currency.service.base.url")
    private String currencyServiceBaseUrl;
}
