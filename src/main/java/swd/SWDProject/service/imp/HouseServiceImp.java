package swd.SWDProject.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swd.SWDProject.config.sercurity.JWTVerifier;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.entity.User;
import swd.SWDProject.filter.ServiceFilter;
import swd.SWDProject.model.ServiceDTO;
import swd.SWDProject.repository.UserRepository;
import swd.SWDProject.repository.mybatis.ServiceMapper;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class HouseServiceImp implements swd.SWDProject.service.HouseService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void addMemberToHouse(String username) throws Exception {
        try {
            log.info(StringRS.BEGIN_SERVICE + "addMemberToHouse");

            User user = userRepository.findUserByUsername(JWTVerifier.USERNAME);

            if( user.getFamilyLevel() != 1) {
                throw new Exception();
            }

            User member = userRepository.findUserByUsername(username);

            if( member == null) {
                throw new Exception();
            }

            member.setFamilyLevel(2);
            member.setHouse(user.getHouse());

            userRepository.save(member);

        } finally {
            log.info(StringRS.BEGIN_SERVICE + "addMemberToHouse");
        }
    }
}
