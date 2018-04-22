package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "1";
    }

    //获取所有的用户
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        logger.info("获取用户列表");
        List<User> users = userService.findAllUsers();

        if (users.isEmpty()) {
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return  new ResponseEntity<List<User>>(users, HttpStatus.OK);

    }

    //通过id获取用户
    @RequestMapping(value = "/user/id", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {

        logger.info("Fetching User with id {}", id);

        Optional<User> user = userService.findById(id);

        if (user.get() == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("User with id " + id + " not found"), HttpStatus.OK);
        }

        return new ResponseEntity<User>(user.get(), HttpStatus.OK);

    }

    //创建一个用户
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {

        logger.info("Creating User: {}", user);

        if (userService.isUserExist(user)) {
            logger.error("Uable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity(new CustomErrorType("Uable to create. A User with name" + user.getName() + " already exist."),
                    HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());

        return  new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);

    }

    //修改用户通过id
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {

        logger.info("Updating User with id {}", id);

        User currentUser = userService.findById(id).get();

        if (currentUser == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to update. User with id "+ id + " not found."),
            HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());

        userService.updateUser(currentUser);

        return  new ResponseEntity<User>(currentUser, HttpStatus.OK);

    }

    //删除一个用户
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

        logger.info("Fetching & Deleting User with id {}", id);

        User user = userService.findById(id).get();

        if (user == null) {
            logger.error("Uable to delete. User with id {} not found.", id);
            return  new ResponseEntity(new CustomErrorType("Uable to delete.  User with id"+ id +" not found."),
                    HttpStatus.NOT_FOUND);
        }

        userService.deleteUserById(id);

        return  new ResponseEntity<User>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUser() {

        logger.info("Deteting All Users");

        userService.deleteAllUsers();

        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

    }

    

}
