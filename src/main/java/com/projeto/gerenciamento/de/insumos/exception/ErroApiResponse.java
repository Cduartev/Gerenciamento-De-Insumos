package com.projeto.gerenciamento.de.insumos.exception;

import java.time.OffsetDateTime;
import java.util.List;

public record ErroApiResponse(
        OffsetDateTime datahora,
        int status,
        String erro,
        String mensagem,
        List<String> detalhes
) {
}
