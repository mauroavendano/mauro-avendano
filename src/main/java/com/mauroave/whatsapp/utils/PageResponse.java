package com.mauroave.whatsapp.utils;

import java.util.Collection;

public class PageResponse<C extends Collection> {
    private C elements;
    private Long length;

    public PageResponse() {
    }

    public C getElements() {
        return this.elements;
    }

    public void setElements(C elements) {
        this.elements = elements;
    }

    public Long getLength() {
        return this.length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
