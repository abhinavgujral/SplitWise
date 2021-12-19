package com.masai.app.book_review;

import com.masai.app.book_review.entity.FriendCircle;
import com.masai.app.book_review.entity.User;
import com.masai.app.book_review.repository.FriendCircleRepository;
import com.masai.app.book_review.repository.UserRepository;
import com.masai.app.book_review.service.FriendCircleService;
import com.masai.app.book_review.service.TransactionHistoryService;
import com.masai.app.book_review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Splitwise implements CommandLineRunner
{
	@Autowired
	UserService userService;

	@Autowired
	FriendCircleService friendCircleService;

	@Autowired
	FriendCircleRepository friendCircleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TransactionHistoryService transactionHistoryService;

	public static void main(String[] args) {
		SpringApplication.run(Splitwise.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
	

		User user1 = new User("Rahul123", "Rahul", "Rahul@xyz.com", "Pass@1234", null);
		User user2 = new User("Bagul123", "Bagul", "Rahul@xyz.com", "Pass@1234", null);

		FriendCircle friendCircle1 = new FriendCircle(null, "Rohan123", "Rahul123", true, true, 100, null);
		FriendCircle friendCircle3 = new FriendCircle(null, "Rohit123", "Rahul123", true, true, 1000, null);
		FriendCircle friendCircle2 = new FriendCircle(null, "Rohan123", "Bagul123", true, true, 200, null);
		FriendCircle friendCircle4 = new FriendCircle(null, "Rahul123", "Bagul123", true, true, 5000, null);

		userService.addUser(user1);
		userService.addUser(user2);

		friendCircleService.addFriendCircle(friendCircle1);
		friendCircleService.addFriendCircle(friendCircle2);
		friendCircleService.addFriendCircle(friendCircle3);
		friendCircleService.addFriendCircle(friendCircle4);


//		userRepository.save(user1);
//		userRepository.save(user2);
//
//		friendCircleRepository.save(friendCircle1);
//		friendCircleRepository.save(friendCircle2);
//		friendCircleRepository.save(friendCircle3);
//		friendCircleRepository.save(friendCircle4);
//
//		friendCircleService.addSingleFriendCircleForUserByIds("Rahul123","Rohan123");
//		friendCircleService.addSingleFriendCircleForUserByIds("Rahul123","Rohit123");
//		friendCircleService.addSingleFriendCircleForUserByIds("Bagul123","Rohan123");
//		friendCircleService.addSingleFriendCircleForUserByIds("Bagul123","Rahul123");

		System.out.println();
//		friendCircleService.test();

		String msg11 = friendCircleService.modifyFriendCircleByUserId("Bagul123","Rahul123", 30);
		System.out.println("T12 => "+msg11);
		String msg12 = friendCircleService.modifyFriendCircleByUserId("Bagul123","Rahul123", 15);
		System.out.println("T12 => "+msg12);
		String msg1 = friendCircleService.modifyFriendCircleByUserId("Rahul123","Rohan123", 30);
		System.out.println("T1 => "+msg1);
		String msg2 = friendCircleService.modifyFriendCircleByUserId("Rahul123","Rohan123", 20);
		System.out.println("T2 => "+msg2);
		String msg3 = friendCircleService.modifyFriendCircleByUserId("Rahul123","Rohan123", 5);
		System.out.println("T3 => "+msg3);
		String msg4 = friendCircleService.modifyFriendCircleByUserId("Rahul123","Rohit123", 5);
		System.out.println("T4 => "+msg4);
		String msg5 = friendCircleService.modifyFriendCircleByUserId("Rahul123","Rohit123", 5);
		System.out.println("T5 => "+msg5);
		String msg6 = friendCircleService.modifyFriendCircleByUserId("Bagul123","Rohan123", 30);
		System.out.println("T6 => "+msg6);

		System.out.println("==> Entire Transaction History of Rahul");
		String entireHistory = transactionHistoryService.getFullTransactionHistoryByUserId("Rahul123");
		System.out.println(entireHistory);

		System.out.println("==> Only Transaction Between Rahul and Rohit");
		String betweenHistory1 = transactionHistoryService.getTransactionHistoryAmongTwoUsers("Rahul123", "Rohit123");
		System.out.println(betweenHistory1);

		System.out.println("==> Only Transaction Between Rahul and Bagul");
		String betweenHistory2 = transactionHistoryService.getTransactionHistoryAmongTwoUsers("Rahul123", "Bagul123");
		System.out.println(betweenHistory2);

//		friendCircleService.test();

		System.out.println();
		System.out.println(" ==> List of Payees for Rohan123 ");
		String listPayees= friendCircleService.getListOfPayees("Rohan123");
		System.out.println(listPayees);

		System.out.println();
		System.out.println(" ==> List of Payors for Rahul123");
		List<FriendCircle> listPayors = friendCircleService.getListOfPayors("Rahul123");
		System.out.println("==> From CommandlineRunner ");
		System.out.println(listPayors);

//		String addcontribution = friendCircleService.addcontribution(100, 5, 'E', { new Pair("A",
//				60), new Pair("B", 20), new Pair("C", 15), new Pair("D", 5),
//				new Pair("E", 0) });
//	      System.out.println(addcontribution);

		//System.out.println(" pair array=>"+ new PairArray().getPairList());






	}
}
