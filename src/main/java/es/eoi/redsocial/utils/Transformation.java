package es.eoi.redsocial.utils;

import java.util.List;

import es.eoi.redsocial.dto.EventDto;
import es.eoi.redsocial.dto.EventSimpleDto;
import es.eoi.redsocial.dto.MessageDto;
import es.eoi.redsocial.dto.ReactionDto;
import es.eoi.redsocial.dto.RelationshipDto;
import es.eoi.redsocial.dto.UserDto;
import es.eoi.redsocial.dto.UserNotPassDto;
import es.eoi.redsocial.dto.UserSimpleDto;
import es.eoi.redsocial.dto.custom.EventCustomDto;
import es.eoi.redsocial.dto.custom.UserCustomDto;
import es.eoi.redsocial.dto.userFull.UserFullDto;
import es.eoi.redsocial.entity.Event;
import es.eoi.redsocial.entity.Message;
import es.eoi.redsocial.entity.Reaction;
import es.eoi.redsocial.entity.RelationShip;
import es.eoi.redsocial.entity.User;
import es.eoi.redsocial.entity.custom.EventCustom;
import es.eoi.redsocial.entity.custom.UserCustom;

public interface Transformation {

	// User and Relationship
	UserNotPassDto userEntity_UsernotpassDto(User user);

	UserFullDto userEntity_UserfullDto(User user);

	List<UserNotPassDto> listUserEntity_UsernotpassDto(List<User> users);

	User userDto_UserEntity(UserDto user);

	List<RelationshipDto> listRelationshipEntity_RelationshipDto(List<RelationShip> relationShips);

	List<UserSimpleDto> listUserEntity_userSimpleDto(List<User> users);

	List<UserCustomDto> listUserCustomEntity_UserCustomDto(List<UserCustom> users);

	// Message and Reaction
	MessageDto messageEntity_MessageDto(Message message);

	Message messageDto_MessageEntity(MessageDto message);

	List<MessageDto> listMessageEntity_MessageDto(List<Message> messages);

	List<ReactionDto> listReactionEntity_ReactionDto(List<Reaction> reactions);

	Reaction reactionDto_ReactionEntity(ReactionDto reaction);

	// Event
	EventSimpleDto eventEntity_EventSimpleDto(Event event);

	EventDto eventEntity_EventDto(Event event);

	List<EventDto> listEventEntity_EventDto(List<Event> events);

	List<EventSimpleDto> listEventEntity_eventSimpleDto(List<Event> events);

	Event eventDto_EventEntity(EventDto eventDto);

	List<EventCustomDto> listEventCustomEntity_EventCustomDto(List<EventCustom> events);

}
