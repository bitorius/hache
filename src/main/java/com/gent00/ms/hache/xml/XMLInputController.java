package com.gent00.ms.hache.xml;

import com.gent00.ms.hache.dto.XMLInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class XMLInputController {


    @Autowired XMLInputRepository xmlInputRepository;

    @PostMapping(value = "/xmldsig", produces = MediaType.APPLICATION_JSON_VALUE)

    public EntityModel<XMLInput> canonicalize(
            @RequestParam(value = "b64xml", defaultValue = "") XMLInput xmlInput
    ) {
        XMLInput xmlInput1 = xmlInputRepository.save(xmlInput);
        return EntityModel.of(xmlInput, linkTo(methodOn(XMLInputController.class).getXMLInputById(xmlInput1.getId())).withSelfRel());
    }

    @GetMapping("/xmlinput/{id}")
    public ResponseEntity<XMLInput> getXMLInputById(@PathVariable("id") long id) {
        Optional<XMLInput> xmlInputData = xmlInputRepository.findById(id);
        if (xmlInputData.isPresent()) {
            return new ResponseEntity<>(xmlInputData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
