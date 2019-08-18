package hci.section.demo.controller;

import hci.section.demo.entity.Section;
import hci.section.demo.exception.DataExist;
import hci.section.demo.exception.DataNotFound;
import hci.section.demo.model.Meta;
import hci.section.demo.model.Response;
import hci.section.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping("/sections")
    public Response getAllSections(){
        return new Response(Response.STATUS_OK,
                sectionService.getAllSection(),
                new Meta(HttpStatus.OK.value(), "Fetch all sections"));
    }

    @GetMapping("/section/{id}")
    public Response getSectionById(@PathVariable("id") Long id){
        try{
            return new Response(
                    Response.STATUS_OK,
                    sectionService.getSectionById(id),
                    new Meta(HttpStatus.OK.value(), "Fetch section by Id"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }

    @GetMapping("/section/search/{sectionName}")
    public Response getSectionByName(@PathVariable("sectionName") String sectionName){
        try{
            return new Response(
                    Response.STATUS_OK,
                    sectionService.getSectionByName(sectionName),
                    new Meta(HttpStatus.OK.value(), "Fetch section by section name"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }

    @PostMapping(value = "/section", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Response addSection(@RequestBody Section section){
        try{
            return new Response(Response.STATUS_OK,
                    sectionService.addSection(section),
                    new Meta(HttpStatus.CREATED.value(),"Add section success"));
        }catch (DataIntegrityViolationException err){
            throw new DataExist(err);
        }
    }

    @PutMapping("/section/{id}")
    public Response editSection(@PathVariable("id") Long id, @RequestBody Section section){
        try{
            return new Response(Response.STATUS_OK,
                    sectionService.editSection(section, id),
                    new Meta(HttpStatus.OK.value(), "Edit section success"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }

    @DeleteMapping("/section/{id}")
    public Response deleteSection(@PathVariable("id") Long id){
        try{
            sectionService.getSectionById(id);
            sectionService.deleteSection(id);
            return new Response(Response.STATUS_OK,
                    "",
                    new Meta(HttpStatus.OK.value(), "Delete section success"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }

}
