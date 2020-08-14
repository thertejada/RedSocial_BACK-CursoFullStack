package es.eoi.redsocial.dto.userFull;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import es.eoi.redsocial.dto.UserSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserFullEventDto {

	private Long id;
	private String name;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "es_ES", timezone = "Europe/Madrid")
	private Timestamp eventDate;
	private UserSimpleDto user;

}
