package org.fuzita.application.usecases.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fuzita.domain.client.Client;
import org.fuzita.domain.client.ClientStatus;
import org.fuzita.domain.client.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateClient implements ICreateClientUseCase {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateClientResponseDTO execute(CreateClientRequestDTO dto) {
        log.info("Criando aplicação cliente: {}", dto.getName());

        String clientKey = UUID.randomUUID().toString();
        String clientSecret = UUID.randomUUID().toString();
        String clientSecretHash = passwordEncoder.encode(clientSecret);

        Client client = new Client(
                UUID.randomUUID(),
                dto.getName().trim(),
                clientKey,
                clientSecretHash,
                ClientStatus.ACTIVE,
                Instant.now()
        );

        Client savedClient = clientRepository.save(client);

        log.info("Aplicação cliente criada com sucesso. ID: {}",
                savedClient.getId());

        return CreateClientMapper.toOutput(savedClient, clientSecret);
    }
}
