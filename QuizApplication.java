package testGUI;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class QuizApplication {
	
	// template for creating quiz questions
	public class Question {

		// stores the categories; only this class can directly access this data
	    private String topic;
	    private String question;
	    private String[] options;
	    private char answer;

	    // constructor 
	    public Question(String topic, String question, String[] options, char answer) {
	    	
	    	// storing the received variables inside this objects variable
	        this.topic = topic;
	        this.question = question;
	        this.options = options;
	        this.answer = answer;
	    }

	    // this makes it possible for other classes to get and read the info in these variables
	    public String getTopic() {
	        return topic;
	    }

	    public String getQuestion() {
	        return question;
	    }

	    public String[] getOptions() {
	        return options;
	    }

	    public char getAnswer() {
	        return answer;
	    }
	}
	
	// creates the QuizLoader class
	public class QuizLoader {

		// method to load the questions of a specific topic from a file and then return them as a list
	    public ArrayList<Question> loadQuestions(String fileName, String topic) {

	    	// creates an empty list where the questions will be stored
	        ArrayList<Question> questions = new ArrayList<>();

	        // try and catch was used as reading a file can fail; it tells the system to be prepared for errors
	        try {

	        	// BR allows the file to be read line by line; FR connects java to the file
	            BufferedReader br = new BufferedReader(new FileReader(fileName));

	            // stores the current line being read
	            String line;

	            // tells the system to keep reading lines until no more lines are left
	            while ((line = br.readLine()) != null) {

	            	// this part splits the information into parts separated by this "|"
	                String[] parts = line.split("\\|");

	                // checks if the question belongs to the selected topic
	                if (parts[0].equals(topic)) {

	                	// creates an array with the four options
	                    String[] options = {
	                        parts[2],
	                        parts[3],
	                        parts[4],
	                        parts[5]
	                    };

	                    // creates a question object with topic, question, options and answer
	                    questions.add(new Question(
	                            parts[0],
	                            parts[1],
	                            options,
	                            parts[6].charAt(0)
	                    ));
	                }
	            }

	            // closes the file after reading
	            br.close();

	            // if there is an error it will be shown here
	        } catch(IOException e) {
	            e.printStackTrace();
	        }

	        // send the questions back
	        return questions;
	    }
	}
	
	// creating the menu class
	public class MenuFrame extends JFrame {
		
		public MenuFrame() {
			
			// GUI components specifications
			setTitle("Java Quiz Application");
			setSize(500, 400);
			setLayout(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JLabel label1 = new JLabel("Welcome to the Java Quiz Application");
			label1.setBounds(20, 20, 300, 25);
			add(label1);
			
			JLabel label2 = new JLabel("Select a Quiz topic:");
			label2.setBounds(20, 50, 200, 25);
			add(label2);
			
			JButton abutton = new JButton("Arrays");
			add(abutton);
			abutton.setBounds(20, 100, 460, 40);
			abutton.addActionListener(e -> {
				
				// creates an object from my QuizLoader class
				QuizLoader loader = new QuizLoader();

				// loads the questions from the quiz file but only those for this specific topic
				ArrayList<Question> questions =
				        loader.loadQuestions("quiz.txt","Arrays");

				// creates a new quiz window with the questions
				new QuizFrame(questions);

				dispose();
			});
			
			JButton lbutton = new JButton("Loops");
			add(lbutton);
			lbutton.setBounds(20, 140, 460, 40);
			lbutton.addActionListener(e -> {
				QuizLoader loader = new QuizLoader();

				ArrayList<Question> questions =
				        loader.loadQuestions("quiz.txt","Loops");

				new QuizFrame(questions);

				dispose();
			});
			
			JButton cbutton = new JButton("Conditional Statements");
			cbutton.setBounds(20, 180, 460, 40);
			add(cbutton);
			cbutton.addActionListener(e -> {
				QuizLoader loader = new QuizLoader();

				ArrayList<Question> questions =
				        loader.loadQuestions("quiz.txt","Conditional Statements");

				new QuizFrame(questions);

				dispose();
			});
			
			JButton fmbutton = new JButton("Functions/Methods");
			fmbutton.setBounds(20, 220, 460, 40);
			add(fmbutton);
			fmbutton.addActionListener(e -> {
				QuizLoader loader = new QuizLoader();

				ArrayList<Question> questions =
				        loader.loadQuestions("quiz.txt","Functions/Methods");

				new QuizFrame(questions);

				dispose();
			});
			
			JButton oobutton = new JButton("Object-oriented Programming basics");
			oobutton.setBounds(20, 260, 460, 40);
			add(oobutton);
			oobutton.addActionListener(e -> {
				QuizLoader loader = new QuizLoader();

				ArrayList<Question> questions =
				        loader.loadQuestions("quiz.txt","Object-oriented Programming basics");

				new QuizFrame(questions);

				dispose();
			});
			
			// exit button that stops the program
			JButton ebutton = new JButton("Exit");
			ebutton.setBounds(20, 300, 460, 40);
			add(ebutton);
			ebutton.addActionListener(e -> {
			    System.exit(0);
			});
			
			setVisible(true);
		}
	}
	
	// creating the quiz class
	class QuizFrame extends JFrame {

		// creates a list that will contain all quiz questions
	    private ArrayList<Question> questions;

	    // this keeps track of the current question and the score
	    private int currentQuestion = 0;
	    private int score = 0;

	    // GUI components for the quiz frame
	    private JLabel questionLabel;

	    private JRadioButton optionA;
	    private JRadioButton optionB;
	    private JRadioButton optionC;
	    private JRadioButton optionD;

	    // groups the button together so that the user can only select one answer
	    private ButtonGroup group;

	    private JButton nextButton;

	    // constructor 
	    public QuizFrame(ArrayList<Question> questions) {

	    	// questions stored in a list before can now be accessed by this constructor
	        this.questions = questions;

	        // GUI component specifications
	        setTitle("Java Quiz");
	        setSize(600,400);
	        setLayout(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        questionLabel = new JLabel();
	        questionLabel.setBounds(20,20,550,30);
	        add(questionLabel);

	        optionA = new JRadioButton();
	        optionA.setBounds(20,70,550,25);

	        optionB = new JRadioButton();
	        optionB.setBounds(20,110,550,25);

	        optionC = new JRadioButton();
	        optionC.setBounds(20,150,550,25);

	        optionD = new JRadioButton();
	        optionD.setBounds(20,190,550,25);

	        // groups all option buttons together
	        group = new ButtonGroup();

	        group.add(optionA);
	        group.add(optionB);
	        group.add(optionC);
	        group.add(optionD);

	        add(optionA);
	        add(optionB);
	        add(optionC);
	        add(optionD);

	        nextButton = new JButton("Next");
	        nextButton.setBounds(220,270,120,40);
	        add(nextButton);

	        // as this is called inside the constructor there is no need to call it in main again
	        displayQuestion();

	        nextButton.addActionListener(e -> checkAnswer());

	        setVisible(true);
	    }

	    private void displayQuestion() {

	    	// gets the current question
	        Question q = questions.get(currentQuestion);

	        // displays the current question by changing the label text accordingly
	        questionLabel.setText("Question " + (currentQuestion+1)
	                + ": " + q.getQuestion());

	        // gets the options that exist for the current question
	        optionA.setText(q.getOptions()[0]);
	        optionB.setText(q.getOptions()[1]);
	        optionC.setText(q.getOptions()[2]);
	        optionD.setText(q.getOptions()[3]);

	        // removes the previous answer selection
	        group.clearSelection();

	    }

	    private void checkAnswer() {
	    	
            // shows an error message if no option is selected
	        if (!optionA.isSelected() && 
	            !optionB.isSelected() && 
	            !optionC.isSelected() && 
	            !optionD.isSelected()) {

	            JOptionPane.showMessageDialog(this, "Please select an answer.");
	            return;
	        }
            
	        // creates a variable to store what the user has selected
	        char answer = ' ';

	        //system stores whatever the user picks as their answer
	        if(optionA.isSelected()) {
	            answer = 'A';
	        } 
	        else if(optionB.isSelected()) {
	            answer = 'B';
	        } 
	        else if(optionC.isSelected()) {
	            answer = 'C';
	        } 
	        else if(optionD.isSelected()) {
	            answer = 'D';
	        }

	        // compares the answer of the user with the correct answer and increases the score if it is correct
	        if(answer == questions.get(currentQuestion).getAnswer()) {

	            JOptionPane.showMessageDialog(this, "Correct Answer!");
	            score++;

	        } else {

	            JOptionPane.showMessageDialog(this, 
	                "Wrong Answer! Correct Answer: " 
	                + questions.get(currentQuestion).getAnswer());

	        }

	        // moves to the next question
	        currentQuestion++;

	        // checks if there are still questions left, if not it displays the results
	        if(currentQuestion < questions.size()) {

	            displayQuestion();

	        } else {

	            new ResultFrame(score, questions.size());
	            dispose();

	        }
	    }

	}
	
	class ResultFrame extends JFrame{

	    public ResultFrame(int score,int total){

	    	// GUI components specifications
	        setTitle("Quiz Result");
	        setSize(400,300);
	        setLayout(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JLabel result = new JLabel("Quiz Completed!");
	        result.setBounds(120,20,200,30);
	        add(result);

	        JLabel scoreLabel = new JLabel("Score: "+score+" / "+total);
	        scoreLabel.setBounds(120,70,200,30);
	        add(scoreLabel);

	        JLabel correct = new JLabel("Correct Answers: "+score);
	        correct.setBounds(120,110,200,30);
	        add(correct);

	        JLabel wrong = new JLabel("Wrong Answers: "+(total-score));
	        wrong.setBounds(120,150,200,30);
	        add(wrong);

	        JButton menu = new JButton("Main Menu");
	        menu.setBounds(40,210,140,35);

	        JButton exit = new JButton("Exit");
	        exit.setBounds(210,210,140,35);

	        add(menu);
	        add(exit);

	        // goes back to the main menu when that button is clicked
	        menu.addActionListener(e->{

	            new MenuFrame();

	            dispose();

	        });

	        // exits the quiz
	        exit.addActionListener(e->System.exit(0));

	        setVisible(true);

	    }

	}

	public static void main(String[] args) {
		
		// calls the menu
		SwingUtilities.invokeLater(() -> {
	        QuizApplication app = new QuizApplication();
	        app.new MenuFrame();
	    });

	}

}
