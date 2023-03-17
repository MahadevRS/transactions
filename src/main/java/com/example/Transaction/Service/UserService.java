package com.example.Transaction.Service;

import com.example.Transaction.Repository.RefundRepository;
import com.example.Transaction.Repository.TransactionRepository;
import com.example.Transaction.Repository.UserRepository;
import com.example.Transaction.TransactionStatus;
import com.example.Transaction.models.Refund;
import com.example.Transaction.models.Transaction;
import com.example.Transaction.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    RefundRepository refundRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void addTransaction(int amount, int userId) {
        Transaction transaction=new Transaction();
        User user=userRepository.findById(userId).get();
        transaction.setAmount(amount);
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction.setAmountDeducted(amount);
        transaction.setUser(user);

        transaction=transactionRepository.save(transaction);
        user.getTransactionList().add(transaction);
        userRepository.save(user);
    }

    public int successfulTransactionAmount(int userId) {
        User user=userRepository.findById(userId).get();

        List<Transaction> transactionList=user.getTransactionList();

        int amount =0;

        for(Transaction transaction:transactionList){

             if(transaction.getStatus().equals(TransactionStatus.SUCCESS)){
                 amount+=transaction.getAmount();
             }
        }

        return amount;
    }

    public void deleteFailedTransaction() {

        List<Transaction> transactionList=transactionRepository.findAll();

        List<Transaction> failedTransaction=new ArrayList<>();

        for(Transaction transaction:transactionList){

            if (transaction.getStatus().equals(TransactionStatus.FAILED)) {

                failedTransaction.add(transaction);
            }
        }

        transactionRepository.deleteAll(failedTransaction);
    }

    public int userWithMaxRefund() {

        List<User> userList=userRepository.findAll();

        int max=Integer.MIN_VALUE;
        int userId=0;
        int refundAmount=0;

        for(User user:userList){

            List<Refund> refundList=user.getRefundList();
            refundAmount=0;

            for(Refund refund:refundList){
                refundAmount+=refund.getAmount();
            }

            if(max<refundAmount){
                userId=user.getId();
                max=refundAmount;
            }

        }

        return userId;
    }
}
