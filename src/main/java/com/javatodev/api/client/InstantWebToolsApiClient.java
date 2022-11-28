package com.javatodev.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${app.feign.config.name}", url = "${app.feign.config.url}")
public interface InstantWebToolsApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/airlines/{airlineId}")
    String readAirLineById(@PathVariable String airlineId);

}