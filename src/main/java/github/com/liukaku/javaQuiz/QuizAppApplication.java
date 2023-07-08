package github.com.liukaku.javaQuiz;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class QuizAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizAppApplication.class, args);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public AliveStatus aliveStatus(){
		return new AliveStatus(String.format("hello %s", "test"));
	}

	@GetMapping("/getAll")
	public ArrayList<String> getAll(){
		Dotenv dotenv = Dotenv.load();
		String MYSQL_URL= dotenv.get("MYSQL_URL");
		String MYSQL_NAME= dotenv.get("MYSQL_NAME");
		String MYSQL_PWD= dotenv.get("MYSQL_PWD");

		ArrayList<String> res = new ArrayList<String>();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_NAME, MYSQL_PWD);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Quiz");

			while(resultSet.next()){
				res.add(resultSet.getString(2));
				System.out.println(resultSet.getMetaData());
			}
			return res;
		} catch (Exception e){
			System.out.println(e);
			return res;
		}
	}

}
