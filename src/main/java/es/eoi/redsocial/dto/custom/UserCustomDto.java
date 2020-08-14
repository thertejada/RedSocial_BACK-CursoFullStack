package es.eoi.redsocial.dto.custom;

import es.eoi.redsocial.dto.UserSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCustomDto {

	private UserSimpleDto user;
	private Long total;

}
