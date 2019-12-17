
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.members.Member;
import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select m from MessageThread m where m.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select m.messageThread from Member m where m.authenticated.id = ?1")
	Collection<MessageThread> findManyByUserId(int id);

	@Query("select m from Member m where m.messageThread.id = ?1 and m.authenticated.id = ?2")
	Member findMembers(int threadId, int authenticatedId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findUserById(int id);
}
