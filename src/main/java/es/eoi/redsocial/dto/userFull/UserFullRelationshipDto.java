package es.eoi.redsocial.dto.userFull;

import es.eoi.redsocial.dto.UserSimpleDto;
import es.eoi.redsocial.enums.RelationShipStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFullRelationshipDto {

	private Long id;
	private RelationShipStateEnum state;
	private UserSimpleDto userFriend;

}
