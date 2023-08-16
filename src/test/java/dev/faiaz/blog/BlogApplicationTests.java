package dev.faiaz.blog;

import dev.faiaz.blog.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest(){
		//Using java reflection Api
		String name = userRepository.getClass().getName();
		String packName = userRepository.getClass().getPackageName();
		System.out.println(name);
		System.out.println(packName);

	}
}
