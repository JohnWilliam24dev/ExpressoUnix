package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.Applications.VendaApplication;
import com.johnwilliam.ExpressoUnix.DTO.VendaDTO;
import com.johnwilliam.ExpressoUnix.DTO.VendaRequestDTO;

@Component
public class VendaFacade {
    private final VendaApplication vendaApplication;

    public VendaFacade(VendaApplication vendaApplication) {
        this.vendaApplication = vendaApplication;
    }

    public VendaDTO createVenda(VendaRequestDTO venda) {
        return vendaApplication.createVenda(venda);
    }

    public VendaDTO getVendaById(long id) {
        return vendaApplication.getVendaById(id);
    }

    public List<VendaDTO> getAllVenda() {
        return vendaApplication.getAllVenda();
    }
}
