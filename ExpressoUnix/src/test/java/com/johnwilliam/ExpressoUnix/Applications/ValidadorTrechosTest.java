package com.johnwilliam.ExpressoUnix.Applications;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.johnwilliam.ExpressoUnix.DTO.ItemVendaDTO;
import com.johnwilliam.ExpressoUnix.Enums.TipoTrecho;
import com.johnwilliam.ExpressoUnix.Exceptions.BusinessException;
import com.johnwilliam.ExpressoUnix.Models.ViagemModels;

class ValidadorTrechosTest {

    private final ValidadorTrechos validador = new ValidadorTrechos(true);

    private ItemVendaDTO item(TipoTrecho tipo, long idViagem, long idAssento, Integer vinculada) {
        ItemVendaDTO item = new ItemVendaDTO();
        item.setTipoTrecho(tipo);
        item.setIdViagem(idViagem);
        item.setIdAssento(idAssento);
        item.setIdPassageiro(1);
        item.setVinculadaAoItem(vinculada);
        return item;
    }

    private ViagemModels viagem(String origem, String destino, LocalDate data, LocalTime hora) {
        ViagemModels viagem = new ViagemModels();
        viagem.setOrigem(origem);
        viagem.setDestino(destino);
        viagem.setDataViagem(data);
        viagem.setHoraViagem(hora);
        return viagem;
    }

    @Test
    void passagemAvulsaSemVinculoEValida() {
        List<ItemVendaDTO> itens = List.of(item(TipoTrecho.AVULSA, 1, 10, null));
        assertDoesNotThrow(() -> validador.validarEstrutura(itens));
    }

    @Test
    void idaEVoltaVinculadasSaoValidas() {
        List<ItemVendaDTO> itens = List.of(
                item(TipoTrecho.IDA, 1, 10, null),
                item(TipoTrecho.VOLTA, 2, 20, 0));
        assertDoesNotThrow(() -> validador.validarEstrutura(itens));
    }

    @Test
    void voltaPodeVirAntesDaIdaNaLista() {
        List<ItemVendaDTO> itens = List.of(
                item(TipoTrecho.VOLTA, 2, 20, 1),
                item(TipoTrecho.IDA, 1, 10, null));
        assertDoesNotThrow(() -> validador.validarEstrutura(itens));
    }

    @Test
    void voltaSemVinculoEhRejeitada() {
        List<ItemVendaDTO> itens = List.of(item(TipoTrecho.VOLTA, 2, 20, null));
        assertThrows(BusinessException.class, () -> validador.validarEstrutura(itens));
    }

    @Test
    void voltaApontandoParaAvulsaEhRejeitada() {
        List<ItemVendaDTO> itens = List.of(
                item(TipoTrecho.AVULSA, 1, 10, null),
                item(TipoTrecho.VOLTA, 2, 20, 0));
        assertThrows(BusinessException.class, () -> validador.validarEstrutura(itens));
    }

    @Test
    void idaSemVoltaEhRejeitada() {
        List<ItemVendaDTO> itens = List.of(item(TipoTrecho.IDA, 1, 10, null));
        assertThrows(BusinessException.class, () -> validador.validarEstrutura(itens));
    }

    @Test
    void idaComDuasVoltasEhRejeitada() {
        List<ItemVendaDTO> itens = List.of(
                item(TipoTrecho.IDA, 1, 10, null),
                item(TipoTrecho.VOLTA, 2, 20, 0),
                item(TipoTrecho.VOLTA, 3, 30, 0));
        assertThrows(BusinessException.class, () -> validador.validarEstrutura(itens));
    }

    @Test
    void vinculoEmItemNaoVoltaEhRejeitado() {
        List<ItemVendaDTO> itens = List.of(item(TipoTrecho.AVULSA, 1, 10, 0));
        assertThrows(BusinessException.class, () -> validador.validarEstrutura(itens));
    }

    @Test
    void mesmoAssentoDuasVezesEhRejeitado() {
        List<ItemVendaDTO> itens = List.of(
                item(TipoTrecho.AVULSA, 1, 10, null),
                item(TipoTrecho.AVULSA, 1, 10, null));
        assertThrows(BusinessException.class, () -> validador.validarEstrutura(itens));
    }

    @Test
    void voltaPosteriorSaindoDoDestinoDaIdaEhCoerente() {
        List<ItemVendaDTO> itens = List.of(
                item(TipoTrecho.IDA, 1, 10, null),
                item(TipoTrecho.VOLTA, 2, 20, 0));
        List<ViagemModels> viagens = List.of(
                viagem("Feira de Santana", "Salvador", LocalDate.of(2026, 10, 15), LocalTime.of(8, 0)),
                viagem("salvador", "Feira de Santana", LocalDate.of(2026, 10, 18), LocalTime.of(17, 0)));
        assertDoesNotThrow(() -> validador.validarCoerencia(itens, viagens));
    }

    @Test
    void voltaAnteriorAIdaEhRejeitada() {
        List<ItemVendaDTO> itens = List.of(
                item(TipoTrecho.IDA, 1, 10, null),
                item(TipoTrecho.VOLTA, 2, 20, 0));
        List<ViagemModels> viagens = List.of(
                viagem("Feira de Santana", "Salvador", LocalDate.of(2026, 10, 15), LocalTime.of(8, 0)),
                viagem("Salvador", "Feira de Santana", LocalDate.of(2026, 10, 15), LocalTime.of(7, 0)));
        assertThrows(BusinessException.class, () -> validador.validarCoerencia(itens, viagens));
    }

    @Test
    void voltaSaindoDeOutraCidadeEhRejeitadaQuandoRegraAtiva() {
        List<ItemVendaDTO> itens = List.of(
                item(TipoTrecho.IDA, 1, 10, null),
                item(TipoTrecho.VOLTA, 2, 20, 0));
        List<ViagemModels> viagens = List.of(
                viagem("Feira de Santana", "Salvador", LocalDate.of(2026, 10, 15), LocalTime.of(8, 0)),
                viagem("Camacari", "Feira de Santana", LocalDate.of(2026, 10, 18), LocalTime.of(17, 0)));
        assertThrows(BusinessException.class, () -> validador.validarCoerencia(itens, viagens));
    }

    @Test
    void voltaSaindoDeOutraCidadeEhAceitaQuandoRegraDesativada() {
        ValidadorTrechos flexivel = new ValidadorTrechos(false);
        List<ItemVendaDTO> itens = List.of(
                item(TipoTrecho.IDA, 1, 10, null),
                item(TipoTrecho.VOLTA, 2, 20, 0));
        List<ViagemModels> viagens = List.of(
                viagem("Feira de Santana", "Salvador", LocalDate.of(2026, 10, 15), LocalTime.of(8, 0)),
                viagem("Camacari", "Feira de Santana", LocalDate.of(2026, 10, 18), LocalTime.of(17, 0)));
        assertDoesNotThrow(() -> flexivel.validarCoerencia(itens, viagens));
    }
}
