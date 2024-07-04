package com.Cliente.Api.controller;

import com.Cliente.Api.entities.Client;
import com.Cliente.Api.services.ClientsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/clients")
public class ClientsController {
    @Autowired
    private ClientsServices services;

    @PostMapping
    public void createClient(@RequestBody Client client){
        try{
            services.saveClient(client);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Create error");
        }
    }
    @GetMapping
    public List<Client> readsClients(){
        try{
            return services.readClients();
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Read all error");
        }
    }
    @GetMapping("/{id}")
    public Optional<Client> readOneClient(@PathVariable Long id){
        try{
            return services.readOneClient(id);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Read by id error");
        }
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        try{
            services.destroy(id);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Delete error");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        try {
            Optional<Client> updatedClient = services.updateClient(id, client);
            return updatedClient.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
