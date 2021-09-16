package com.alianza.ptecnica.clients.services.impl;

import com.alianza.ptecnica.clients.io.ClientNewRequest;
import com.alianza.ptecnica.clients.io.ClientResponse;
import com.alianza.ptecnica.clients.repositories.ClientDao;
import com.alianza.ptecnica.clients.repositories.entities.ClientEntity;
import com.alianza.ptecnica.clients.services.ClientService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public List<ClientResponse> list() {
        try {
            logger.info("function start -> list");
            return clientDao.findAll().stream()
                    .map(clientEntity -> (new ModelMapper()).map(clientEntity, ClientResponse.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<ClientResponse> listBySharedKey(String sharedKeySearch) {
        try {
            logger.info("function start -> listBySharedKey");
            return clientDao.findAll().stream()
                    .map(clientEntity -> (new ModelMapper()).map(clientEntity, ClientResponse.class))
                    .filter(clientResponse -> Objects.equals(sharedKeySearch, clientResponse.getSharedKey()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<ClientResponse> listByOptions(ClientNewRequest request) {
        try {
            logger.info("function start -> listByOptions");
            return clientDao.findAll().stream()
                    .filter(clientResponse -> isNullOrEmpty(request.getName()) || Objects.equals(request.getName(), clientResponse.getName()))
                    .filter(clientResponse -> isNullOrEmpty(request.getEmail()) || Objects.equals(request.getEmail(), clientResponse.getEmail()))
                    .filter(clientResponse -> isNullOrEmpty(request.getPhone()) || Objects.equals(request.getPhone(), clientResponse.getPhone()))
                    .filter(clientResponse -> Objects.isNull(request.getStartDate()) || Objects.equals(request.getStartDate(), clientResponse.getStartDate()))
                    .filter(clientResponse -> Objects.isNull(request.getEndDate()) || Objects.equals(request.getEndDate(), clientResponse.getEndDate()))
                    .map(clientEntity -> (new ModelMapper()).map(clientEntity, ClientResponse.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    private String generateSharedKey(String name) {
        String[] nameParted = Objects.requireNonNull(name).toLowerCase().split(" ");
        if (nameParted.length == 1) {
            return nameParted[0].toLowerCase();
        } else if (nameParted.length == 2) {
            return (nameParted[0].charAt(0) + nameParted[1]).toLowerCase();
        } else if (nameParted.length == 3) {
            return (nameParted[0].charAt(0) + nameParted[1].charAt(0) + nameParted[2]).toLowerCase();
        } else if (nameParted.length == 4) {
            return (nameParted[0].charAt(0) + nameParted[1].charAt(0) + +nameParted[3].charAt(0) + nameParted[2]).toLowerCase();
        }
        return name;
    }

    private Boolean isNullOrEmpty(String text) {
        return (Objects.isNull(text) || Objects.equals(text, ""));
    }

    @Override
    public Long newClient(ClientNewRequest request) {
        try {
            logger.info("function start -> list");
            return clientDao.save(ClientEntity.builder()
                    .name(request.getName())
                    .sharedKey(generateSharedKey(request.getName()))
                    .businessId(request.getName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .startDate(request.getStartDate())
                    .endDate(request.getEndDate())
                    .dataAdded(new Date())
                    .build()).getIdClient();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return -1L;
        }
    }
}
