package blesspay.entry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import blesspay.entry.model.entity.Entry;
import blesspay.entry.model.to.CreateEntryTO;
import blesspay.entry.model.to.OrchestratorResponseTO;
import blesspay.entry.service.EntryService;

@RestController
@RequestMapping("api/v1/entries")
public class EntryController {
	
	@Autowired
	private EntryService entryService;
	
	@PostMapping("create-entry")
	public ResponseEntity<?> createEntry(@RequestBody CreateEntryTO createEntryTO){
		try {
			OrchestratorResponseTO response = entryService.createEntry(createEntryTO);
			return new ResponseEntity<OrchestratorResponseTO>(response, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("get-entry")
	public ResponseEntity<Entry> getEntry(@RequestParam("entry") String entry){
		return new ResponseEntity<Entry>(entryService.getEntry(entry), HttpStatus.OK);
	}
	
	@DeleteMapping("delete-entry")
	public ResponseEntity<String> deleteEntry(@RequestParam("entry") String entry){
		try {
			entryService.deleteEntry(entry);
			return new ResponseEntity<String>("Entry deleted successfully", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
}
