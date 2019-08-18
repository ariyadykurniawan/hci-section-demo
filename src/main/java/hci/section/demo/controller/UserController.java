package hci.section.demo.controller;

import hci.section.demo.entity.User;
import hci.section.demo.exception.DataExist;
import hci.section.demo.exception.DataNotFound;
import hci.section.demo.model.Meta;
import hci.section.demo.model.Response;
import hci.section.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Response getAlluser(){
        return new Response(Response.STATUS_OK,
                userService.getAllUser(),
                new Meta(HttpStatus.OK.value(), "Fetch all users"));
    }

    @GetMapping("/user/{id}")
    public Response getUserById(@PathVariable("id") Long id){
        try{
            return new Response(
                    Response.STATUS_OK,
                    userService.getUserById(id),
                    new Meta(HttpStatus.OK.value(), "Fetch user by Id"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }

    @GetMapping("/user/search/{username}")
    public Response getUserByUsername(@PathVariable("username") String username){
        try{
            return new Response(
                    Response.STATUS_OK,
                    userService.getUserByUsername(username),
                    new Meta(HttpStatus.OK.value(),"fetch use by Username"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }

    @GetMapping("/user/section/{id}")
    public Response getUserSection(@PathVariable("id") Long id){
        try{
            return new Response(
                    Response.STATUS_OK,
                    userService.getUserSection(id),
                    new Meta(HttpStatus.OK.value(), "fetch user section"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }

    @PostMapping(value = "/user", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Response addUser(@RequestBody User user){
        try{
            return new Response(Response.STATUS_OK,
                    userService.addUser(user),
                    new Meta(HttpStatus.CREATED.value(),"Add user success"));
        }catch (DataIntegrityViolationException err){
            throw new DataExist(err);
        }
    }

    @PutMapping("/user/{id}")
    public Response editUser(@PathVariable("id") Long id, @RequestBody User user){
        try{
            return new Response(Response.STATUS_OK,
                    userService.editUser(user, id),
                    new Meta(HttpStatus.OK.value(), "Edit user success"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }catch (DataIntegrityViolationException err){
            throw new DataExist(err);
        }
    }

    @DeleteMapping("/user/{id}")
    public Response deleteUser(@PathVariable("id") Long id){
        try{
            userService.getUserById(id);
            userService.deleteUser(id);
            return new Response(Response.STATUS_OK,
                    "",
                    new Meta(HttpStatus.OK.value(), "Delete user success"));
        }catch (NoSuchElementException err){
            throw new DataNotFound(err);
        }
    }
}
