package com.mauroave.whatsapp.mensaje;

import com.mauroave.whatsapp.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mensaje")
public class MensajeController {
    @Autowired
    private MensajeService mensajeService;

    @GetMapping("")
    public ResponseEntity get(@RequestParam("p") Integer pageNumber, @RequestParam("s") Integer size, HttpServletRequest request) {
        return ResponseEntity.ok((new ResponseBuilder()).makeSuccessResponse(request.getRequestURI(), this.mensajeService.get(pageNumber, size)));
    }

    @PostMapping({""})
    public ResponseEntity save(@RequestBody MensajeConsume consume, HttpServletRequest request) {
        return ResponseEntity.ok((new ResponseBuilder<>()).makeSuccessResponse(request.getRequestURI(), this.mensajeService.insert(consume)));
    }

}
