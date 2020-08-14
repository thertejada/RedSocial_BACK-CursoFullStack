package es.eoi.redsocial.dto.custom;

import es.eoi.redsocial.dto.EventSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventCustomDto {

	private EventSimpleDto event;
	private Long total_assist;

}
