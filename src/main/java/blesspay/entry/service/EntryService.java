package blesspay.entry.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import blesspay.entry.model.entity.Entry;
import blesspay.entry.model.to.CreateEntryTO;
import blesspay.entry.persistence.EntryRepository;
import blesspay.entry.util.HttpConnectionUtil;


@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	public void createEntry(CreateEntryTO createEntryTO) throws Exception {
		boolean success = HttpConnectionUtil.sendPostRequest(
				"http://localhost:8081/api/v1/entries/create-entry",
				new ObjectMapper().writeValueAsString(createEntryTO));
		
		if(success) {
			entryRepository.save(Entry.fromCreateEntryTO(createEntryTO));
		} else {
			throw new Exception("An error occured on the orchestrator API, try again in a few minutes");
		}
	}
	
	public Entry getEntry(String entry) {
		Entry entryClaimed = entryRepository.findFirstByEntryAndSituationOrderByIdDesc(entry, 1).orElse(null);
		return entryClaimed;
	}
	
	public void deleteEntry(String entry) throws Exception {
		Optional<Entry> entryOpt = entryRepository.findFirstByEntryAndSituationOrderByIdDesc(entry, 1);
		
		if(entryOpt.isPresent()) {
			Entry entryObtained = entryOpt.get();
			entryObtained.setSituation(0);
			entryRepository.save(entryObtained);
		} else {
			throw new Exception("Entry doesn't exists");
		}
		
	}
	
}
