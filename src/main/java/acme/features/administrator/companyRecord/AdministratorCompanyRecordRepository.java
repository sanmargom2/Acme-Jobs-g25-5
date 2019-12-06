package acme.features.administrator.companyRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCompanyRecordRepository extends AbstractRepository{

	
	@Query("select r from CompanyRecord r where r.id = ?1")
	CompanyRecord findOneById(int id);
	
	@Query("select r from CompanyRecord r")
	Collection<CompanyRecord> findManyAll();



}
