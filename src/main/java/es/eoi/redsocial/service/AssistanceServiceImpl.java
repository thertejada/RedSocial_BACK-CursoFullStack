package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eoi.redsocial.entity.Assistance;
import es.eoi.redsocial.entity.Event;
import es.eoi.redsocial.entity.User;
import es.eoi.redsocial.enums.AssistanceStateEnum;
import es.eoi.redsocial.repository.AssistanceRepository;
import es.eoi.redsocial.repository.EventRepository;
import es.eoi.redsocial.repository.UserRepository;
import es.eoi.redsocial.service.UserServiceImpl.IncremetScoreType;

@Service
public class AssistanceServiceImpl implements AssistanceService {

	@Autowired
	private AssistanceRepository assitanceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public void createUpdateAssitance(Assistance assistance) {
		// Si la assistance no existe hay que crearla, si no, actualizarla
		Optional<Assistance> a = assitanceRepository.findByUser_IdAndEvent_id(
				assistance.getUser().getId(),
				assistance.getEvent().getId());
		assitanceRepository.flush();

		if (a.isPresent()) { // Actualizar estado
			a.get().setState(assistance.getState());
			assitanceRepository.save(a.get());
		} else {
			assitanceRepository.save(assistance);
		}

		if (assistance.getState() == AssistanceStateEnum.YES) {
			userService.incrementScoreUser(assistance.getUser(), IncremetScoreType.ASSISTANCE);
			userService.incrementScoreUser(
					eventRepository.findById(assistance.getEvent().getId()).get().getUser(),
					IncremetScoreType.ASSISTANCE_CREATOR);
		}
	}

	@Override
	public Optional<List<User>> getAllUsersAssistanceByState(Long idAsistance, AssistanceStateEnum state) {
		return userRepository.findAllUsersAssistanceState(idAsistance, state);
	}

	@Override
	public Optional<List<Event>> getAllAssistanceByUserAndState(Long idUser, AssistanceStateEnum state) {
		return eventRepository.getAllAssistanceByUserAndAssistanceState(idUser, state);
	}

}
