package com.appsigel.crud_spring.dto;

import java.util.List;

public record ClientePageDTO(List<ClienteDTO> clientes, long totalElements, int totalPages) {

}
