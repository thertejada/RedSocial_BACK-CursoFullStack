package es.eoi.redsocial.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.redsocial.entity.RelationShip;
import es.eoi.redsocial.enums.RelationShipStateEnum;

@Repository
public interface RelationshipRepository extends JpaRepository<RelationShip, Long> {

	@Query("SELECT rs FROM RelationShip rs" +
			" WHERE rs.userMe.id = ?1 AND (rs.state = ?2 OR rs.state = ?3)")
	Optional<List<RelationShip>> findByUserMe_IdAndStateOrState(
			Long usermeId, RelationShipStateEnum state, RelationShipStateEnum pendingToMe);

	Optional<RelationShip> findByUserMe_IdAndUserFriend_Id(Long usermeId, Long userfriendId);

}
