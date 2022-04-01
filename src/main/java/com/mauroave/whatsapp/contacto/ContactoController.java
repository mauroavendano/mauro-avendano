package com.mauroave.whatsapp.contacto;

import com.mauroave.whatsapp.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/contacto")
public class ContactoController {
    @Autowired
    private ContactoService contactoService;

    @GetMapping("")
    public ResponseEntity get(@RequestParam("p") Integer pageNumber, @RequestParam("s") Integer size, HttpServletRequest request) {
        return ResponseEntity.ok((new ResponseBuilder()).makeSuccessResponse(request.getRequestURI(), this.contactoService.get(pageNumber, size)));
    }

}
