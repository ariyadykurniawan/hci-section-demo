package hci.section.demo.controller;

import hci.section.demo.entity.SectionGroupDetail;
import hci.section.demo.entity.User;
import hci.section.demo.exception.DataExist;
import hci.section.demo.model.Meta;
import hci.section.demo.model.Response;
import hci.section.demo.service.SectionGroupDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SectionGroupDetailController {

    @Autowired
    private SectionGroupDetailService sectionGroupDetailService;

    @PostMapping(value = "/user/section", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Response addUser(@RequestBody SectionGroupDetail sectionGroupDetail){
        try{
            return new Response(Response.STATUS_OK,
                    sectionGroupDetailService.addSectionGroupDetail(sectionGroupDetail),
                    new Meta(HttpStatus.CREATED.value(),"Add Section Group Detail"));
        }catch (DataIntegrityViolationException err){
            throw new DataExist(err);
        }
    }
}
