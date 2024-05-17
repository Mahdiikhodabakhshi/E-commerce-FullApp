package com.tfg.emp.contactUs.infrastructure.rest;


import com.tfg.emp.contactUs.application.Dto.ContactUsDto;
import com.tfg.emp.contactUs.application.service.ContactUsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ContactUsRestController {
    
    private final ContactUsService contactUsService;

    public ContactUsRestController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @GetMapping(value = "/contacts/latest-message" , produces = "application/json")
    public ResponseEntity<Page<ContactUsDto>> getNewMessages(@RequestParam(value = "filter",required = false) String filter , Pageable pageable) {
        Sort sort =Sort.by(Sort.Direction.DESC,"sendDate");
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<ContactUsDto> contactUsDtoS = contactUsService.getContactUsByCriteriaPaged(pageable, filter);
        return ResponseEntity.ok(contactUsDtoS);
    }

    @GetMapping(value = "/contacts" , produces = "application/json")
    public ResponseEntity<Page<ContactUsDto>> getMessages(@RequestParam(value = "filter",required = false) String filter , Pageable pageable) {
        Sort sort =Sort.by(Sort.Direction.DESC,"sendDate");
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<ContactUsDto> contactUsDtoS = contactUsService.getContactUsByCriteriaPaged(pageable, filter);
        return ResponseEntity.ok(contactUsDtoS);
    }

    @GetMapping(value = "/contacts/{contactUsId}" , produces = "application/json")
    public ResponseEntity<ContactUsDto> getContactUs(@PathVariable("contactUsId") Long contactUsId) {
        return contactUsService.getContactUsById(contactUsId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/contacts" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<ContactUsDto> createContactUs(@RequestBody ContactUsDto contactUsDto) {

        ContactUsDto cDTO = contactUsService.saveContactUs(contactUsDto);
        return new ResponseEntity<>(cDTO , HttpStatus.CREATED);
    }

    @PatchMapping(value = "/contacts" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<ContactUsDto> updateContactUs(@RequestBody ContactUsDto contactUsDto) {
        ContactUsDto aDTO = contactUsService.saveContactUs(contactUsDto);
        return ResponseEntity.ok(aDTO);
    }

    @DeleteMapping(value = "/contacts/{contactUsId}" , produces = "application/json")
    public ResponseEntity<ContactUsDto> deleteActivity(@PathVariable("contactUsId") Long contactUsId) {
        contactUsService.deleteContactUsDto(contactUsId);

        return ResponseEntity.noContent().build();
    }
}
