package es.eoi.redsocial.dto;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Long id;
	private String name;
	private String surname;
	private Date birthDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "es_ES", timezone = "Europe/Madrid")
	private Timestamp startDate;
	private String user;
	private String pass;
	private Integer score;

}
