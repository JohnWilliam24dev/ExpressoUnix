package com.johnwilliam.ExpressoUnix.Configs.QrCode.DTO;

import com.johnwilliam.ExpressoUnix.DTO.AssentoDTO;
import com.johnwilliam.ExpressoUnix.DTO.PassageiroDTO;
import com.johnwilliam.ExpressoUnix.DTO.VendaDTO;
import com.johnwilliam.ExpressoUnix.DTO.ViagemDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record PassagemDto(
        @NotBlank String status,
        @NotNull Long idViagem,
        @NotNull Long idAssento,
        @NotNull Long idPassageiro,
        @NotNull LocalDate dataPassagem,
        @NotNull LocalTime horaPassagem,
        @NotBlank String origem,
        @NotBlank String destino,
        @NotNull BigDecimal distancia,
        @NotNull BigDecimal preco,
        ViagemDTO viagem, // not null apenas para teste
        AssentoDTO assento, // not null apenas para teste
        PassageiroDTO passageiro, // not null apenas para teste
        List<VendaDTO> vendas // not null apenas para teste
) {}

