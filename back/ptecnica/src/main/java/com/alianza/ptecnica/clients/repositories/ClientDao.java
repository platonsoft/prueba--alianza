package com.alianza.ptecnica.clients.repositories;

import com.alianza.ptecnica.clients.repositories.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<ClientEntity, Long> {
}
