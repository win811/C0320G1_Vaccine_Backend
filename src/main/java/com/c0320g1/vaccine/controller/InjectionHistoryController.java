package com.c0320g1.vaccine.controller;
import com.c0320g1.vaccine.dto.InjectionHistoryDTO;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class InjectionHistoryController {

    @Autowired
    InjectionHistoryService injectionHistoryService;

    //    Quân
//    @GetMapping("/injection-list")
//    public ResponseEntity<Page<InjectionHistoryDTO>> getListInjected(@RequestParam(name = "fullName", defaultValue = "") String fullName,
//                                                                     @RequestParam(name = "injected", defaultValue = "") String injected,
//                                                                     @RequestParam(name = "page", defaultValue = "0") int page) {
//        Page<InjectionHistoryDTO> injectionHistoryDTO = injectionHistoryService.search(fullName, injected, page);
//        if (injectionHistoryDTO.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return ResponseEntity.ok(injectionHistoryDTO);
//    }

    // Thành Long
    @GetMapping("/account/injection-history/{patientId}")
    public ResponseEntity<Page<InjectionHistory>> getAll (@PathVariable(value = "patientId") Long patientId,
                                                          @PageableDefault(value = 5) Pageable pageable){
        Page<InjectionHistory> injectionHistories = injectionHistoryService.findInjectionHistoryByPatientId(patientId, pageable);
        return ResponseEntity.ok(injectionHistories);
    }

    //Tùng

}


