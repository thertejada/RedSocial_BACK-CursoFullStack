package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import es.eoi.redsocial.entity.Assistance;
import es.eoi.redsocial.entity.Event;
import es.eoi.redsocial.entity.User;
import es.eoi.redsocial.enums.AssistanceStateEnum;

public interface AssistanceService {

	void createUpdateAssitance(Assistance assistance);

	Optional<List<User>> getAllUsersAssistanceByState(Long idAsistance, AssistanceStateEnum state);

	Optional<List<Event>> getAllAssistanceByUserAndState(Long idUser, AssistanceStateEnum state);

}
