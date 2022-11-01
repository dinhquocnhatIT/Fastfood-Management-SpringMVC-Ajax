package com.qn.controller.rest;

import com.qn.model.User;
import com.qn.model.dto.UserDTO;
import com.qn.service.user.IUserService;
import com.qn.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private AppUtil appUtils;

    @Autowired
    private IUserService userService;


    @GetMapping
    public ResponseEntity<?> showListUser() {
        List<UserDTO> userDTOS = userService.findAllUserDTOByDeletedIsFailse();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userService.findByUserId(id);
        if (userDTO.isPresent()) {
            return new ResponseEntity<>(userDTO.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        User user = userService.saveWithOutPassword(userDTO.toUser());
        return new ResponseEntity<>(user.toUserDTO(), HttpStatus.OK);
    }

    @PutMapping("/update/active")
    public ResponseEntity<?> doUpdateActive(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        userDTO.setStatus("Active");
        User user = userService.saveWithOutPassword(userDTO.toUser());
        return new ResponseEntity<>(user.toUserDTO(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/block")
    public ResponseEntity<?> doUpdateBlock(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        userDTO.setStatus("Block");
        User user = userService.saveWithOutPassword(userDTO.toUser());
        return new ResponseEntity<>(user.toUserDTO(), HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doDelete(@PathVariable Long id) {
        Optional<User> userDTO = userService.findByUserIdUser(id);
        if (userDTO.isPresent()) {
            userService.deleteSoft(userDTO.get().toUserDTO().toUser());

            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete False", HttpStatus.NO_CONTENT);
    }

}

