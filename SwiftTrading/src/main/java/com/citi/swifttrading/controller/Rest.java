package com.citi.swifttrading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.swifttrading.dao.SecurityDAO;
import com.citi.swifttrading.domain.Security;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/rest/sec")
public class Rest {

	@Autowired
    SecurityDAO dao;
   
    @RequestMapping(value="{id}",method=RequestMethod.GET)
    public Security hello(@PathVariable("id") int id){
        log.debug("hello world");
        return dao.get(id);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String save(@RequestBody(required=true)Security s){
        log.debug("save");
    	dao.save(s);
        return "saved";
    }
    
   
}