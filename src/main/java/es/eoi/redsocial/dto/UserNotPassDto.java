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
public class UserNotPassDto {

	private Long id;
	private String name;
	private String surname;
	private Date birthDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp startDate;
	private String user;
	private Integer score;

}
