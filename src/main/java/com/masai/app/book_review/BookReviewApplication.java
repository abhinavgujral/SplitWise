package com.masai.app.book_review;

import com.masai.app.book_review.DTO.PairArray;
import com.masai.app.book_review.entity.FriendCircle;
import com.masai.app.book_review.entity.User;
import com.masai.app.book_review.repository.FriendCircleRepository;
import com.masai.app.book_review.repository.UserRepository;
import com.masai.app.book_review.service.FriendCircleService;
import com.masai.app.book_review.service.UserService;
import javassist.compiler.ast.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootApplication
public class BookReviewApplication implements CommandLineRunner
{
	@Autowired
	UserService userService;

	@Autowired
	FriendCircleService friendCircleService;

	@Autowired
	FriendCircleRepository friendCircleRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookReviewApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		User user1 = new User("Rahul123", "Rahul", "Rahul@xyz.com", "Pass@1234", null);
		User user2 = new User("Bagul123", "Bagul", "Rahul@xyz.com", "Pass@1234", null);

		FriendCircle friendCircle1 = new FriendCircle(null, "Rohan123", "Rahul123", true, true, 100, null);
		FriendCircle friendCircle2 = new FriendCircle(null, "Rohan123", "Bagul123", true, true, 200, null);

		userRepository.save(user1);
		userRepository.save(user2);

		friendCircleRepository.save(friendCircle1);
		friendCircleRepository.save(friendCircle2);

		friendCircleService.addSingleFriendCircleForUserByIds("Rahul123","Rohan123");
		friendCircleService.addSingleFriendCircleForUserByIds("Bagul123","Rohan123");

		friendCircleService.test();

		String msg1 = friendCircleService.modifyFriendCircleByUserId("Rahul123","Rohan123", 30);
		System.out.println("T1 => "+msg1);
		String msg2 = friendCircleService.modifyFriendCircleByUserId("Bagul123","Rohan123", 30);
		System.out.println("T2 => "+msg2);

		friendCircleService.test();

		String listPayees= friendCircleService.getListOfPayees("Rohan123");
		System.out.println(listPayees);

//		String addcontribution = friendCircleService.addcontribution(100, 5, 'E', { new Pair("A",
//				60), new Pair("B", 20), new Pair("C", 15), new Pair("D", 5),
//				new Pair("E", 0) });
//	      System.out.println(addcontribution);

		System.out.println(" pair array=>"+ new PairArray().getPairList());
	}
}
