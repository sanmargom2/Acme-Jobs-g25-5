
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyByMessageThreadId(int id);

	@Query("select m from Message m where m.id = ?1")
	Message findOneMessageById(int id);

}
