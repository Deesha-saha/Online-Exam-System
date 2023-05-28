import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Question {
    private String question;
    private List<String> options;
    private int answer;

    public Question(String question, List<String> options, int answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getAnswer() {
        return answer;
    }
}

class Exam {
    private List<Question> questions;
    private int score;

    public Exam() {
        questions = new ArrayList<>();
        score = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        JFrame frame = new JFrame("Online Exam System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(2, 1));

        JLabel questionLabel = new JLabel();
        questionPanel.add(questionLabel);

        ButtonGroup optionGroup = new ButtonGroup();
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        questionPanel.add(optionsPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);

        frame.add(questionPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedOption = -1;

                for (int i = 0; i < optionsPanel.getComponentCount(); i++) {
                    JRadioButton radioButton = (JRadioButton) optionsPanel.getComponent(i);
                    if (radioButton.isSelected()) {
                        selectedOption = i + 1;
                        break;
                    }
                }

                if (selectedOption != -1) {
                    Question currentQuestion = questions.get(score);
                    if (selectedOption == currentQuestion.getAnswer()) {
                        score++;
                    }

                    if (score < questions.size()) {
                        showQuestion(score, questionLabel, optionsPanel, optionGroup);
                    } else {
                        showResult(frame, score, questions.size());
                    }
                }
            }
        });

        showQuestion(0, questionLabel, optionsPanel, optionGroup);

        frame.setVisible(true);
    }

    private void showQuestion(int index, JLabel questionLabel, JPanel optionsPanel, ButtonGroup optionGroup) {
        Question question = questions.get(index);
        questionLabel.setText("Question " + (index + 1) + ": " + question.getQuestion());

        optionsPanel.removeAll();
        optionGroup.clearSelection();

        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            JRadioButton radioButton = new JRadioButton(options.get(i));
            radioButton.setActionCommand(String.valueOf(i + 1));
            optionsPanel.add(radioButton);
            optionGroup.add(radioButton);
        }
    }

    private void showResult(JFrame frame, int score, int totalQuestions) {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());

        JLabel resultLabel = new JLabel("Exam finished!");
        resultPanel.add(resultLabel);

        JLabel scoreLabel = new JLabel("Your score: " + score + "/" + totalQuestions);
        resultPanel.add(scoreLabel);

        frame.add(resultPanel, BorderLayout.CENTER);
        frame.revalidate();
    }
}

public class OnlineExamSystem4 {
    public static void main(String[] args) {
        Exam exam = new Exam();

        // Sample questions
        Question question1 = new Question("What is the square root of 64?", List.of("4", "6", "8", "10"), 3);
        Question question2 = new Question("Solve for x: 3x + 7 = 22", List.of("5", "7", "8", "9"), 4);
        Question question3 = new Question("What is the value of 5! (factorial)?", List.of("20", "60", "120", "720"), 3);
        Question question4 = new Question("What is the value of pi (π) approximately?", List.of("3.14", "2.71", "1.62", "1.41"), 1);
        Question question5 = new Question("What is the area of a square with side length 8?", List.of("32", "48", "64", "72"), 3);
        Question question6 = new Question("What is the value of log(100) base 10?", List.of("0", "1", "2", "10"), 2);
        Question question7 = new Question("Solve for x: 2x - 5 = 13", List.of("7", "9", "10", "11"), 4);
        Question question8 = new Question("What is the value of sin(45°)?", List.of("0", "0.5", "1", "√2/2"), 4);
        Question question9 = new Question("What is the sum of angles in a triangle?", List.of("90°", "180°", "270°", "360°"), 2);
        Question question10 = new Question("Simplify: 3 + 5 * 2 - 4", List.of("5", "9", "10", "14"), 2);
        Question question11 = new Question("What is the value of 2^4?", List.of("4", "8", "12", "16"), 4);
        Question question12 = new Question("What is the perimeter of a rectangle with length 12 and width 8?", List.of("28", "32", "36", "40"), 3);
        Question question13 = new Question("Solve for x: 4x - 3 = 5x + 2", List.of("-5", "-3", "2", "5"), 1);
        Question question14 = new Question("What is the value of cos(60°)?", List.of("0", "0.5", "1", "√3/2"), 4);
        Question question15 = new Question("What is the product of 9 and 7?", List.of("45", "63", "70", "81"), 3);

        // Add questions to the exam
        exam.addQuestion(question1);
        exam.addQuestion(question2);
        exam.addQuestion(question3);
         exam.addQuestion(question4);
        exam.addQuestion(question5);
        exam.addQuestion(question6);
        exam.addQuestion(question7);
        exam.addQuestion(question8);
        exam.addQuestion(question9);
        exam.addQuestion(question10);
        exam.addQuestion(question11);
        exam.addQuestion(question12);
        exam.addQuestion(question13);
        exam.addQuestion(question14);
        exam.addQuestion(question15);


        // Start the exam
        exam.start();
    }
}
