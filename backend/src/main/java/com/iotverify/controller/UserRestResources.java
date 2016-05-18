package com.iotverify.controller;

import com.iotverify.model.User;
import com.iotverify.service.DeviceService;
import com.iotverify.service.LogEventService;
import com.iotverify.service.PhoneService;
import com.iotverify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Le on 4/11/2016.
 */
@RestController()
@RequestMapping("/rest/user")
public class UserRestResources {

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private LogEventService logEventService;

    @Autowired
    private PhoneService phoneService;

    @RequestMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value="/userId", method = RequestMethod.POST)
    public User findByUserId(@RequestBody String userId) {
        return userService.findByUserId(Long.parseLong(userId));
    }


    @RequestMapping(value="/delete/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable(value = "userId") String userId) {
        deviceService.deleteByDeviceCompUdid(userId);
        logEventService.deleteByUserId(Long.parseLong(userId));
        phoneService.deleteByUserId(Long.parseLong(userId));
        userService.delete(Long.parseLong(userId));

        return "delete success.";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {

        return  userService.save(user);

    }
}
