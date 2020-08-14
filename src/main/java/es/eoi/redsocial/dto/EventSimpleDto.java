package es.eoi.redsocial.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import es.eoi.redsocial.enums.EventStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventSimpleDto {

	private Long id;
	private String name;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "es_ES", timezone = "Europe/Madrid")
	private Timestamp eventDate;
	private EventStateEnum state;
	private UserSimpleDto user;

}
