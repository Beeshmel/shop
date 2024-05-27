package my.web.app.shop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import my.web.app.shop.models.BaseModel;
import my.web.app.shop.models.User;
import my.web.app.shop.servies.UserServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserServies userServies;

    @Autowired
    public UserController(UserServies userServies) {
        this.userServies = userServies;
    }




    @PostMapping(value = "/registration")
    public ResponseEntity<?> create(@RequestBody User user, HttpServletRequest request) {
        try {
            user.setTocken(BaseModel.getNewTocken(user.getLogin()));
            userServies.create(user);
            HttpSession session = request.getSession();
            session.setAttribute("tocken", user.getTocken());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/avtorisation/{tocken}")
    public ResponseEntity<User> read(@PathVariable(name = "tocken") String tocken, HttpServletRequest request){
        User user = userServies.read(tocken);
        HttpSession session = request.getSession();
        session.setAttribute("tocken", user.getTocken());
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
