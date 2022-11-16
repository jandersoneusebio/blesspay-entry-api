package blesspay.entry.persistence;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blesspay.entry.model.entity.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, BigInteger>{
	
	public Optional<Entry> findFirstByEntryAndSituationOrderByIdDesc(String entry, Integer situation);
	
	public Optional<List<Entry>> findByAccountDocumentAndSituation(String document, Integer situation);
}
