package fr.epita.quiz.services.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.quiz.datamodel.Question;

public class QuestionDAO {
	
	public void create(/*what?*/) {
		
	}
	public List<Question> search(/*what?*/) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/2020s1-quiz-database", "postgres", "");
		System.out.println(connection.getSchema());
		String query = "select question,difficulty from \"QUESTIONS\"";
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet results = prepareStatement.executeQuery();
		List<Question> questions = new ArrayList<>();
		while (results.next()) {
			String question = results.getString("question");
			int difficulty = results.getInt("difficulty");
			questions.add(new Question(question, difficulty));
			System.out.println("question : " + question + " with difficulty :" + difficulty);
		}
		
		connection.close();
        System.out.println("questions " + questions);
		return questions; //TODO complete;
		
	}

}
