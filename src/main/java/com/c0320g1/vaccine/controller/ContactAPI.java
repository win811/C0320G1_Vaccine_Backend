package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.service.ContactReplyService;
import com.c0320g1.vaccine.service.ContactService;
import com.c0320g1.vaccine.model.Contact;
import com.c0320g1.vaccine.model.ContactReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/contact")
public class ContactAPI {
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactReplyService contactReplyService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllContact(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        Contact contact = new Contact();
        Page<Contact> contactPage = contactService.findAll(paging);
        Map<String, Object> response = new HashMap<>();
        if (contactPage.isEmpty()) {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("message", "Không có phản hồi nào được tìm thấy !");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("status", HttpStatus.OK);
        response.put("message", "Lấy dữ liệu thành công !");
        response.put("body", contactPage.getContent());
        response.put("currentPage", contactPage.getNumber());
        response.put("totalItems", contactPage.getTotalElements());
        response.put("totalPages", contactPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> saveContact(
            @RequestBody Contact contact
    ) {
        Map<String, Object> response = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        contact.setReceivingTime(localDateTime);
        contact.setStatus("Chưa xử lí");
        contactService.save(contact);
        response.put("status", HttpStatus.OK);
        response.put("message", "Gửi phản hồi thành công !");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/reply/{id}")
    public ResponseEntity<Map<String, Object>> saveContactReply(
            @RequestBody ContactReply contactReply,
            @PathVariable("id") Long id
    ) {
        Map<String, Object> response = new HashMap<>();
        Contact contact = contactService.findById(id);
        contact.setStatus("Đang xử lí");
        contactReply.setContact(contact);
        LocalDateTime localDateTime = LocalDateTime.now();
        contactReply.setReplyTime(localDateTime);
        contactReplyService.save(contactReply);
        response.put("status", HttpStatus.OK);
        response.put("message", "Gửi phản hồi thành công !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/reply/id")
    public ResponseEntity<Map<String, Object>> endContact(
            @PathVariable("id") Long id
    ) {
        Map<String, Object> response = new HashMap<>();
        Contact contact = contactService.findById(id);
        contact.setStatus("Hoàn thành");
        LocalDateTime localDateTime = LocalDateTime.now();
        contact.setEndTime(localDateTime);
        contactService.save(contact);
        response.put("status", HttpStatus.OK);
        response.put("message", "Thay đổi trạng thái thành công");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
