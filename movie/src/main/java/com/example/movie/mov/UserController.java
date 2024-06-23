//package com.example.movie.mov;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(path="/api")
//public class UserController {
//    private final UserService userService;
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping(value="/login")
//    public ResponseEntity<String> toCheckLoginCredentials(@RequestParam("username")String username,@RequestParam("password")String password){
//        boolean credentialMatches=userService.checkIfUserExists(username,password);
//        if(credentialMatches)
//        {
//            return ResponseEntity.ok("Login Success!");
//
//        }
//        return ResponseEntity.ok("Login Credentials Does Not Match!!!");
//
//    }
//
//}
package com.example.movie.mov;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path="/api/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/login")
    public ResponseEntity<String> toCheckLoginCredentials(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean credentialMatches = userService.checkIfUserExists(username, password);
        if(credentialMatches)
        {
            return ResponseEntity.ok("Login Success!");

        }
        return ResponseEntity.ok("Login Credentials Does Not Match!!!");
    }
    @PostMapping(value="/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String result = userService.registerUser(user);
        return ResponseEntity.ok(result);
    }


}
