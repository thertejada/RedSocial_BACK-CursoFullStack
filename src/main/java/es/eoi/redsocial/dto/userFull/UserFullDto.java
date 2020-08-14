package es.eoi.redsocial.dto.userFull;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFullDto {

	private Long id;
	private String name;
	private String surname;
	private Date birthDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "es_ES", timezone = "Europe/Madrid")
	private Timestamp startDate;
	private String user;
	private Integer score;
	private Set<UserFullRelationshipDto> relationsShip;
	private Set<UserFullAssistanceDto> assistEvents;
	private Set<UserFullEventDto> createEvents;

}
