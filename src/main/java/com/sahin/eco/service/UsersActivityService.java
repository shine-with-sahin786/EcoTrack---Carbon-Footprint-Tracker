package com.sahin.eco.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahin.eco.dto.UsersActivityDTO;
import com.sahin.eco.entity.Users;
import com.sahin.eco.entity.UsersActivity;
import com.sahin.eco.entity.UsersImage;
import com.sahin.eco.repository.UsersActivityDAO;
import com.sahin.eco.repository.UsersDAO;
import com.sahin.eco.repository.UsersImageDAO;

import jakarta.transaction.Transactional;

@Service
public class UsersActivityService
{
	@Autowired
	private UsersActivityDAO actdao;
	
	@Autowired
	private UsersDAO usdao;
	
	@Autowired
    private UsersImageDAO usersImageDAO; // Add the UsersImageDAO here to access UsersImage data
	
	// For Adding Activities
	@Transactional
	public void creating(UsersActivityDTO actdto, String email)
	{
		UsersActivity ob = new UsersActivity();
		ob.setType(actdto.getType());
		ob.setDescription(actdto.getDescription());
		ob.setPoints(2);
		ob.setWhen(actdto.getWhen());
		ob.setLocation(actdto.getLocation());
		// ✅ Fetch User and link activity
        Users user = usdao.findById(email).orElse(null);
        if (user != null)
        {
            ob.setUsers(user);
            actdao.save(ob); // saving UsersActivity entity here
        }
	}
	// For Showing Activity Logs
	public List<UsersActivityDTO> logActivities(String email)
	{
	    List<UsersActivity> entityList = actdao.findByUsersEmail(email);
	    
	    return entityList.stream().map(entity -> {
	        UsersActivityDTO dto = new UsersActivityDTO();
	        dto.setType(entity.getType());
	        dto.setDescription(entity.getDescription());
	        dto.setWhen(entity.getWhen());
	        dto.setLocation(entity.getLocation());
	        dto.setPoints(entity.getPoints());
	        // no recursion here
	        return dto;
	    }).toList();
	}
	// New method to get leaderboard based on total points
    public List<UsersActivityDTO> getLeaderboard() {
        // Fetch all users' activities
        List<UsersActivity> activities = actdao.findAll();
        
        // Aggregate total points per user
        Map<Users, Integer> userPointsMap = activities.stream()
            .collect(Collectors.groupingBy(
                UsersActivity::getUsers, // Group by user
                Collectors.summingInt(UsersActivity::getPoints) // Sum points for each user
            ));
        
        // Sort users by total points in descending order
        return userPointsMap.entrySet().stream()
            .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue())) // Sort by points
            .map(entry -> {
                UsersActivityDTO dto = new UsersActivityDTO();
                dto.setType("Total Points");
                dto.setDescription("User points ranking");
                dto.setPoints(entry.getValue());
                dto.setWhen(LocalDate.now()); // You can set this to a different value if needed
                dto.setLocation("N/A"); // You can set this accordingl
                dto.setEmail(entry.getKey().getEmail());
                
             // Fetch the image URL from the UsersImage table
                String imageUrl = getProfileImageUrl(entry.getKey().getEmail()); // Fetch the image URL using email
                dto.setProfileImageUrl(imageUrl);

                
                return dto;
            })
            .collect(Collectors.toList());
    }
    // Fetch image URL from UsersImage table based on email
    private String getProfileImageUrl(String email) {
        UsersImage usersImage = usersImageDAO.findById(email).orElse(null);
        if (usersImage != null) {
            return usersImage.getImageUrl(); // Return the image URL if found
        }
        return "/images/nodp.jpg"; // Return a default image URL if not found
    }
    // For admin to show Reports
    public List<UsersActivityDTO> getAllActivities() {
        List<UsersActivity> allActivities = actdao.findAll();

        return allActivities.stream().map(entity -> {
            UsersActivityDTO dto = new UsersActivityDTO();
            dto.setType(entity.getType());
            dto.setDescription(entity.getDescription());
            dto.setWhen(entity.getWhen());
            dto.setLocation(entity.getLocation());
            dto.setPoints(entity.getPoints());
            dto.setEmail(entity.getUsers().getEmail());

            // Optional: include profile image if needed
            String imageUrl = getProfileImageUrl(entity.getUsers().getEmail());
            dto.setProfileImageUrl(imageUrl);

            return dto;
        }).toList();
    }
}
