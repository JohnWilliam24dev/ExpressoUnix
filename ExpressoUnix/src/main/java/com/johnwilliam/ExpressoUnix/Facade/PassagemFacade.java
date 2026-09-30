package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.Applications.PassagemApplication;
import com.johnwilliam.ExpressoUnix.DTO.PassagemDTO;

@Component
public class PassagemFacade {
    private final PassagemApplication passagemApplication;

    public PassagemFacade(PassagemApplication passagemApplication) {
        this.passagemApplication = passagemApplication;
    }

    public PassagemDTO getPassagemById(long id) {
        return passagemApplication.getPassagemById(id);
    }

    public List<PassagemDTO> getAllPassagem() {
        return passagemApplication.getAllPassagem();
    }
}
