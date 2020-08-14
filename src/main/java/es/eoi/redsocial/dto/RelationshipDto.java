package es.eoi.redsocial.dto;

import es.eoi.redsocial.enums.RelationShipStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelationshipDto {

	private Long id;
	private RelationShipStateEnum state;
	UserSimpleDto userFriend;

}
