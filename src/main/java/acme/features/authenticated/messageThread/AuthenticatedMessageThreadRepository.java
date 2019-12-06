
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select m from MessageThread m where m.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select m from MessageThread m join m.members members where members.id = ?1")
	Collection<MessageThread> findManyByUserId(int id);

	//@Query("select distinct m from MessageThread m join fetch t.members mb
	//Clloection<> findMany
	@Query("select a.userAccount.username from MessageThread m join m.members a where m.id = ?1")
	Collection<String> findMembers(int id);

}
