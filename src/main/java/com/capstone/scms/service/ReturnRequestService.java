package com.capstone.scms.service;

import com.capstone.scms.model.ReturnRequest;
import com.capstone.scms.repository.ReturnRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnRequestService {

    @Autowired
    ReturnRequestRepository returnRequestRepository;


    public ReturnRequest createReturnRequest(ReturnRequest returnRequest) {
        return returnRequestRepository.save(returnRequest);
    }

    public ReturnRequest getReturnRequestById(Long id) {
        return returnRequestRepository.findById(id).orElse(null);
    }

    public List<ReturnRequest> getAllReturnRequests() {
        return returnRequestRepository.findAll();
    }

    public ReturnRequest updateReturnRequest(Long id, ReturnRequest updatedReturnRequest) {
        ReturnRequest returnRequest = getReturnRequestById(id);
        returnRequest.setProductName(updatedReturnRequest.getProductName());
        returnRequest.setProductId(updatedReturnRequest.getProductId());
        returnRequest.setProductPrice(updatedReturnRequest.getProductPrice());
        returnRequest.setProductQuantity(updatedReturnRequest.getProductQuantity());
        returnRequest.setReason(updatedReturnRequest.getReason());
        returnRequest.setEmail(updatedReturnRequest.getEmail());
        return returnRequestRepository.save(returnRequest);
    }

    public void deleteReturnRequest(Long id) {
        returnRequestRepository.deleteById(id);
    }
}
