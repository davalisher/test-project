package uz.pdp.frontend;

import uz.pdp.dto.QuestionDTO;
import uz.pdp.dto.UserDTO;
import uz.pdp.entity.Answer;
import uz.pdp.entity.Question;
import uz.pdp.entity.User;
import uz.pdp.enums.UserType;
import uz.pdp.service.QuestionService;
import uz.pdp.service.QuestionServiceImpl;
import uz.pdp.service.UserService;
import uz.pdp.service.UserServiceImpl;

import java.util.*;


public class UI {
    static Scanner textScanner = new Scanner(System.in);
    static Scanner numberScanner = new Scanner(System.in);
    static UserService userService = new UserServiceImpl();
    static QuestionService questionService = new QuestionServiceImpl();

    static {
        userService.create(new UserDTO("admin", "root123456", "root123456"));
    }

    static Locale locale = Locale.ENGLISH;
    static User currentUser;
    static ResourceBundle resource = ResourceBundle.getBundle("lang", locale);

    public static void main(String[] args) {
        chooseLang();
        while (true) {
            mainMenu();
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
        switch (textScanner.next()) {
            case "1" -> signIn();
            case "2" -> signUpForm();
//                case "3" -> chooseLang();
            default -> {
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
        locale = switch (textScanner.next()) {
            case "1" -> Locale.ENGLISH;
            case "2" -> Locale.forLanguageTag("RU");
            default -> Locale.forLanguageTag("UZ");
        };
        resource = ResourceBundle.getBundle("lang", locale);
    }

    public static void signIn() {
        System.out.print(resource.getString("username") + ": ");
        String username = textScanner.next();
        System.out.print(resource.getString("password") + ": ");
        String password = textScanner.next();
        currentUser = userService.get(username, password);
        if (currentUser == null) {
            System.out.print(resource.getString("fail_message"));
        } else {
            System.out.println(currentUser);
        }

    }

    public static void signUpForm() {
        // TODO: 6/14/2023

        System.out.print(resource.getString("username") + ": ");
        String username = textScanner.next();
        System.out.print(resource.getString("password") + ": ");
        String password = textScanner.next();
        System.out.print(resource.getString("confirm_password") + ": ");
        String confirmCassword = textScanner.next();
        try {
            UserDTO userDTO = new UserDTO(username, password, confirmCassword);
            currentUser = userService.create(userDTO);
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
            switch (textScanner.next()) {
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
                if (questionService.delete(questionService.getAll().get(index).getUuid())) {
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
                    editedQuestion.setTitle(oldQuestion.getTitle());
                } else {
                    editedQuestion.setTitle(title);
                }
                List<Answer> answers = new ArrayList<>();
                String answer;
                for (int i = 1; i < 5; i++) {
                    System.out.println("Enter " + i + "-answer: ");
                    answer = textScanner.nextLine();
                    if (answer.length() == 0) {
                        answers.add(oldQuestion.getAnswers().get(i));
                    }
                    answers.add(new Answer(answer));
                }
                while (true) {
                    System.out.println("Choose correct answer (1-4)");
                    int correctAnswer = numberScanner.nextInt() - 1;
                    if (correctAnswer < 0 || correctAnswer > 3) {
                        System.out.println("Wrong number");
                    } else {
                        answers.get(correctAnswer).setCorrect(true);
                        break;
                    }
                }
                editedQuestion.setAnswers(answers);
                break;


            }
        }
        questionService.edit(editedQuestion);
    }

    private static void createQuestion() {
        List<Answer> answers = new ArrayList<>();
        String answer;
//        textScanner.next();
        textScanner = new Scanner(System.in);
        System.out.println("Enter title: ");
        String title = textScanner.nextLine();
        for (int i = 1; i < 5; i++) {
            System.out.println("Enter " + i + "-answer: ");
            answers.add(new Answer(textScanner.nextLine()));
        }
        while (true) {
            System.out.println("Choose correct answer (1-4)");
            int correctAnswer = numberScanner.nextInt() - 1;
            if (correctAnswer < 0 || correctAnswer > 3) {
                System.out.println("Wrong number");
            } else {
                answers.get(correctAnswer).setCorrect(true);
                break;
            }
        }
        QuestionDTO questionDTO = new QuestionDTO(title, answers);
        questionService.create(questionDTO);
    }

    public static void userPage() {

    }

    public static void testing() {
    }

    public static void resultHistory() {

    }


}
