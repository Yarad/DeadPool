package com.controller;

import com.DTO.*;
import com.DTO.parsers.ManParser;
import com.logic.Man;
import com.logic.Participant;
import com.services.interfaces.IManService;
import com.services.interfaces.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/man")
public class ManController {
    @Autowired
    private IManService manService;

    @Autowired
    private IParticipantService participantService;

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

    //TODO: потестить
    @CrossOrigin
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public GenericDTO<ManExtendedDTO> getManFullInfo(@PathVariable("id") long id) {
        Man man = manService.getFullManInfo(id);
        List<Participant> participants = participantService.getParticipantsByManId(id);
        return (man != null)
                ? new GenericDTO<ManExtendedDTO>(false, ManParser.parseManExtended(man, participants))
                : new GenericDTO<ManExtendedDTO>(true, null);
    }

    //TODO: потестить
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public GenericDTO<ListMenDTO> getAllManWithCrimeAmount() {
        Map<Man,Long> men = manService.getAllManWithCrimeAmount();
        List<ManForListWithCrimesAmountDTO> results = new ArrayList<>();
        men.forEach((key,value) -> {
            results.add(ManParser.parseManForList(key, value));
        });
        return new GenericDTO<ListMenDTO>(false, new ListMenDTO(results));
    }
}
