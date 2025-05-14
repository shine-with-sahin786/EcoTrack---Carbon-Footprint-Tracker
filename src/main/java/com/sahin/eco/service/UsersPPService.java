package com.sahin.eco.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sahin.eco.dto.UsersDTO;
import com.sahin.eco.dto.UsersImageDTO;
import com.sahin.eco.entity.Users;
import com.sahin.eco.entity.UsersImage;
import com.sahin.eco.repository.UsersDAO;
import com.sahin.eco.repository.UsersImageDAO;

import jakarta.transaction.Transactional;

@Service
public class UsersPPService
{
    @Autowired
    private UsersImageDAO imgdao;
    
    @Autowired
    private UsersDAO usdao;
    
    // For Upload
    @Transactional
    public String uploadImage(String email, MultipartFile imageFile)
    {
        boolean exists = imgdao.existsById(email);

        String originalFilename = imageFile.getOriginalFilename();
        if (originalFilename == null || !originalFilename.matches(".*\\.(png|jpg|jpeg|gif|webp)$"))
        {
            return "Invalid file type. Only image files are allowed.";
        }

        try {
            Path uploadDir = Paths.get(System.getProperty("user.dir"),
                    "src", "main", "resources", "static", "uploads");
            Files.createDirectories(uploadDir);

            Path filePath = uploadDir.resolve(originalFilename);
            imageFile.transferTo(filePath.toFile());

            String imagePath = "/uploads/" + originalFilename;

            // ✅ Create and save UsersImage
            UsersImage img = new UsersImage();
            img.setEmail(email);
            img.setImageUrl(imagePath);
            imgdao.save(img); // save first, optional due to cascade

            // ✅ Fetch User and link image
            Users user = usdao.findById(email).orElse(null);
            if (user != null)
            {
                user.setUs_img(img);  // Setting image reference in User
                usdao.save(user);     // Saving User updates owning table
            }

            return exists ? "Image Updated" : "Image Uploaded";

        } catch (Exception e)
        {
            e.printStackTrace();
            return "Image upload failed: " + e.getMessage();
        }
    }
    // For Deleting PP
    @Transactional
    public String deleteImage(String email)
    {
        UsersImage img = imgdao.findById(email).orElse(null);
        if (img == null) {
            return "No image found for given email.";
        }

        try {
            // ✅ Unlink image from owning User entity first
            Users user = usdao.findById(email).orElse(null);
            if (user != null) {
                user.setUs_img(null);  // Break the association
                usdao.save(user);      // Persist changes
            }

            // 🧼 Delete physical image file
            String imageUrl = img.getImageUrl(); // e.g., "/uploads/xyz.jpg"
            String fileName = Paths.get(imageUrl).getFileName().toString();
            Path filePath = Paths.get(System.getProperty("user.dir"),
                                      "src", "main", "resources", "static", "uploads", fileName);
            Files.deleteIfExists(filePath);

            // 🗑️ Delete DB record from UsersImage table
            imgdao.deleteById(email);

            return "Image deleted successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Image deletion failed: " + e.getMessage();
        }
    }
    // For View/Download
    public UsersImageDTO viewImage(String email)
    {
        UsersImage img = imgdao.findById(email).orElse(null);
        if (img != null)
        {
            UsersImageDTO dto = new UsersImageDTO();
            dto.setImageUrl(img.getImageUrl());
            return dto;
        }
        return null;
    }
    //For updating profile
    @Transactional
  	public UsersDTO updateProfile(String email, String name, String address)
  	{
  		// Finding student
  		Users users = usdao.findById(email).orElse(null);
  		if(users!=null)
  		{
  			// Updating
  			users.setName(name);
  			users.setAddress(address);
  			usdao.save(users);
  			// Setting new DTO
  			UsersDTO ob = new UsersDTO();
			ob.setName(users.getName());
			ob.setEmail(users.getEmail());
			ob.setBirthday(users.getBirthday());
			ob.setAddress(users.getAddress());
			ob.setGender(users.getGender());
  			return ob;
  		}
  		else
  		{
  			return null;
  		}
  	}
}
