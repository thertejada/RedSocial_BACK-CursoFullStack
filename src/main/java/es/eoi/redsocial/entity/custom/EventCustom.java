package es.eoi.redsocial.entity.custom;

import java.util.Date;

import es.eoi.redsocial.entity.Event;
import es.eoi.redsocial.entity.User;
import es.eoi.redsocial.enums.EventStateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventCustom {

	private Event event;
	private Long total_assist;

	public EventCustom(Long id, String name, String description, Date eventDate, User user, EventStateEnum state,
			Long total) {
		event = new Event(id, name, description, eventDate, user, state);
		this.total_assist = total;
	}
}
