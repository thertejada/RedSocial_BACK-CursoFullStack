package es.eoi.redsocial.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;

import es.eoi.redsocial.dto.ExceptionDto;

public interface ManagerException {
	
	public ResponseEntity<ExceptionDto> getExceptionDto(DataAccessException e);

}