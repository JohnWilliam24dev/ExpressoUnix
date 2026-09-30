package com.johnwilliam.ExpressoUnix.Applications;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.ItemVendaDTO;
import com.johnwilliam.ExpressoUnix.Enums.TipoTrecho;
import com.johnwilliam.ExpressoUnix.Exceptions.BusinessException;
import com.johnwilliam.ExpressoUnix.Models.ViagemModels;

/**
 * Regras de ida e volta dentro de uma venda (VEN-02 / VEN-03).
 * Ida e volta sao passagens independentes; aqui so se valida que o par faz sentido no momento da compra.
 */
@Component
public class ValidadorTrechos {

    private final boolean voltaExigeOrigemIgualDestinoIda;

    public ValidadorTrechos(
            @Value("${expressounix.venda.volta-origem-igual-destino-ida:true}") boolean voltaExigeOrigemIgualDestinoIda) {
        this.voltaExigeOrigemIgualDestinoIda = voltaExigeOrigemIgualDestinoIda;
    }

    /** Estrutura dos itens: vinculos IDA x VOLTA e assentos repetidos. Nao consulta o banco. */
    public void validarEstrutura(List<ItemVendaDTO> itens) {
        int n = itens.size();
        int[] voltasPorIda = new int[n];
        Set<String> assentos = new HashSet<>();

        for (int i = 0; i < n; i++) {
            ItemVendaDTO item = itens.get(i);

            if (!assentos.add(item.getIdViagem() + ":" + item.getIdAssento())) {
                throw new BusinessException("O assento " + item.getIdAssento() + " da viagem "
                        + item.getIdViagem() + " aparece mais de uma vez na venda (itens[" + i + "])");
            }

            if (item.getTipoTrecho() != TipoTrecho.VOLTA) {
                if (item.getVinculadaAoItem() != null) {
                    throw new BusinessException("itens[" + i + "]: vinculadaAoItem so pode ser usado em item VOLTA");
                }
                continue;
            }

            Integer ida = item.getVinculadaAoItem();
            if (ida == null) {
                throw new BusinessException("itens[" + i + "]: item VOLTA exige vinculadaAoItem apontando para a IDA");
            }
            if (ida < 0 || ida >= n || ida == i) {
                throw new BusinessException("itens[" + i + "]: vinculadaAoItem invalido (" + ida + ")");
            }
            if (itens.get(ida).getTipoTrecho() != TipoTrecho.IDA) {
                throw new BusinessException("itens[" + i + "]: vinculadaAoItem deve apontar para um item IDA");
            }
            voltasPorIda[ida]++;
        }

        for (int i = 0; i < n; i++) {
            if (itens.get(i).getTipoTrecho() != TipoTrecho.IDA) {
                continue;
            }
            if (voltasPorIda[i] == 0) {
                throw new BusinessException("itens[" + i + "]: item IDA sem VOLTA correspondente");
            }
            if (voltasPorIda[i] > 1) {
                throw new BusinessException("itens[" + i + "]: item IDA com mais de uma VOLTA");
            }
        }
    }

    /**
     * Coerencia entre viagens: a volta parte depois da ida e (regra configuravel) sai de onde a ida chegou.
     * {@code viagens} tem a mesma ordem de {@code itens}. Exige que validarEstrutura ja tenha passado.
     */
    public void validarCoerencia(List<ItemVendaDTO> itens, List<ViagemModels> viagens) {
        for (int i = 0; i < itens.size(); i++) {
            ItemVendaDTO item = itens.get(i);
            if (item.getTipoTrecho() != TipoTrecho.VOLTA) {
                continue;
            }
            ViagemModels volta = viagens.get(i);
            ViagemModels ida = viagens.get(item.getVinculadaAoItem());

            if (!partida(volta).isAfter(partida(ida))) {
                throw new BusinessException("itens[" + i + "]: a viagem de volta deve ser posterior a viagem de ida");
            }
            if (voltaExigeOrigemIgualDestinoIda && !mesmoLocal(volta.getOrigem(), ida.getDestino())) {
                throw new BusinessException("itens[" + i + "]: a origem da volta (" + volta.getOrigem()
                        + ") deve ser o destino da ida (" + ida.getDestino() + ")");
            }
        }
    }

    private LocalDateTime partida(ViagemModels viagem) {
        return LocalDateTime.of(viagem.getDataViagem(), viagem.getHoraViagem());
    }

    private boolean mesmoLocal(String a, String b) {
        return a.trim().equalsIgnoreCase(b.trim());
    }
}
