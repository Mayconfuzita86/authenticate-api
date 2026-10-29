package org.fuzita.application.controllers.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.fuzita.application.usecases.create.CreateClientRequestDTO;
import org.fuzita.application.usecases.create.CreateClientResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Clients",
        description = "Gerenciamento de aplicações clientes da AUTHENTICATE API"
)
public interface ClientAPI {

    @Operation(
            summary = "Cadastrar aplicação cliente",
            description = "Cria uma aplicação cliente e gera suas credenciais de autenticação."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Aplicação cliente criada com sucesso",
            content = @Content(
                    schema = @Schema(
                            implementation = CreateClientResponseDTO.class
                    )
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Dados da requisição inválidos"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor"
    )
    ResponseEntity<CreateClientResponseDTO> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da aplicação cliente",
                    required = true,
                    content = @Content(
                            schema = @Schema(
                                    implementation = CreateClientRequestDTO.class
                            )
                    )
            )
            @RequestBody CreateClientRequestDTO request
    );
}
