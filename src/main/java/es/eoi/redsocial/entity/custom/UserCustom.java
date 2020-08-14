package es.eoi.redsocial.entity.custom;

import java.util.Date;

import es.eoi.redsocial.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCustom {

	private User user;
	private Long total;

	public UserCustom(Long id, String name, String surname, Date birthDate, Date startDate, String users, Integer score,
			Long total) {
		user = new User(id, name, surname, birthDate, startDate, users, score);
		this.total = total;
	}

}
