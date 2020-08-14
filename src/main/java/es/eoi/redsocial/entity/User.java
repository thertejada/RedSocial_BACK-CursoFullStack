package es.eoi.redsocial.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(nullable = false, length = 20)
	private String name;

	@Column(nullable = false, length = 40)
	private String surname;

	@Column(nullable = false)
	private Date birthDate;

	@Column(updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp startDate;

	@Column(unique = true, nullable = false, length = 20)
	private String user;

	@Column(nullable = false, length = 20)
	private String pass;

	@Column(columnDefinition = "integer default 0", nullable = false)
	private Integer score;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Message> writeMessages;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Event> createEvents;

	@ManyToMany(mappedBy = "userMe")
	private Set<RelationShip> relationsShip;

	@OneToMany(mappedBy = "user")
	private Set<Assistance> assistEvents;

	@OneToMany(mappedBy = "user")
	private Set<Reaction> reactionMessages;

	public User(Long id, String name, String surname, java.util.Date birthDate, java.util.Date startDate, String user,
			Integer score) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthDate = new Date(birthDate.getTime());
		this.startDate = new Timestamp(startDate.getTime());
		this.user = user;
		this.score = score;
	}
}
