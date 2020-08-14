package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eoi.redsocial.entity.RelationShip;
import es.eoi.redsocial.enums.RelationShipStateEnum;
import es.eoi.redsocial.repository.RelationshipRepository;

@Service
public class RelationshipServiceImpl implements RelationshipService {

	@Autowired
	private RelationshipRepository repository;

	@Override
	public Optional<List<RelationShip>> findAllRelationshipsFriendStatus(Long usermeId) {
		return repository.findByUserMe_IdAndStateOrState(usermeId, RelationShipStateEnum.FRIEND, RelationShipStateEnum.FRIEND);
	}

	@Override
	public Optional<List<RelationShip>> findAllRelationshipsPendingStatus(Long usermeId) {
		return repository.findByUserMe_IdAndStateOrState(
				usermeId,
				RelationShipStateEnum.PENDING,
				RelationShipStateEnum.PENDING_TO_ME);
	}

	/**
	 * @return true: creado con exito
	 */
	@Override
	@Transactional
	public boolean createRelationShip(RelationShip relationShip) {
		if (!checkRelationShip(relationShip)) {
			repository.save(relationShip);
			repository.save(inverseRelationship(relationShip));
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public void updateRelationShip(RelationShip relationShip) {
		RelationShip r = repository.getOne(relationShip.getId());
		r.setState(relationShip.getState());
		repository.save(r);

		RelationShip r2 = getInverseRelationship(r);
		r2.setState(relationShip.getState());
		repository.save(r2);
	}

	@Override
	@Transactional
	public void deleteRelationShip(RelationShip relationShip) {
		repository.delete(getInverseRelationship(repository.getOne(relationShip.getId())));
		repository.delete(relationShip);
	}

	/**
	 * Es necesario crear en la base de datos la relación para ambos usuarios, para
	 * eso establecemos el userMe al userFriend y viceversa. Al no tener el objeto
	 * relationship entero, primero necesitamos obtenerlo de la base de datos.
	 */
	private RelationShip getInverseRelationship(RelationShip r) {
		return repository.getOne(
				repository.findByUserMe_IdAndUserFriend_Id(
						r.getUserFriend().getId(),
						r.getUserMe().getId())
						.get().getId());
	}

	/**
	 * Es necesario crear en la base de datos la relación para ambos usuarios, para
	 * eso establecemos el userMe al userFriend y viceversa.
	 */
	private RelationShip inverseRelationship(RelationShip relationShip) {
		return new RelationShip(0l, RelationShipStateEnum.PENDING_TO_ME, relationShip.getUserFriend(),
				relationShip.getUserMe());
	}

	/**
	 * Comprobar si existe la relación sin tener en cuenta el state
	 * 
	 * @return true: existe | false: no existe
	 */
	private boolean checkRelationShip(RelationShip relationShip) {
		return repository.findByUserMe_IdAndUserFriend_Id(
				relationShip.getUserMe().getId(),
				relationShip.getUserFriend().getId())
				.isPresent();
	}

}
