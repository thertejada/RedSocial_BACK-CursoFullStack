package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import es.eoi.redsocial.entity.Reaction;

public interface ReactionService {
	
	Optional<List<Reaction>> findAllReactionOfMessage(Long id);
	
	void createReaction(Reaction reaction);

}
