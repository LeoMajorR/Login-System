package net.codejava;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	// @Autowired
	// private TestEntityManager entityManager;

	@Autowired
	private UserRepository repo;

	// @Test
	// public void testCreateUser() {
	// User user = new User();
	// user.setEmail("test@gmail.com");
	// user.setPassword("test2020");
	// user.setFirstName("Test");
	// user.setLastName("User");
	// user.setMobileNumber("1234567890");
	// user.setOccupation("Software Engineer");
	// user.setAge(25);

	// User savedUser = repo.save(user);

	// User existUser = entityManager.find(User.class, savedUser.getId());

	// assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

	// }

	@Test
	public void testCustomQuery() {
		String name1 = "Ravi";
		Integer age1 = 21;
		String profession1 = "Intern";
		String email1 = "ravi.singh@gmail.com";
		String mobileNumber1 = "7309814157";

		String name2 = "Alia";
		Integer age2 = 29;
		String profession2 = "Actor";
		String email2 = "aaliabhat@gmail.com";
		String mobileNumber2 = "8723652894";

		String name3 = null;
		Integer age3 = 21;
		String profession3 = null;
		String email3 = null;
		String mobileNumber3 = null;

		String name4 = "Ravi";
		Integer age4 = 0;
		String profession4 = "Intern";
		String email4 = null;
		String mobileNumber4 = "7309814157";

		List<User> users = repo.customQuery(name1, age1, profession1, email1, mobileNumber1);
		List<User> users2 = repo.customQuery(name2, age2, profession2, email2, mobileNumber2);
		List<User> user3 = repo.customQuery(name3, age3, profession3, email3, mobileNumber3);
		List<User> user4 = repo.customQuery(name4, age4, profession4, email4, mobileNumber4);

		assertThat(users.size()).isEqualTo(1);
		assertThat(users2.size()).isEqualTo(1);
		assertThat(user3.size()).isGreaterThan(1);
		assertThat(user4.size()).isGreaterThan(1);
	}
}
