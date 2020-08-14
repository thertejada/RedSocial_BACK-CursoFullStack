package es.eoi.redsocial.dto.userFull;

import es.eoi.redsocial.enums.AssistanceStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserFullAssistanceDto {

	private AssistanceStateEnum state;
	private UserFullEventDto event;

}
