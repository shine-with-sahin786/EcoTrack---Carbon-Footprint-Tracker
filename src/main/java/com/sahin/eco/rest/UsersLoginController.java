package com.sahin.eco.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.UsersDTO;
import com.sahin.eco.dto.UsersDTO.UserSessionResponse;
import com.sahin.eco.dto.UsersLoginDTO;
import com.sahin.eco.service.UsersLoginService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;

@RestController
@Validated
@RequestMapping("/users")
public class UsersLoginController
{
    @Autowired
    private UsersLoginService usserv;
    
    // Login API
    @PostMapping("/login/{email}")
	public ResponseEntity<String> login(@PathVariable @NotBlank(message="Email must be filled out") String email,
			@RequestBody UsersLoginDTO uslogdto,
			HttpSession ses)
	{
		UsersDTO usdto = usserv.login(email,uslogdto);
		if(usdto!=null)
		{
			
			ses.setAttribute("email", email);
			ses.setAttribute("name", usdto.getName());
			ses.setAttribute("address", usdto.getAddress());
			ses.setAttribute("gender", usdto.getGender());
			return ResponseEntity.ok("Login Success");
		}
		else
		{
			return ResponseEntity.status(404).body("Not Found");
		}
	}
    // Control All Session
 	@GetMapping("/login_session")
 	public ResponseEntity<?> allSession(HttpSession session)
 	{
 		String email = (String) session.getAttribute("email");
 		String name = (String) session.getAttribute("name");
 		String address = (String) session.getAttribute("address");
 		String gender = (String) session.getAttribute("gender");
 		
 		if (email != null || name != null || address != null || gender != null)
 		{
 			UserSessionResponse response = new UserSessionResponse(name, email, address, gender);
 	        return ResponseEntity.ok(response);
 		}
 		else
 		{
 			return ResponseEntity.status(404).body("Not Found");
 		}
 	}
 	
 	//✅ Added Logout API
	@PostMapping("/user_logout")
	public ResponseEntity<String> logout(HttpSession session) 
	{
		session.invalidate(); // Destroy session
		return ResponseEntity.ok("Logout Success");
	}
}
