package hci.section.demo.controller;

import hci.section.demo.entity.Section;
import hci.section.demo.entity.SectionGroup;
import hci.section.demo.exception.DataExist;
import hci.section.demo.exception.DataNotFound;
import hci.section.demo.model.Meta;
import hci.section.demo.model.Response;
import hci.section.demo.service.SectionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class SectionGroupController {
    @Autowired
    private SectionGroupService sectionGroupService;

    @GetMapping("/section-group")
    public Response getAllSectionGroup(){
        return new Response(Response.STATUS_OK,
                sectionGroupService.getAllSectionGroup(),
                new Meta(HttpStatus.OK.value(), "Fetch all section group"));
    }

    @GetMapping("/section-group/{id}")
    public Response getSectionGroupById(@PathVariable("id") Long id){
        try{
            return new Response(
                    Response.STATUS_OK,
                    sectionGroupService.getSectionGroupById(id),
                    new Meta(HttpStatus.OK.value(), "Fetch section group by Id"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }

    @GetMapping("/section-group/search/{name}")
    public Response getSectionGroupByName(@PathVariable("name") String name){
        try{
            return new Response(
                    Response.STATUS_OK,
                    sectionGroupService.getSectionGroupByName(name),
                    new Meta(HttpStatus.OK.value(), "Fetch section group by section name"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }

    @PostMapping(value = "/section-group", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Response addSectionGroup(@RequestBody SectionGroup sectionGroup){
        try{
            return new Response(Response.STATUS_OK,
                    sectionGroupService.addSectionGroup(sectionGroup),
                    new Meta(HttpStatus.CREATED.value(),"Add section group success"));
        }catch (DataIntegrityViolationException err){
            throw new DataExist(err);
        }
    }

    @PutMapping("/section-group/{id}")
    public Response editSection(@PathVariable("id") Long id, @RequestBody SectionGroup sectionGroup){
        try{
            return new Response(Response.STATUS_OK,
                    sectionGroupService.editSectionGroup(sectionGroup, id),
                    new Meta(HttpStatus.OK.value(), "Edit section group success"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }

    @DeleteMapping("/section-group/{id}")
    public Response deleteSection(@PathVariable("id") Long id){
        try{
            sectionGroupService.getSectionGroupById(id);
            sectionGroupService.deleteSectionGroup(id);
            return new Response(Response.STATUS_OK,
                    "",
                    new Meta(HttpStatus.OK.value(), "Delete section group success"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }
}
