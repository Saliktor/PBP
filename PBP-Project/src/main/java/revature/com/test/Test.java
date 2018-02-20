package revature.com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.revature.beans.UserAccount;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;


public class Test {
	private static UserDAO uDAO = new UserDAOImp();
	//private static UserService uService = new UserServiceImp();

	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	

	private UserService uService = ac.getBean(UserService.class);
	
	public UserAccount getUser() {
		return uService.getUser("user1", "password");
	}

	public static void main(String[] args) {
		Test t = new Test();
		
		UserAccount user = t.getUser();
		System.out.println(user);

	
}
}