package com.mauroave.whatsapp.contacto;

import com.mauroave.whatsapp.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactoService {
    @Autowired
    private ContactoRepository contactoRepository;

    public PageResponse<List<Contacto>> get(Integer pageNumber, Integer size){
        PageResponse<List<Contacto>> page = new PageResponse();
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<Contacto> result = new ArrayList<Contacto>();
        this.contactoRepository.findAll(pageable).iterator().forEachRemaining(result::add);
        page.setElements(result);
        page.setLength(this.contactoRepository.count());
        return page;
    }

}
