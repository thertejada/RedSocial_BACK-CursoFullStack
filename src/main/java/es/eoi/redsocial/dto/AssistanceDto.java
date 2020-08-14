package es.eoi.redsocial.dto;

import es.eoi.redsocial.enums.AssistanceStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssistanceDto {

	private Long id;
	private AssistanceStateEnum state;
	UserSimpleDto user;

}
