package es.eoi.redsocial.dto;

import es.eoi.redsocial.enums.ReactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReactionDto {
	
	private Long id;
	private ReactionTypeEnum type;
	//MessageDto message;
	UserSimpleDto user;

}
