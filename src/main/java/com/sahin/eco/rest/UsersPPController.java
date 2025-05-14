package com.sahin.eco.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sahin.eco.dto.UsersDTO;
import com.sahin.eco.dto.UsersImageDTO;
import com.sahin.eco.service.UsersPPService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UsersPPController
{

    @Autowired
    private UsersPPService ppserv;
    
    // For Uploading PP
    @PostMapping("/pp_upload")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile imageFile, HttpSession ses)
    {
        String email = (String) ses.getAttribute("email");
    	String msg = ppserv.uploadImage(email, imageFile);
        return ResponseEntity.ok(msg);
    }
    
    // For Viewing PP
    @GetMapping("/view_pp")
    public ResponseEntity<?> viewImage(HttpSession ses)
    {
        String email = (String) ses.getAttribute("email");
        UsersImageDTO dto = ppserv.viewImage(email);

        if (dto == null)
        {
            return ResponseEntity.badRequest().body("No image found for this user.");
        }
        return ResponseEntity.ok(dto);
    }
    
    // For Updating User's Details
    @PostMapping("/update_profile")
	public ResponseEntity<String> updateProfile
	(@RequestParam String name, @RequestParam String address, HttpSession session)
	{
		String email = (String) session.getAttribute("email");
		UsersDTO usdto = ppserv.updateProfile(email,name,address);
		if(usdto!=null)
		{
			// ✅ Add this block to update session attributes immediately
	        session.setAttribute("name", usdto.getName());
	        session.setAttribute("address", usdto.getAddress());
			return ResponseEntity.ok("Profile Updated");
		}
		else
		{
			return ResponseEntity.status(404).body("Not Found");
		}
	}
 // For Deleting PP
    @DeleteMapping("/delete_pp")
    public ResponseEntity<String> deleteProfilePicture(HttpSession session)
    {
        String email = (String) session.getAttribute("email");
        String msg = ppserv.deleteImage(email);
        return ResponseEntity.ok(msg);
    }
}
