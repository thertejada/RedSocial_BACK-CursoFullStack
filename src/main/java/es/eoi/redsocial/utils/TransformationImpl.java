package es.eoi.redsocial.utils;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

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

@Component
public class TransformationImpl implements Transformation {

	private ModelMapper mapper;

	public TransformationImpl() {
		mapper = new ModelMapper();
	}

	// ********** User and Relationship **********
	@Override
	public UserNotPassDto userEntity_UsernotpassDto(User user) {
		return mapper.map(user, UserNotPassDto.class);
	}

	@Override
	public UserFullDto userEntity_UserfullDto(User user) {
		return mapper.map(user, UserFullDto.class);
	}

	@Override
	public List<UserNotPassDto> listUserEntity_UsernotpassDto(List<User> users) {
		return mapper.map(users, new TypeToken<List<UserNotPassDto>>() {
		}.getType());
	}

	@Override
	public User userDto_UserEntity(UserDto user) {
		return mapper.map(user, User.class);
	}

	@Override
	public List<RelationshipDto> listRelationshipEntity_RelationshipDto(List<RelationShip> relationShips) {
		return mapper.map(relationShips, new TypeToken<List<RelationshipDto>>() {
		}.getType());
	}

	@Override
	public List<UserSimpleDto> listUserEntity_userSimpleDto(List<User> users) {
		return mapper.map(users, new TypeToken<List<UserSimpleDto>>() {
		}.getType());
	}

	@Override
	public List<UserCustomDto> listUserCustomEntity_UserCustomDto(List<UserCustom> users) {
		return mapper.map(users, new TypeToken<List<UserCustomDto>>() {
		}.getType());
	}

	// ********** Message and Reaction **********
	@Override
	public MessageDto messageEntity_MessageDto(Message message) {
		return mapper.map(message, MessageDto.class);
	}

	@Override
	public Message messageDto_MessageEntity(MessageDto message) {
		return mapper.map(message, Message.class);
	}

	@Override
	public List<MessageDto> listMessageEntity_MessageDto(List<Message> messages) {
		return mapper.map(messages, new TypeToken<List<MessageDto>>() {
		}.getType());
	}

	@Override
	public List<ReactionDto> listReactionEntity_ReactionDto(List<Reaction> reactions) {
		return mapper.map(reactions, new TypeToken<List<ReactionDto>>() {
		}.getType());
	}

	@Override
	public Reaction reactionDto_ReactionEntity(ReactionDto reaction) {
		return mapper.map(reaction, Reaction.class);
	}

	// ********** Event **********
	@Override
	public EventSimpleDto eventEntity_EventSimpleDto(Event event) {
		return mapper.map(event, EventSimpleDto.class);
	}

	@Override
	public EventDto eventEntity_EventDto(Event event) {
		return mapper.map(event, EventDto.class);
	}

	@Override
	public List<EventDto> listEventEntity_EventDto(List<Event> events) {
		return mapper.map(events, new TypeToken<List<EventDto>>() {
		}.getType());
	}

	@Override
	public List<EventSimpleDto> listEventEntity_eventSimpleDto(List<Event> events) {
		return mapper.map(events, new TypeToken<List<EventSimpleDto>>() {
		}.getType());
	}

	@Override
	public Event eventDto_EventEntity(EventDto eventDto) {
		return mapper.map(eventDto, Event.class);
	}

	@Override
	public List<EventCustomDto> listEventCustomEntity_EventCustomDto(List<EventCustom> events) {
		return mapper.map(events, new TypeToken<List<EventCustomDto>>() {
		}.getType());
	}

}
