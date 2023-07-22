package uz.pdp.frontend;

import uz.pdp.backend.dto.QuestionDTO;
import uz.pdp.backend.dto.UserDTO;
import uz.pdp.backend.entity.Answer;
import uz.pdp.backend.entity.Option;
import uz.pdp.backend.entity.Question;
import uz.pdp.backend.entity.User;
import uz.pdp.backend.enums.UserType;
//import uz.pdp.backend.service.QuestionServiceImpl;
import uz.pdp.backend.service.QuestionService;
import uz.pdp.backend.service.UserService;
//import uz.pdp.backend.service.UserServiceImpl;

import java.util.*;


public class UI {

    static Scanner textScanner = new Scanner(System.in);
    static Scanner numberScanner = new Scanner(System.in);
    static UserService userService = new UserService();
    static QuestionService questionService = new QuestionService();
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";
    static int totalNumberOfQuestions = 5;
    static Locale locale = Locale.ENGLISH;
    static User currentUser;
    static ResourceBundle resource = ResourceBundle.getBundle("lang", locale);

    public static void main(String[] args) {

        chooseLang();
        while (true) {
            mainMenu();
            if (currentUser == null) {
                return;
            }
            if (currentUser.getUserType() == UserType.ADMIN) {
                adminPanel();
            } else {
                userPage();
            }

        }
    }

    public static void mainMenu() {
        System.out.print(
                resource.getString("sign_in") + " - 1\n" +
                        resource.getString("sign_up") + " - 2\n" +
                        resource.getString("choose_language") + " - 3\n" +
                        resource.getString("exit") + " - 4\n" +
                        resource.getString("choose_operation") + ": "
        );
        switch (textScanner.nextLine()) {
            case "1" -> signIn();
            case "2" -> signUpForm();
//                case "3" -> chooseLang();
            default -> {
                currentUser = null;
                return;
            }
        }
    }

    public static void chooseLang() {
        System.out.println(
                resource.getString("choose_language") + ": \n" +
                        """
                                1 - English
                                2 - Russian
                                3 - Uzbek""");
        locale = switch (textScanner.nextLine()) {
            case "1" -> Locale.ENGLISH;
            case "2" -> Locale.forLanguageTag("RU");
            default -> Locale.forLanguageTag("UZ");
        };
        resource = ResourceBundle.getBundle("lang", locale);
    }

    public static void signIn() {
        System.out.print(resource.getString("username") + ": ");
        String username = textScanner.nextLine();
        System.out.print(resource.getString("password") + ": ");
        String password = textScanner.nextLine();
        currentUser = userService.get(username, password);
        if (currentUser == null) {
            System.out.print(resource.getString("fail_message"));
        } else {
            System.out.println("Welcome " + currentUser.getUsername());
        }

    }

    public static void signUpForm() {
        // TODO: 6/14/2023

        System.out.print(resource.getString("username") + ": ");
        String username = textScanner.nextLine();
        System.out.print(resource.getString("password") + ": ");
        String password = textScanner.nextLine();
        System.out.print(resource.getString("confirm_password") + ": ");
        String confirmCassword = textScanner.nextLine();
        try {
            UserDTO userDTO = new UserDTO(username, password, confirmCassword, null);
            userService.create(userDTO);
            currentUser = userService.get(username, password);
            if (currentUser != null) {
                System.out.println(resource.getString("success_message"));
            } else {
                System.out.println(resource.getString("exist_user"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void adminPanel() {
        while (true) {
            System.out.print("""
                    Create new Question: 1
                    Edit Question: 2
                    Delete Question: 3
                    Sign Out: 4
                    """);
            switch (textScanner.nextLine()) {
                case "1" -> createQuestion();
                case "2" -> editQuestion();
                case "3" -> deleteQuestion();
                default -> {
                    return;
                }
            }
        }
    }

   /* private static void signOut() {
        currentUser = null;
        mainMenu();
    }*/

    private static void deleteQuestion() {
        int questionsSize = questionService.getAll().size();
        for (int i = 0; i < questionsSize; i++) {
            System.out.println((i + 1) + "=>" + questionService.getAll().get(i));
        }
        while (true) {
            System.out.println("Choose question: ");
            int index = numberScanner.nextInt() - 1;
            if (index < 0 || index > questionsSize) {
                System.out.println("Wrong number");
            } else {
                if (questionService.delete(questionService.getAll().get(index).getId())) {
                    System.out.println("Successfully deleted");
                } else {
                    System.out.println("Wrong operation");
                }
            }
        }


    }

    private static void editQuestion() {
        Question editedQuestion = new Question();

        int questionsSize = questionService.getAll().size();
        for (int i = 0; i < questionsSize; i++) {
            System.out.println((i + 1) + "=>" + questionService.getAll().get(i));
        }
        while (true) {
            System.out.println("Choose question: ");
            int index = numberScanner.nextInt() - 1;
            if (index < 0 || index > questionsSize) {
                System.out.println("Wrong number");
            } else {
                Question oldQuestion = questionService.getAll().get(index);
                System.out.print("Enter new Title: ");
                String title = textScanner.nextLine();
                if (title.length() == 0) {
                    editedQuestion.setText(oldQuestion.getText());
                } else {
                    editedQuestion.setText(title);
                }
                List<Option> options = new ArrayList<>();
                String answer;
                for (int i = 1; i < 5; i++) {
                    System.out.println("Enter " + i + "-answer: ");
                    answer = textScanner.nextLine();
                    if (answer.length() == 0) {
                        options.add(oldQuestion.getOptions().get(i - 1));
                    }
                    options.add(new Option(answer));
                }
                while (true) {
                    System.out.println("Choose correct answer (1-4)");
                    int correctAnswer = numberScanner.nextInt() - 1;
                    if (correctAnswer < 0 || correctAnswer > 3) {
                        System.out.println("Wrong number");
                    } else {
                        options.get(correctAnswer).setCorrect(true);
                        break;
                    }
                }
                editedQuestion.setOptions(options);
                break;


            }
        }
        questionService.update(editedQuestion);
    }

    private static void createQuestion() {
        List<Option> options = new ArrayList<>();
        String answer;
//        textScanner.next();
        textScanner = new Scanner(System.in);
        System.out.println("Enter title: ");
        String title = textScanner.nextLine();
        for (int i = 1; i < 5; i++) {
            System.out.println("Enter " + i + "-answer: ");
            options.add(new Option(textScanner.nextLine()));
        }
        while (true) {
            System.out.println("Choose correct answer (1-4)");
            int correctAnswer = numberScanner.nextInt() - 1;
            if (correctAnswer < 0 || correctAnswer > 3) {
                System.out.println("Wrong number");
            } else {
                options.get(correctAnswer).setCorrect(true);
                break;
            }
        }
        QuestionDTO questionDTO = new QuestionDTO(title, options, currentUser.getId());
        questionService.create(questionDTO);
    }

    public static void userPage() {
        while (true) {
            System.out.print(
                    resource.getString("history") + " - 1\n" +
                            resource.getString("new_test") + " - 2\n" +
                            resource.getString("edit_profile") + " - 3\n" +
                            resource.getString("choose_language") + "- 4\n" +
                            resource.getString("log_out") + " - 5\n" +
                            resource.getString("choose_operation") + " : ");

            switch (textScanner.nextLine()) {
                case "1" -> history();
                case "2" -> newTest();
                case "3" -> editProfile();
                case "4" -> chooseLang();

                default -> {
                    currentUser = null;
                    return;
                }
            }
        }
    }

    private static void editProfile() {

    }

    private static void newTest() {
        List<Answer> answers = new ArrayList<>();


        Question[] questions = new Question[totalNumberOfQuestions];
        Question question;

        for (int i = 0; i < totalNumberOfQuestions; i++) {

            question = getRandomQuestion(questions);
            questions[i] = question;

            answers.add(answeringQuestion(question));
        }
        currentUser.setAnswers(answers);

    }

    private static Answer answeringQuestion(Question question) {
        while (true) {
            Answer answer = new Answer();
            System.out.println(question.getText());
//            question.getOptions().forEach(option -> System.out.println(option.getText()));
            for (int i = 1; i < 5; i++) {
                System.out.println(i + ") " + question.getOptions().get(i - 1).getText());
            }
            System.out.println("Choose correct answer (1-4)");
            numberScanner = new Scanner(System.in);
            int correctAnswer = numberScanner.nextInt() - 1;
            if (correctAnswer < 0 || correctAnswer > 3) {
                System.out.println("Wrong number");
            } else {
                answer.setQuestion(question);
                answer.setSelectedOption(question.getOptions().get(correctAnswer));
                return answer;
            }
        }
    }

    private static Question getRandomQuestion(Question[] questions) {
        Random random = new Random();
        boolean isEqual;
        int randomNumber;
        Question randomQuestion = null;
        do {
            isEqual = false;
            randomNumber = random.nextInt(questionService.getAll().size());
            randomQuestion = questionService.getAll().get(randomNumber);
            for (Question question : questions) {
                if (randomQuestion.equals(question)) {
                    isEqual = true;
                    break;
                }
            }
        } while (isEqual);
        return randomQuestion;
    }

    private static void history() {
        if (currentUser.getAnswers() == null) {
            System.out.println("There is no history");
        } else {
            List<Answer> answers = currentUser.getAnswers();
            List<Option> options = null;
            for (Answer answer : answers) {
                System.out.println(answer.getQuestion().getText());
                options = answer.getQuestion().getOptions();
                if (answer.getSelectedOption().isCorrect()) {
                    for (Option option : options) {
                        System.out.println((option.isCorrect()) ? TEXT_GREEN + option.getText()+TEXT_RESET : option.getText());
                    }
                }else {
                    for (Option option : options) {
                        System.out.println(
                                (option.isCorrect())
                                        ? TEXT_GREEN + option.getText()+TEXT_RESET
                                        :(option.equals(answer.getSelectedOption())
                                                ?TEXT_RED+option.getText()+TEXT_RESET
                                        : option.getText()));
                    }
                }

            }
        }

    }

    public static void testing() {
    }

    public static void resultHistory() {

    }


}
