package com.thoughtmechanix.eureka.clients;

import com.thoughtmechanix.eureka.model.Company;
import com.thoughtmechanix.eureka.utils.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class CompanyRestClient {

    @Autowired
    private RestTemplate restTemplate;

    Logger logger = Logger.getLogger(CompanyRestClient.class.getName());

    public Company getCompanyRestTemplate(String companyId){
        logger.info("Rest client correlation id " + UserContextHolder.getUserContext().getCorrelationId());
        return restTemplate.exchange("http://zuul-service/api/company/v1/companys/{companyId}",
                HttpMethod.GET, null, Company.class, companyId).getBody();
    }
}
