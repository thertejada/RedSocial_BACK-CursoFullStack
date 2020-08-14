package es.eoi.redsocial.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import es.eoi.redsocial.dto.ExceptionDto;

@Component
public class ManagerExceptionImpl implements ManagerException {

	@Override
	public ResponseEntity<ExceptionDto> getExceptionDto(DataAccessException e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new ExceptionDto(e.getMessage(), sw.toString()));
	}

}