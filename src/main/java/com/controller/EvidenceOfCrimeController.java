package com.controller;

import com.DTO.*;
import com.DTO.parsers.EvidenceOfCrimeParser;
import com.DTO.parsers.EvidenceParser;
import com.logic.Evidence;
import com.logic.EvidenceOfCrime;
import com.security.annotations.IsDetective;
import com.services.interfaces.IEvidenceOfCrimeService;
import com.services.interfaces.IEvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/evidences")
public class EvidenceOfCrimeController {
    @Autowired
    private IEvidenceOfCrimeService evidenceOfCrimeService;

    @Autowired
    private IEvidenceService evidenceService;

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/add_single", method = RequestMethod.POST)
    public OperationResultAddDTO addEvidenceSingle(@RequestBody EvidenceInputDTO evidence) {
        AddResult result = evidenceService.addEvidence(
                evidence.getName(),
                evidence.getDescription()
        );
        return new OperationResultAddDTO(result);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/update_single", method = RequestMethod.POST)
    public OperationResultDTO updateEvidenceSingle(@RequestBody EvidenceInputWithIdDTO evidence) {
        boolean result = evidenceService.updateEvidence(
                evidence.getId(),
                evidence.getName(),
                evidence.getDescription()
        );
        return new OperationResultDTO(result);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/add_for_crime", method = RequestMethod.POST)
    public OperationResultDTO addEvidenceForCrime(@RequestBody EvidenceOfCrimeInputDTO evidenceOfCrime) {
        boolean result = evidenceOfCrimeService.addEvidenceOfCrime(
                evidenceOfCrime.getEvidence().getId(),
                evidenceOfCrime.getCrime().getId(),
                evidenceOfCrime.getType(),
                evidenceOfCrime.getDateAdded(),
                evidenceOfCrime.getTimeAdded(),
                evidenceOfCrime.getDetails(),
                evidenceOfCrime.getPhotoPath()
        );
        return new OperationResultDTO(result);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/update_for_crime", method = RequestMethod.POST)
    public OperationResultDTO updateEvidenceForCrime(@RequestBody EvidenceOfCrimeInputDTO evidenceOfCrime) {
        boolean result = evidenceOfCrimeService.updateEvidenceOfCrime(
                evidenceOfCrime.getEvidence().getId(),
                evidenceOfCrime.getCrime().getId(),
                evidenceOfCrime.getType(),
                evidenceOfCrime.getDateAdded(),
                evidenceOfCrime.getTimeAdded(),
                evidenceOfCrime.getDetails(),
                evidenceOfCrime.getPhotoPath()
        );
        return new OperationResultDTO(result);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/all_singles", method = RequestMethod.GET)
    public GenericDTO<ListEvidencesDTO> getAllEvidences() {
        List<Evidence> inputEvidences = evidenceService.getAllEvidences();
        List<EvidenceObjectDTO> results = inputEvidences.stream()
                .map(curEvidence -> EvidenceParser.parseEvidence(curEvidence))
                .collect(Collectors.toList());
        return new GenericDTO<>(false, new ListEvidencesDTO(results));
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public GenericDTO<ListEvidenceOfCrimeShortedWithCrimeList> getAllEvidenceOfCrime() {
        List<EvidenceOfCrime> inputEvidencesOfCrime = evidenceOfCrimeService.getAllEvidencesOfCrime();
        List<EvidenceOfCrimeShortedWithCrimeDTO> results = inputEvidencesOfCrime.stream()
                .map(curEvidence -> EvidenceOfCrimeParser.parseEvidenceOfCrimeShortedWithCrime(curEvidence))
                .collect(Collectors.toList());
        return new GenericDTO<>(false, new ListEvidenceOfCrimeShortedWithCrimeList(results));
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/{evidence_id}/{crime_id}", method = RequestMethod.GET)
    public GenericDTO<EvidenceOfCrimeExtendedDTO> getEvidenceOfCrimeByEvidenceAndCrime(
            @PathVariable("evidence_id") long evidenceId,
            @PathVariable("crime_id") long crimeId
    ) {
        EvidenceOfCrime evidenceOfCrime = evidenceOfCrimeService.getEvidenceOfCrimeByEvidenceAndCrime(evidenceId, crimeId);
        return (evidenceOfCrime != null)
                ? new GenericDTO<>(false, EvidenceOfCrimeParser.parseEvidenceOfCrimeExtended(evidenceOfCrime))
                : new GenericDTO<>(true, null);
    }

    //TODO: потестировать, когда будет реализован метод получения списка EvidenceOfCrime
    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/{evidence_id}", method = RequestMethod.GET)
    public GenericDTO<EvidenceExtendedDTO> getEvidenceById(@PathVariable("evidence_id") long id) {
        Evidence evidence = evidenceService.getEvidenceById(id);
        List<EvidenceOfCrime> evidencesOfCrime = evidenceOfCrimeService.getEvidencesOfCrimeByEvidenceId(id);
        return (evidence != null)
                ? new GenericDTO<>(false, EvidenceParser.parseEvidenceExtended(evidence, evidencesOfCrime))
                : new GenericDTO<>(true, null);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/types_list", method = RequestMethod.GET)
    public ListEnumDTO getParticipantStatuses() {
        return EvidenceOfCrimeParser.getEvidenceTypes();
    }
}
