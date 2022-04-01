package com.mauroave.whatsapp.persona;

import com.challenge.new_test.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/usuario")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    /*@PostMapping({"/login"})
    public ResponseEntity login(@RequestParam("p") Integer pageNumber, @RequestParam("s") Integer size, HttpServletRequest request) {
        return ResponseEntity.ok((new ResponseBuilder()).makeSuccessResponse(request.getRequestURI(), this.logService.getLogs(pageNumber, size)));
    }*/

    @PostMapping({"/logout"})
    public ResponseEntity logout(HttpServletRequest request, SecurityContextHolder authentication) {
        return ResponseEntity.ok((new ResponseBuilder()).makeSuccessResponse(request.getRequestURI(), this.personaService.logout(request, SecurityContextHolder.getContext().getAuthentication())));
    }

    @PostMapping({"/signup"})
    public ResponseEntity signup(Persona user, HttpServletRequest request, SecurityContextHolder authentication) {
        return ResponseEntity.ok((new ResponseBuilder()).makeSuccessResponse(request.getRequestURI(), this.personaService.signup(user, request, SecurityContextHolder.getContext().getAuthentication())));
    }
}
