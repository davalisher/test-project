package uz.pdp.frontend;

import uz.pdp.backend.dto.UserDTO;
import uz.pdp.backend.entity.Answer;
import uz.pdp.backend.entity.Question;
import uz.pdp.backend.entity.User;
import uz.pdp.backend.service.AnswerService;
import uz.pdp.backend.service.QuestionService;
import uz.pdp.backend.service.UserService;
import uz.pdp.backend.util.Result;

import java.time.LocalDateTime;
import java.util.*;

public class UserUtil {
    static UserService userService = UserService.getInstance();
    static AnswerService answerService = AnswerService.getInstance();


    //    static User Util.currentUser;
//    static Util.resourceBundle Util.resource = Util.resourceBundle.getBundle("lang", locale);
    public static void signIn() {
        System.out.print(Util.resource.getString("username") + ": ");
        String username = Util.textScanner.nextLine();
        System.out.print(Util.resource.getString("password") + ": ");
        String password = Util.textScanner.nextLine();
        Util.currentUser = userService.get(username, password);
        if (Util.currentUser == null) {
            Util.printFail(Util.resource.getString("fail_message"));
        } else {
            System.out.println("Welcome " + Util.currentUser.getUsername());

        }

    }

    public static void signUpForm() {
        // TODO: 6/14/2023

        System.out.print(Util.resource.getString("username") + ": ");
        String username = Util.textScanner.nextLine();
        System.out.print(Util.resource.getString("password") + ": ");
        String password = Util.textScanner.nextLine();
        System.out.print(Util.resource.getString("confirm_password") + ": ");
        String confirmCassword = Util.textScanner.nextLine();
        try {
            UserDTO userDTO = new UserDTO(username, password, confirmCassword, null);
            userService.create(userDTO);
            Util.currentUser = userService.get(username, password);
            if (Util.currentUser != null) {
                System.out.println(Util.resource.getString("success_message"));
            } else {
                System.out.println(Util.resource.getString("exist_user"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void adminPanel() {
        while (true) {
            Util.printMenu("""
                    1 - Create new Question
                    2 - Edit Question
                    3 - Delete Question
                    4 - Sign Out
                    """);
            switch (Util.textScanner.nextLine()) {
                case "1" -> QuestionUtil.createQuestion();
                case "2" -> QuestionUtil.editQuestion(false);
                case "3" -> QuestionUtil.editQuestion(true);
                default -> {
                    return;
                }
            }
        }
    }

    public static void userPage() {
        while (true) {
            Util.printMenu(
                    "1 - " +
                            Util.resource.getString("history") + "\n2 - " +
                            Util.resource.getString("new_test") + "\n3 - " +
                            Util.resource.getString("edit_profile") + "\n4 - " +
                            Util.resource.getString("choose_language") + "\n5 - " +
                            Util.resource.getString("log_out") + "\n" +
                            Util.resource.getString("choose_operation") + ": ");

            switch (Util.textScanner.nextLine()) {
                case "1" -> QuestionUtil.history();
                case "2" -> newTest();
                case "3" -> editProfile();
                case "4" -> Util.chooseLang();

                default -> {
                    Util.currentUser = null;
                    return;
                }
            }
        }
    }

    public static User changeUser(User oldUser) {
        System.out.println("Enter username");
        String editedUsername = Util.textScanner.nextLine();
        if (editedUsername.length() == 0) {
            editedUsername = oldUser.getUsername();
        }
        System.out.println("Enter password");
        String editedPassword = Util.textScanner.nextLine();
        if (editedPassword.length() == 0) {
            editedPassword = oldUser.getPassword();
        }
        System.out.println("Enter phone");
        String editedPhone = Util.textScanner.nextLine();
        if (editedPhone.length() == 0) {
            editedPhone = oldUser.getPhone();
        }
        System.out.println("Enter email");
        String editedEmail = Util.textScanner.nextLine();
        if (editedEmail.length() == 0) {
            editedEmail = oldUser.getEmail();
        }
        User editedUser = User.builder()
                .id(oldUser.getId())
                .username(editedUsername)
                .password(editedPassword)
                .phone(editedPhone)
                .email(editedEmail)
                .updatedAt(LocalDateTime.now())
                .updatedBy(Util.currentUser.getId())
                .build();
        return editedUser;
    }

    public static void editProfile() {
        User changedUser = changeUser(Util.currentUser);
        Util.currentUser.setUsername(changedUser.getUsername());
        Util.currentUser.setPassword(changedUser.getPassword());
        Util.currentUser.setPhone(changedUser.getPhone());
        Util.currentUser.setEmail(changedUser.getEmail());
        changedUser.setUpdatedAt(changedUser.getUpdatedAt());

    }

    public static void newTest() {
        List<Answer> answers = new ArrayList<>();


        List<Question> questions = new ArrayList<>(Util.totalNumberOfQuestions);
        Question question;
        Util.printMenu("====Test is starting===");
        for (int i = 0; i < Util.totalNumberOfQuestions; i++) {

            question = Util.getRandomElements(questions, QuestionUtil.questionService.getAll());

            questions.add(question);
            QuestionUtil.randomizeOption(question);
            answers.add(answeringQuestion(question));
        }
        Result result = answerService.getResult(answers);
        Util.printMenu("===Test is finished.====");
        System.out.println("==================================");
        Util.printMenu("Your Results: ");
        Util.printMenu("\tRight answers: " + result.getRightAnswers());
        Util.printMenu("\tWrong answers: " + result.getWrongAnswers());
        System.out.println("==================================");
        Util.currentUser.setAnswers(answers);

    }

    public static Answer answeringQuestion(Question question) {
        while (true) {
            Answer answer = new Answer();
            System.out.println(question.getText());
//            question.getOptions().forEach(option -> System.out.println(option.getText()));
            for (int i = 1; i < 5; i++) {
                System.out.println(i + ") " + question.getOptions().get(i - 1).getText());
            }
            Util.printMenu("Choose correct answer(1-4): ");
            Util.numberScanner = new Scanner(System.in);
            int correctAnswer = Util.numberScanner.nextInt() - 1;
            if (correctAnswer < 0 || correctAnswer > 3) {
                System.out.println("Wrong number");
            } else {
                answer.setQuestion(question);
                answer.setSelectedOption(question.getOptions().get(correctAnswer));
                return answer;
            }
        }
    }


}
