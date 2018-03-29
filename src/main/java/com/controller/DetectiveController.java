package com.controller;

import com.DTO.*;
import com.DTO.parsers.ManParser;
import com.logic.Man;
import com.security.annotations.IsDetective;
import com.services.interfaces.IDetectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/detectives")
public class DetectiveController {
    @Autowired
    private IDetectiveService detectiveService;

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public OperationResultDTO addDetective(@RequestBody DetectiveWithoutManIdDTO detective) {
        boolean result = detectiveService.addDetective(
                detective.getMan().getName(),
                detective.getMan().getSurname(),
                detective.getMan().getBirthday(),
                detective.getMan().getHomeAddress(),
                detective.getMan().getPhotoPath(),
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
                detective.getMan().getName(),
                detective.getMan().getSurname(),
                detective.getMan().getBirthday(),
                detective.getMan().getHomeAddress(),
                detective.getMan().getPhotoPath(),
                detective.getLogin(),
                detective.getPassword(),
                detective.getEmail()
        );
        return new OperationResultDTO(result);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public GenericDTO<ListMenShortedDTO> getAllDetectives() {
        List<Man> inputDetectives = detectiveService.getAllDetectives();
        List<ManShortedDTO> results = inputDetectives.stream()
                .map(man -> ManParser.parseManShorted(man))
                .collect(Collectors.toList());
        return new GenericDTO<>(false, new ListMenShortedDTO(results));
    }
}
