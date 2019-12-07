package swd.SWDProject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.model.ServiceDTO;
import swd.SWDProject.service.HouseService;
import swd.SWDProject.service.ServiceService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/houses")
public class HouseController {

    @Autowired
    HouseService houseService;

    @PutMapping(value = "/member/{username}")
    public ResponseEntity addMemberToHouse(@PathVariable String username) {
        try {
            log.info(StringRS.BEGIN_CONTROLLER + "addMemberToHouse");

            houseService.addMemberToHouse(username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } finally {
            log.info(StringRS.BEGIN_CONTROLLER + "addMemberToHouse");
        }
    }

    @DeleteMapping(value = "/member/{username}")
    public ResponseEntity removeMemberToHouse(@PathVariable String username) {
        try {
            log.info(StringRS.BEGIN_CONTROLLER + "addMemberToHouse");

            houseService.deleteMemberToHouse(username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } finally {
            log.info(StringRS.BEGIN_CONTROLLER + "addMemberToHouse");
        }
    }

}
