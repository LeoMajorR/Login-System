package net.codejava;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * @param email
	 * @return
	 */
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);

	// check if user exists with given email, name, age, profession, mobileNumber
	/**
	 * @param name
	 * @param age
	 * @param profession
	 * @param email
	 * @param mobileNumber
	 * @return
	 */
	@Query("SELECT u FROM User u WHERE (?1 is null or u.firstName LIKE ?1) AND (?2 is null or u.age = ?2) AND (?3 is null or u.occupation LIKE ?3) AND (?4 is null or u.email LIKE ?4) AND (?5 is null or u.mobileNumber LIKE ?5)")
	public List<User> customQuery(String name, Integer age, String profession, String email, String mobileNumber);

}