package com.daurada.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daurada.base.BasicController;
import com.daurada.dir.DIR;
import com.daurada.http.HttpResponse;

@RestController
//@RequestMapping(DIR.USER)
public class User_Controller extends BasicController<User> {

   // @Autowired
  //  private Security_Service securityService;

    @Autowired
    private User_Validator userValidator;
    
    @GetMapping(DIR.SIGN_UP)
    public String registration() {
        return "registration";
    }

    @PostMapping(DIR.SIGN_UP)
    public ResponseEntity<?> registration(@RequestBody User user, BindingResult result) {
    	
    	userValidator.validate(user, result);

        if (result.hasErrors()) 
            return HttpResponse.bad(
            		result.getFieldError().getField(), 
            		result.getFieldError().getDefaultMessage());

        if (getService().insert(user))
        	//securityService.autoLogin(user);
            return HttpResponse.created("Sign up successful.");
        
        else return ResponseEntity.noContent().build();
    }
    
    @GetMapping(DIR.SIGN_IN)
    public String login() {
        return "sign in";
    }
    
    @GetMapping(DIR.AUTH_ERROR)
    public String error() {
    	return "Sign in error";
    }

    @GetMapping(DIR.HOME)
    public String welcome() {
        return "Welcome home! (authenticated)";
    }
}
