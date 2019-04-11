package com.thoughtmechanix.routes.controllers;

import com.thoughtmechanix.routes.model.AbTestingRoute;
import com.thoughtmechanix.routes.service.SpecialRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="v1/routes/")
public class SpecialRoutesController {

    @Autowired
    SpecialRoutesService specialRoutesService;

    @RequestMapping(value= "/{serviceId}" , method = RequestMethod.GET)
    public AbTestingRoute getRoute(@PathVariable("serviceId") String serviceName){
        return specialRoutesService.getRoute(serviceName);
    }

}
