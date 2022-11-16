package blesspay.entry.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import blesspay.entry.model.entity.Entry;
import blesspay.entry.model.to.CreateEntryTO;
import blesspay.entry.model.to.OrchestratorResponseTO;
import blesspay.entry.persistence.EntryRepository;
import blesspay.entry.util.HttpConnectionUtil;


@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Value("${orchestratorUrl}")
	private String orchestratorUrl;
	
	public OrchestratorResponseTO createEntry(CreateEntryTO createEntryTO) throws Exception {
		Optional<OrchestratorResponseTO> response = HttpConnectionUtil.sendPostRequest(
				orchestratorUrl + "/v1/entries/create-entry",
				new ObjectMapper().writeValueAsString(createEntryTO));
		
		if(response.isPresent()) {
			
			if(response.get().getStatusCode() == 200) {
				entryRepository.save(Entry.fromCreateEntryTO(createEntryTO));
			}
			
			return response.get();
			
			
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
