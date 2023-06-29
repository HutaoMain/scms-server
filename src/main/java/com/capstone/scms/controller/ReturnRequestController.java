package com.capstone.scms.controller;

import com.capstone.scms.model.ReturnRequest;
import com.capstone.scms.service.ReturnRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returnRequest")
@CrossOrigin("*")
public class ReturnRequestController {

    @Autowired
    ReturnRequestService returnRequestService;


    @PostMapping("/create")
    public ResponseEntity<ReturnRequest> createReturnRequest(@RequestBody ReturnRequest returnRequest) {
        ReturnRequest createdReturnRequest = returnRequestService.createReturnRequest(returnRequest);
        return ResponseEntity.ok(createdReturnRequest);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ReturnRequest> getReturnRequestById(@PathVariable Long id) {
        ReturnRequest returnRequest = returnRequestService.getReturnRequestById(id);
        return ResponseEntity.ok(returnRequest);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ReturnRequest>> getAllReturnRequests() {
        List<ReturnRequest> returnRequests = returnRequestService.getAllReturnRequests();
        return ResponseEntity.ok(returnRequests);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReturnRequest> updateReturnRequest(
            @PathVariable Long id, @RequestBody ReturnRequest updatedReturnRequest) {
        ReturnRequest returnRequest = returnRequestService.updateReturnRequest(id, updatedReturnRequest);
        return ResponseEntity.ok(returnRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReturnRequest(@PathVariable Long id) {
        returnRequestService.deleteReturnRequest(id);
        return ResponseEntity.ok("Return Request deleted successfully");
    }

}
