package com.alianza.ptecnica.clients.services;

import com.alianza.ptecnica.clients.io.ClientNewRequest;
import com.alianza.ptecnica.clients.io.ClientResponse;

import java.util.List;

public interface ClientService {
    List<ClientResponse> list();
    List<ClientResponse> listBySharedKey(String sharedKeySearch);
    List<ClientResponse> listByOptions(ClientNewRequest request);
    Long newClient(ClientNewRequest request);
}
