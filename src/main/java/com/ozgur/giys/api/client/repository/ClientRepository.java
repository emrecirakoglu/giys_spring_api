package com.ozgur.giys.api.client.repository;

import com.ozgur.giys.api.client.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
