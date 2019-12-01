package swd.SWDProject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.model.ServiceDTO;
import swd.SWDProject.service.ServiceService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @GetMapping
    public ResponseEntity getService(String filter) {
        try {
            log.info(StringRS.BEGIN_CONTROLLER + "getService");
            List<ServiceDTO> listService= null;
            listService = serviceService.getService(filter);
            return ResponseEntity.ok(listService);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        } finally {
            log.info(StringRS.END_CONTROLLER + "getService");
        }
    }
}
