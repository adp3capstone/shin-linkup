package za.ac.cput.linkup.controller;

import za.ac.cput.linkup.domain.Preference;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.domain.enums.Course;
import za.ac.cput.linkup.domain.enums.Gender;
import za.ac.cput.linkup.domain.enums.Institution;
import za.ac.cput.linkup.domain.enums.Interest;
import za.ac.cput.linkup.factory.UserFactory;
import za.ac.cput.linkup.service.ImageService;
import za.ac.cput.linkup.service.PreferenceService;
import za.ac.cput.linkup.service.UserService;
import za.ac.cput.linkup.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {
    private final UserService userService;
    private final PreferenceService preferenceService;
    private final ImageService imageService;

    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, PreferenceService preferenceService, ImageService imageService,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.preferenceService = preferenceService;
        this.imageService = imageService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        User foundUser = userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (foundUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

//        String token = jwtUtil.generateToken(request.getUsername());

        String token = foundUser.getUserId().toString();
        UserDTO userDTO = new UserDTO(foundUser);

        return ResponseEntity.ok(new AuthResponse(token, userDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody SignUpRequest user) {
        User buildUser = UserFactory.createUserForSignup(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getAge(),
                user.getBio(),
                user.getInstitution(),
                user.getGender(),
                user.getCourse(),
                user.getInterests(),
                user.isSmoker(),
                user.isDrinker(),
                user.getHeight(),
                user.getOrientation()
        );
        if(buildUser==null){
            return ResponseEntity.badRequest().body("Not all fields were completed correctly. Please try signing up again.");
        }
        User createdUser = userService.create(buildUser);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }



    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long userId) {
        User user = userService.read(userId);

        if (user != null) {
            UserDTO userDTO = new UserDTO(user);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PatchMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUserFields(
            @PathVariable Long userId,
            @RequestBody Map<String, Object> fields) {

        // Load the existing user
        User user = userService.read(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Update fields dynamically
        fields.forEach((key, value) -> {
            try {
                Field field = ReflectionUtils.findField(User.class, key);
                if (field != null) {
                    field.setAccessible(true);

                    // Handle type conversion for primitive fields
                    Class<?> fieldType = field.getType();
                    Object convertedValue = value;
                    if (fieldType == int.class) {
                        convertedValue = ((Number) value).intValue();
                    } else if (fieldType == long.class) {
                        convertedValue = ((Number) value).longValue();
                    } else if (fieldType == double.class) {
                        convertedValue = ((Number) value).doubleValue();
                    } else if (fieldType == boolean.class) {
                        convertedValue = Boolean.valueOf(value.toString());
                    } else if (fieldType.isEnum()) {
                        convertedValue = Enum.valueOf((Class<Enum>) fieldType, value.toString());
                    }

                    ReflectionUtils.setField(field, user, convertedValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Save the updated user
        User updatedUser = userService.update(user);
        UserDTO dto = new UserDTO(updatedUser);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteImage(@RequestParam long userId) {
        userService.delete(userId);
        if (userService.read(userId) != null) {
            return ResponseEntity
                    .status(409)
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }

    //Users Filters for feed:
    @GetMapping("/by-course")
    public ResponseEntity<List<UserDTO>> getUsersByCourse(@RequestParam Course course) {
        List<User> users = userService.findAllByCourse(course);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/by-institution")
    public ResponseEntity<List<UserDTO>> getUsersByInstitution(@RequestParam Institution institution) {
        List<User> users = userService.findAllByInstitution(institution);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/by-age")
    public ResponseEntity<List<UserDTO>> getUsersByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        List<User> users = userService.findAllByAgeBetween(minAge, maxAge);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/by-gender")
    public ResponseEntity<List<UserDTO>> getUsersByGender(@RequestParam Gender gender) {
        List<User> users = userService.findAllByGender(gender);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/by-interests")
    public ResponseEntity<List<UserDTO>> getUsersByInterests(@RequestParam List<Interest> interests) {
        List<User> users = userService.findAllByInterestsIn(interests);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }
}
