package com.mauroave.whatsapp.persona;

import com.mauroave.whatsapp.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;
    @Autowired
    protected Validator validator;

    @GetMapping("")
    public ResponseEntity get(@RequestParam("p") Integer pageNumber, @RequestParam("s") Integer size, HttpServletRequest request) {
        return ResponseEntity.ok((new ResponseBuilder()).makeSuccessResponse(request.getRequestURI(), this.personaService.get(pageNumber, size)));
    }

    @PostMapping({""})
    public ResponseEntity save(@RequestBody Persona consume, HttpServletRequest request) {

        return ResponseEntity.ok((new ResponseBuilder<>()).makeSuccessResponse(request.getRequestURI(), this.personaService.insert(consume)));
    }
}
