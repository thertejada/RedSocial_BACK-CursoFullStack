package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.redsocial.entity.Reaction;
import es.eoi.redsocial.repository.ReactionRepository;

@Service
public class ReactionServiceImpl implements ReactionService {

	@Autowired
	private ReactionRepository reactionRepo;

	@Override
	public Optional<List<Reaction>> findAllReactionOfMessage(Long id) {

		return reactionRepo.findByMessage_Id(id);
	}

	/**
	 * Como no se puede buscar por la id buscamos por el mensaje id y usuario.
	 * Si ya existe: Si es el mismo tipo, la borramos. Si es diferente tipo, la actualizamos.
	 */
	@Override
	public void createReaction(Reaction reaction) {
		Optional<Reaction> r = reactionRepo.findByMessage_IdAndUser_Id(reaction.getMessage().getId(),
				reaction.getUser().getId());
		if (!r.isPresent()) {
			reactionRepo.save(reaction);
		} else {
			if (r.get().getType() == reaction.getType()) {
				reactionRepo.deleteById(r.get().getId());
			} else {
				r.get().setType(reaction.getType());
				reactionRepo.save(r.get());
			}
		}

	}

}
