package es.eoi.redsocial.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.eoi.redsocial.enums.EventStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Timestamp eventDate;

	@Column(columnDefinition = "integer default 0", nullable = false)
	private EventStateEnum state;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "event")
	private Set<Assistance> assistances;

	public Event(Long id, String name, String description, java.util.Date eventDate, User user, EventStateEnum state) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.eventDate = new Timestamp(eventDate.getTime());
		this.user = user;
		this.state = state;
	}

}
