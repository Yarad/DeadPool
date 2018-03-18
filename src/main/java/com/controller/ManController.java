package com.controller;

import com.services.interfaces.IManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/man")
public class ManController {
    @Autowired
    private IManService manService;


}
