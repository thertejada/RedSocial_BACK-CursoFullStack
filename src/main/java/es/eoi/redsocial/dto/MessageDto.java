package es.eoi.redsocial.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

	private Long id;
	private String content;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "es_ES", timezone = "Europe/Madrid")
	private Timestamp publishDate;
	UserSimpleDto user;

}
