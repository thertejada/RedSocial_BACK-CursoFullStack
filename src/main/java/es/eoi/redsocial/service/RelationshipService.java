package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import es.eoi.redsocial.entity.RelationShip;

public interface RelationshipService {

	Optional<List<RelationShip>> findAllRelationshipsFriendStatus(Long usermeId);

	Optional<List<RelationShip>> findAllRelationshipsPendingStatus(Long usermeId);

	boolean createRelationShip(RelationShip relationShip);

	void updateRelationShip(RelationShip relationShip);

	void deleteRelationShip(RelationShip relationShip);

}
