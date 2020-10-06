package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.dto.InjectionHistoryDTO;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import com.c0320g1.vaccine.service.PatientService;
import com.c0320g1.vaccine.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class InjectionHistoryController {

    @Autowired
    InjectionHistoryService injectionHistoryService;
    //    CREATE BY ANH ĐỨC
    @Autowired
    private PatientService patientService;
    @Autowired
    private VaccineService vaccineService;

    //    Quân
    @GetMapping("/injection-list")
    public ResponseEntity<Page<InjectionHistoryDTO>> getListInjected(@RequestParam(name = "fullName", defaultValue = "") String fullName,
                                                                     @RequestParam(name = "injected", defaultValue = "") String injected,
                                                                     @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<InjectionHistoryDTO> injectionHistoryDTO = injectionHistoryService.search(fullName, injected, page);
        if (injectionHistoryDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(injectionHistoryDTO);
    }

    //    CREATE BY ANH ĐỨC
    @PostMapping("injection/registration")
    public ResponseEntity<Map<String, Object>> registration(@RequestBody InjectionHistory injectionHistory) {
        Map<String, Object> response = new HashMap<>();
        if (vaccineService.findById(injectionHistory.getVaccine().getId()) == null) {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("message", "Vắc xin này không tồn tại ");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

//        Kiểm tra xem bệnh nhân đã tồn tại hay chưa, nếu chưa sẽ tạo mới bệnh nhân vào database rồi trả về
        injectionHistory.setPatient(patientService.checkPatient(injectionHistory.getPatient()));
        this.injectionHistoryService.save(injectionHistory);
        response.put("status", HttpStatus.OK);
        response.put("message", "Đăng kí tiêm chủng theo yêu cầu thành công ! ");
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }

    // Thành Long
    @GetMapping("/account/injection-history/{accountId}")
    public ResponseEntity<Page<InjectionHistory>> getAllInjectionHistoryByAccountId (@PathVariable(value = "accountId") Long accountId,
                                                                                     @PageableDefault(value = 5) Pageable pageable){
        Page<InjectionHistory> injectionHistories = injectionHistoryService.findInjectionHistoryByAccountId(accountId, pageable);
        return ResponseEntity.ok(injectionHistories);
    }
}
