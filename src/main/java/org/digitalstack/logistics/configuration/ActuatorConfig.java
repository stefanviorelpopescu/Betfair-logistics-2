package org.digitalstack.logistics.configuration;

import org.digitalstack.logistics.service.CompanyInformationService;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActuatorConfig  implements InfoContributor {

    private final CompanyInformationService companyInformationService;

    public ActuatorConfig(CompanyInformationService companyInformationService) {
        this.companyInformationService = companyInformationService;
    }

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> companyDetails = new HashMap<>();
        companyDetails.put("currentDate", companyInformationService.getCurrentDate().toString());
        companyDetails.put("profit", String.valueOf(companyInformationService.getProfit()));
        builder.withDetail("companyDetails", companyDetails);
    }
}

