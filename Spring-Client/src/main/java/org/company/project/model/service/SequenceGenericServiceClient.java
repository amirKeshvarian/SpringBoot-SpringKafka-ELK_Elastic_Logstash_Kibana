package org.company.project.model.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("sequence-generator")
public interface SequenceGenericServiceClient {
    @GetMapping("/sequenceGenerator/nextVal.do")
    ResponseEntity<Long> nextVal ();
}
