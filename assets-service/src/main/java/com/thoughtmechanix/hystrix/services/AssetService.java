package com.thoughtmechanix.hystrix.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.thoughtmechanix.hystrix.clients.CompanyDiscoveryClient;
import com.thoughtmechanix.hystrix.clients.CompanyFeignClient;
import com.thoughtmechanix.hystrix.clients.CompanyRestClient;
import com.thoughtmechanix.hystrix.model.Asset;
import com.thoughtmechanix.hystrix.model.Company;
import com.thoughtmechanix.hystrix.repository.AssetRepository;
import com.thoughtmechanix.hystrix.utils.UserContextHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    CompanyDiscoveryClient companyDiscoveryClient;

    @Autowired
    CompanyRestClient companyRestClient;

    @Autowired
    CompanyFeignClient companyFeignClient;

    private static final Logger logger = Logger.getLogger(AssetService.class.getName());

//    public List<Asset> getAssetsByCompany(String companyId){
//        return assetRepository.findAllByCompanyId(companyId);
//    }

    public void randomIntervalSleep(){
        Random rand = new Random();
        int randNum = rand.nextInt(3) + 1;

        if(randNum == 2){
            sleep();
        }
    }

    private void sleep() {
        try{
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @HystrixCommand
//    public List<Asset> getAssetsByCompany(String companyId){
//        logger.info("Waiting 11 seconds Correlation Id " + UserContextHolder.getUserContext().getCorrelationId());
//        randomIntervalSleep();
//        return assetRepository.findAllByCompanyId(companyId);
//    }


    @HystrixCommand(
            commandProperties=
                    {@HystrixProperty(
                            name="execution.isolation.thread.timeoutInMilliseconds",
                            value="12000")})
    public List<Asset> getAssetsByCompany(String companyId){
        logger.info("Waiting 11 seconds Correlation Id " + UserContextHolder.getUserContext().getCorrelationId());
        randomIntervalSleep();
        return assetRepository.findAllByCompanyId(companyId);
    }


    public Asset getAsset(String companyId, String assetId, String clientType){
        Asset asset = assetRepository.findByCompanyIdAndAssetId(companyId, assetId);
        Company company = retrieveCompany(companyId, clientType);

        return asset
                .withCompanyName(company.getCompanyName())
                .withNumOfEmployees(company.getNumOfEmployees())
                .withYearEstablished(company.getYearEstablished())
                .withCapital(company.getCapital());
    }

    public Asset createAsset(Asset asset){

        return assetRepository.save(asset);
    }

    public Asset updateAsset(Asset assetOld, Asset assetNew){

        BeanUtils.copyProperties(assetNew, assetOld);

        return assetRepository.save(assetOld);
    }

    public void  deleteAsset(Asset asset){
        assetRepository.delete(asset);
    }

    public Company retrieveCompany(String companyId, String clientType){
         Company company = null;

         switch (clientType){
             case "feign":
                 logger.info("Feign client");
                 company = companyFeignClient.getCompany(companyId);
                 break;
             case "rest":
                 logger.info("Rest Client");
                 company = companyRestClient.getCompanyRestTemplate(companyId);
                 break;
             case "discovery":
                 logger.info("Discovery client");
                 company = companyDiscoveryClient.getCompanyDiscovery(companyId);
             default:
                 logger.info("Default client");
                 company = companyRestClient.getCompanyRestTemplate(companyId);
         }

         return company;
    }
}