package com.rest.demo.restdemo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    //URI versioning
    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Bob Jakson");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Bob","Jackson"));
    }


    //request parameter versioning
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 versionWithParam1(){
        return new PersonV1("Bob Jackson");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 versionWithParam2(){
        return new PersonV2(new Name("Bob","Jackson"));
    }



    //header type versioning
    @GetMapping(path = "/person", headers = "X-API-VERSION=1")
    public PersonV1 versionWithHeader1(){
        return new PersonV1("Bob Jackson");
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=2")
    public PersonV2 versionWithHeader2(){
        return new PersonV2(new Name("Bob","Jackson"));
    }



    //verison with produce, also called mime type versioning
    @GetMapping(path = "/person", produces = "application/vnd.company.app-v1+json")
    public PersonV1 versionWithProduce1(){
        return new PersonV1("Bob Jackson");
    }

    @GetMapping(path = "/person", produces = "application/vnd.company.app-v2+json")
    public PersonV2 versionWithProduce2(){
        return new PersonV2(new Name("Bob","Jackson"));
    }
}
