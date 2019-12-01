package swd.SWDProject.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.filter.ServiceFilter;
import swd.SWDProject.model.ServiceDTO;
import swd.SWDProject.repository.mybatis.ServiceMapper;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class ServiceServiceImp implements swd.SWDProject.service.ServiceService {

    @Autowired
    ServiceMapper serviceMapper;

    @Override
    public List<ServiceDTO> getService(String filter) throws JsonProcessingException {
        try {
            log.info(StringRS.BEGIN_SERVICE + "getService");
            List<ServiceDTO> rs = null;

            ServiceFilter serviceFilter;
            serviceFilter = new ObjectMapper().readValue(filter, ServiceFilter.class);

            Map params = new HashMap();
            params.put("catogeryid", serviceFilter.getCatogeryId());
            params.put("serviceid", serviceFilter.getId());
            rs = serviceMapper.getServices(params);

            return  rs;
        } finally {
            log.info(StringRS.BEGIN_SERVICE + "getService");
        }
    }
}
