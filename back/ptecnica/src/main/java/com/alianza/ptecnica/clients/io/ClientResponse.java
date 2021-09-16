package com.alianza.ptecnica.clients.io;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponse {
    private Long idClient;
    private String sharedKey;
    private String businessId;
    private String email;
    private String phone;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataAdded;
}
