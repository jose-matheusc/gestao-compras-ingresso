package br.com.projeto.gestaoingressos.ticketmanagement.service;

import br.com.projeto.gestaoingressos.ticketmanagement.dto.ClienteDTO;
import br.com.projeto.gestaoingressos.ticketmanagement.entity.Cliente;
import br.com.projeto.gestaoingressos.ticketmanagement.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteDTO criar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        Cliente salvo = clienteRepository.save(cliente);
        return toDTO(salvo);
    }

    public List<ClienteDTO> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ClienteDTO buscar(Long id) {
        return clienteRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(dto.getNome());
                    cliente.setEmail(dto.getEmail());
                    return toDTO(clienteRepository.save(cliente));
                })
                .orElse(null);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO toDTO(Cliente entity) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}

