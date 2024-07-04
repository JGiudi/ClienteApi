package com.Cliente.Api.services;

import com.Cliente.Api.entities.Client;
import com.Cliente.Api.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsServices {

    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client){
        clientRepository.save(client);
    }
    public List<Client> readClients(){
        return clientRepository.findAll();
    }
    public Optional<Client> readOneClient(Long id){
        return clientRepository.findById(id);
    }
    public void destroy(Long id){
        clientRepository.deleteById(id);
    }
    public Optional<Client> updateClient(Long id, Client client) {
        return clientRepository.findById(id).map(existingClient -> {
            existingClient.setName(client.getName());
            existingClient.setLastname(client.getLastname());
            existingClient.setAge(client.getAge());
            existingClient.setDni(client.getDni());
            existingClient.setPuntos(client.getPuntos());
            return clientRepository.save(existingClient);
        });
    }


}
