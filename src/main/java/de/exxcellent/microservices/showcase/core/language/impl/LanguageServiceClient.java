package de.exxcellent.microservices.showcase.core.language.impl;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.language.api.LanguageESI;
import de.exxcellent.microservices.showcase.core.language.api.types.CountryWithLanguageCTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A client to communicate with language service. Implements {@link LanguageESI}.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 22.01.2020
 */
@ApplicationScoped
public class LanguageServiceClient implements LanguageESI {
    /**
     * The {@link Logger} of this {@link LanguageServiceClient}.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LanguageServiceClient.class);

    @Inject
    @RestClient
    private LanguageServiceInterface languageService;

    @Override
    public Set<CountryWithLanguageCTO> getCountriesWithLanguage() {
        LOG.info("Calling language service to get all countries with their language");
        final Response response = this.languageService.getCountriesWithLanguage();
        if(response.getStatus() == 200) {
            return new HashSet<>(Arrays.asList(response.readEntity(CountryWithLanguageCTO[].class)));
        } else if(response.getStatus() == 204) {
            return Collections.emptySet();
        } else {
            // 4xx and 5xx responses are forwarded by the framework, so we should never run in this else block.
            throw new TechnicalException(ErrorCode.INTERNAL_ERROR, "Application reached an unstable state at: LanguageServiceClient.GetCountriesWithLanguage. Please contact 3rd level support");
        }
    }

    @Override
    public CountryWithLanguageCTO getCountryWithLanguage(final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        LOG.info("Calling language service to get language of country with short name {}", countryShortName);
        final Response response = this.languageService.getCountryWithLanguage(countryShortName);
        if(response.getStatus() == 200) {
            return response.readEntity(CountryWithLanguageCTO.class);
        } else {
            // 4xx and 5xx responses are forwarded by the framework, so we should never run in this else block.
            throw new TechnicalException(ErrorCode.INTERNAL_ERROR, "Application reached an unstable state at: LanguageServiceClient.GetCountryWithLanguage. Please contact 3rd level support");
        }
    }

    @Override
    public Set<CountryWithLanguageCTO> createCountryWithLanguage(final CountryWithLanguageCTO countryWithLanguage) {
        Preconditions.checkNotNull(countryWithLanguage, "Country with language must not be null");
        Preconditions.checkNotNull(countryWithLanguage.getCountryShortName(), "Country short name must not be null");
        Preconditions.checkStringLength(countryWithLanguage.getCountryShortName(), 3, "Country short name must have 3 characters");
        Preconditions.checkNotNull(countryWithLanguage.getLanguage(), "Language must not be null");
        Preconditions.checkNotNull(countryWithLanguage.getLanguage().getShortName(), "Language short name must not be null");
        Preconditions.checkNotNull(countryWithLanguage.getLanguage().getName(), "Language name must not be null");
        Preconditions.checkStringLength(countryWithLanguage.getLanguage().getShortName(), 3, "Language short name must have 3 characters");
        LOG.info("Calling language service to create mapping of country {} and language {}", countryWithLanguage.getCountryShortName(), countryWithLanguage.getLanguage().getName());
        final Response response = this.languageService.createCountryWithLanguage(countryWithLanguage);
        if(response.getStatus() == 200) {
            return new HashSet<>(Arrays.asList(response.readEntity(CountryWithLanguageCTO[].class)));
        } else {
            // 4xx and 5xx responses are forwarded by the framework, so we should never run in this else block.
            throw new TechnicalException(ErrorCode.INTERNAL_ERROR, "Application reached an unstable state at: LanguageServiceClient.CreateCountryWithLanguage. Please contact 3rd level support");
        }
    }
}
