package org.fuzita.application.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fuzita.application.controllers.docs.ClientAPI;
import org.fuzita.application.usecases.create.CreateClientRequestDTO;
import org.fuzita.application.usecases.create.CreateClientResponseDTO;
import org.fuzita.application.usecases.create.ICreateClientUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController  implements ClientAPI {

    private final ICreateClientUseCase createClientUseCase;

    @PostMapping
    public ResponseEntity<CreateClientResponseDTO> create(
            @Valid @RequestBody CreateClientRequestDTO request
    ) {
        CreateClientResponseDTO response =
                createClientUseCase.execute(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
