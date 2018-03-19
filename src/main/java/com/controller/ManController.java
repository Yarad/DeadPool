package com.controller;

import com.DTO.ManInfoDTO;
import com.DTO.ManInfoWithoutIdDTO;
import com.DTO.OperationResultDTO;
import com.services.interfaces.IManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/man")
public class ManController {
    @Autowired
    private IManService manService;

    @CrossOrigin
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public OperationResultDTO addMan(@RequestBody ManInfoWithoutIdDTO man) {
        boolean result = manService.addMan(
                man.getName(),
                man.getSurname(),
                man.getBirthday(),
                man.getHomeAddress(),
                man.getPhotoPath()
        );
        return new OperationResultDTO(result);
    }

    @CrossOrigin
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public OperationResultDTO updateMan(@RequestBody ManInfoDTO man) {
        boolean result = manService.updateMan(
                man.getId(),
                man.getName(),
                man.getSurname(),
                man.getBirthday(),
                man.getHomeAddress(),
                man.getPhotoPath()
        );
        return new OperationResultDTO(result);
    }
}
