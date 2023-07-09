package github.com.liukaku.javaQuiz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;

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
	public ArrayList<QuizTable> getAll(){
		Dotenv dotenv = Dotenv.load();
		String MYSQL_URL= dotenv.get("MYSQL_URL");
		String MYSQL_NAME= dotenv.get("MYSQL_NAME");
		String MYSQL_PWD= dotenv.get("MYSQL_PWD");
		ObjectMapper mapper = new ObjectMapper();

		ArrayList<QuizTable> res = new ArrayList<QuizTable>();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_NAME, MYSQL_PWD);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Quiz");
			int columnCount = resultSet.getMetaData().getColumnCount();

			while(resultSet.next()){

				ArrayList<String> rowArr = new ArrayList<String>();
				for(int i = 1; i <= columnCount; i++){
					rowArr.add(resultSet.getString(i));
				}
				QuizTable quiz = new QuizTable(rowArr);
				res.add(quiz);
			}

			return res;
		} catch (Exception e){
			System.out.println(e);
			return res;
	}
	}

	public static QuizTable getObjData(QuizTable quiz, ArrayList<String> data){
		String id = data.get(0);
		String setQuizTitle = data.get(1);
		String setOwnerId = data.get(2);
		quiz.setId("id");
		quiz.setQuizTitle("setQuizTitle");
		quiz.setOwnerId("setOwnerId");
		return quiz;

	}

}
