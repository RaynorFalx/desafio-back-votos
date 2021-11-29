package com.southsystem.utils.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SouthSystemDefaultErro implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<SouthSystemErro> erros = new ArrayList<>();

    public List<SouthSystemErro> getErros() {
        return erros;
    }

    public void addErro(SouthSystemErro error) {
        erros.add(error);
    }
}
