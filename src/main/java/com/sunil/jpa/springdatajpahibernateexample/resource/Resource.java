package com.sunil.jpa.springdatajpahibernateexample.resource;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/springboot")
public class Resource {

    private UserRepository usersRepository;

    private Users users;

    public Resource(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @GetMapping("/getMessage/{id}")
    public Users getMessage(@PathVariable("id") String id) {

        try {
            String messageId = id;
            if (!id.isEmpty()) {
                return usersRepository.findOne(Integer.parseInt(messageId));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // TODO : This is not a proper validation just for testing added this code
            users = new Users();
            users.setMessage("ERROR WHILE PROCESSING THE REQUEST. PLEASE CHECK THE REQUEST. ID SHOULD NOT BE NULL OR EMPTY OR ID NOT FOUND IN DATABASE");
        }
        return users;
    }

    @PostMapping("/add/message")
    public String addMesssage(@RequestBody Map<String, String> messageinfo) {
        try {
            Users users = new Users();
            users.setMessage(messageinfo.get("message"));
            usersRepository.saveAndFlush(users);
            return "SUCCESSFULLY STORED INFO TO THE DATABASE";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "FAILED TO STORE THE INFORMATION TO DATABASE";
        }
    }
}
