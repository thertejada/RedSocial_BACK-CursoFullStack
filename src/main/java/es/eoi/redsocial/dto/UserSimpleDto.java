package es.eoi.redsocial.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSimpleDto {
	
	private Long id;
	private String name;
	private String surname;
	private String user;

}
