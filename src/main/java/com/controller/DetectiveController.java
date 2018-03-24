package com.controller;

import com.DTO.DetectiveDTO;
import com.DTO.OperationResultDTO;
import com.security.annotations.IsDetective;
import com.services.interfaces.IDetectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/detectives")
public class DetectiveController {
    @Autowired
    private IDetectiveService detectiveService;

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public OperationResultDTO addDetective(@RequestBody DetectiveDTO detective) {
        boolean result = detectiveService.addDetective(
                detective.getMan().getId(),
                detective.getLogin(),
                detective.getPassword(),
                detective.getEmail()
        );
        return new OperationResultDTO(result);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public OperationResultDTO updateDetective(@RequestBody DetectiveDTO detective) {
        boolean result = detectiveService.updateDetective(
                detective.getMan().getId(),
                detective.getLogin(),
                detective.getPassword(),
                detective.getEmail()
        );
        return new OperationResultDTO(result);
    }
}
