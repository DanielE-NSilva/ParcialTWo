package co.com.poli.usersservice.controller;

import co.com.poli.usersservice.helper.*;
import co.com.poli.usersservice.persistence.entity.User;
import co.com.poli.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResponseBuild builder;
    private final Common common;

    @GetMapping
    public Response findAll(){
        return builder.success(userService.findAll());
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        if(user == null)
            return builder.success(new ArrayList<>(),NO_CONTENT.value());
        return builder.success(user);
    }

    @PostMapping
    public Response save(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(common.formatMessage(result));
        }
        return builder.success(userService.save(user), CREATED.value());
    }

    @DeleteMapping("/{id}")
    public Response deleteById(@PathVariable("id") Long id){
        User user = userService.delete(id);
        if(user == null)
            return builder.failed("User Not found", NOT_FOUND.value());
        if(user.getId() == 0)
            return builder.failed("Used on booking", NOT_ACCEPTABLE.value());
        return builder.success(user);
    }
}