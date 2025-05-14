package com.sahin.eco.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahin.eco.dto.UsersActivityDTO;
import com.sahin.eco.service.UsersActivityService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UsersActivityController
{
	@Autowired
	private UsersActivityService actserv;
	
	// For Adding Activities
	@PostMapping("/add_activity")
	public ResponseEntity <String> creating(@Valid @RequestBody UsersActivityDTO actdto, HttpSession ses)
	{
		String email = (String) ses.getAttribute("email");
		actserv.creating(actdto,email);
		return ResponseEntity.ok("Eco activity added.");
	}
	
	// For Showing Activity Logs
	@GetMapping("/log_activities")
	public ResponseEntity<List<UsersActivityDTO>> logActivities(HttpSession ses)
	{
	    String email = (String) ses.getAttribute("email");
	    if (email == null)
	    {
	        return ResponseEntity.badRequest().build(); // Better to return 400 with no body
	    }

	    List<UsersActivityDTO> activities = actserv.logActivities(email);
	    return ResponseEntity.ok(activities); // Will return 200 with the list of DTOs
	}
	// For showing leaderboard
	@GetMapping("/leaderboard")
    public ResponseEntity<List<UsersActivityDTO>> getLeaderboard() {
        List<UsersActivityDTO> leaderboard = actserv.getLeaderboard();
        return ResponseEntity.ok(leaderboard);
    }
	// For Showing All Users Report to Admin
	@GetMapping("/all_log_activities")
    public List<UsersActivityDTO> getAllUserActivities()
	{
        return actserv.getAllActivities(); // Fetch all activity logs
    }
}
