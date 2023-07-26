package uz.pdp.frontend;

import uz.pdp.backend.dto.QuestionDTO;
import uz.pdp.backend.entity.Answer;
import uz.pdp.backend.entity.Option;
import uz.pdp.backend.entity.Question;
import uz.pdp.backend.entity.User;
import uz.pdp.backend.service.QuestionService;
import uz.pdp.backend.service.UserService;

import java.time.LocalDateTime;
import java.util.*;

public class QuestionUtil {

    static QuestionService questionService = QuestionService.getInstance();
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";



    public static void editQuestion(boolean isDelete) {

        Util.printElements(questionService.getAll());
        Util.editElement("question", questionService, isDelete);
    }

    public static Question changeQuestion(Question oldQuestion) {
        Util.printMenu("Enter new Title: ");
        String editedText = Util.textScanner.nextLine();
        if (editedText.length() == 0) {
            editedText = oldQuestion.getText();
        }
        List<Option> editedOptions = new ArrayList<>();
        String option;
        for (int i = 1; i < 5; i++) {
            Util.printMenu("Enter " + i + "-option: ");
            option = Util.textScanner.nextLine();
            if (option.length() == 0) {
                editedOptions.add(oldQuestion.getOptions().get(i - 1));
            }
            editedOptions.add(new Option(option));
        }
        while (true) {
            Util.printMenu("Choose correct answer (1-4)");
            int correctAnswer = Util.numberScanner.nextInt() - 1;
            if (correctAnswer < 0 || correctAnswer > 3) {
                System.out.println("Wrong number");
            } else {
                editedOptions.get(correctAnswer).setCorrect(true);
                break;
            }
        }
        return Question.builder()
                .id(oldQuestion.getId())
                .text(editedText)
                .options(editedOptions)
                .updatedAt(LocalDateTime.now())
                .updatedBy(Util.currentUser.getId()).build();

    }


    public static void createQuestion() {
        List<Option> options = new ArrayList<>();
        String answer;
//        Util.textScanner.next();
        Util.textScanner = new Scanner(System.in);
        Util.printMenu("Enter title: ");
        String title = Util.textScanner.nextLine();
        for (int i = 1; i < 5; i++) {
            Util.printMenu("Enter " + i + "-answer: ");
            options.add(new Option(Util.textScanner.nextLine()));
        }
        while (true) {
            Util.printMenu("Choose correct answer (1-4)");
            int correctAnswer = Util.numberScanner.nextInt() - 1;
            if (correctAnswer < 0 || correctAnswer > 3) {
                System.out.println("Wrong number");
            } else {
                options.get(correctAnswer).setCorrect(true);
                break;
            }
        }
        QuestionDTO questionDTO = new QuestionDTO(title, options, Util.currentUser.getId());
        questionService.create(questionDTO);
    }


    public static Question getRandomQuestion(Question[] questions) {
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

    public static void randomizeOption(Question question) {
        List<Option> randomizedOptions = new ArrayList<>();
        int[] indexes = new int[question.getOptions().size()];
        for (int i = 0; i <4 ; i++) {
            randomizedOptions.add(Util.getRandomElements(randomizedOptions,question.getOptions()));
        }
        question.setOptions(randomizedOptions);

    }


    public static void history() {
        if (Util.currentUser.getAnswers() == null) {
            Util.printFail("There is no history");
        } else {
            List<Answer> answers = Util.currentUser.getAnswers();
            List<Option> options = null;
            for (Answer answer : answers) {
                System.out.println(answer.getQuestion().getText());
                options = answer.getQuestion().getOptions();
                if (answer.getSelectedOption().isCorrect()) {
                    for (Option option : options) {
                        System.out.println((option.isCorrect()) ? TEXT_GREEN + option.getText() + TEXT_RESET : option.getText());
                    }
                } else {
                    for (Option option : options) {
                        System.out.println(
                                (option.isCorrect())
                                        ? TEXT_GREEN + option.getText() + TEXT_RESET
                                        : (option.equals(answer.getSelectedOption())
                                        ? TEXT_RED + option.getText() + TEXT_RESET
                                        : option.getText()));
                    }
                }

            }
        }

    }

}
