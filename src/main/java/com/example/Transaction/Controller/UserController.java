package com.example.Transaction.Controller;

import com.example.Transaction.Service.UserService;
import com.example.Transaction.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add_user")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "user added successfully";
    }

    @PostMapping("/add_transaction")
    public String addTransaction(@RequestParam int amount,@RequestParam int userId){
        userService.addTransaction(amount,userId);
        return "transaction added successfully";
    }

    @GetMapping("/successful_transaction_amount")
    public int successfulTransactionAmount(@RequestParam int userId){
        return userService.successfulTransactionAmount(userId);
    }

    @DeleteMapping("/delete_failed_transaction")
    public String deleteFailedTransaction(){
        userService.deleteFailedTransaction();
        return "Successfully deleted failed transactions";
    }

    @GetMapping("/user_with_maximum_refund-amount")
    public int userWithMaxRefund(){
        return  userService.userWithMaxRefund();
    }

}
