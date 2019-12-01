package swd.SWDProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import swd.SWDProject.model.ServiceDTO;

import java.util.List;

public interface ServiceService {

    List<ServiceDTO> getService(String filter) throws JsonProcessingException;

}
