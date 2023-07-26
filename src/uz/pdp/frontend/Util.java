package uz.pdp.frontend;

import uz.pdp.backend.entity.BaseEntity;
import uz.pdp.backend.entity.Question;
import uz.pdp.backend.entity.User;
import uz.pdp.backend.service.QuestionService;
import uz.pdp.backend.service.Service;
import uz.pdp.backend.service.UserService;

import java.util.*;

public class Util {
    static Scanner textScanner = new Scanner(System.in);
    static Scanner numberScanner = new Scanner(System.in);
    static int totalNumberOfQuestions = 5;
    static Locale locale = Locale.ENGLISH;
    static User currentUser;
    static ResourceBundle resource = ResourceBundle.getBundle("lang", locale);

    public static void mainMenu() {
        while (true) {
            printMenu("1 - "+resource.getString("sign_in") + "\n2 - " +
                    resource.getString("sign_up") + "\n3 - " +
                    resource.getString("choose_language") + "\n4 - " +
                    resource.getString("exit") + "\n" +
                    resource.getString("choose_operation") + ": ");
            switch (textScanner.nextLine()) {
                case "1" -> {
                    UserUtil.signIn();
                    if (currentUser != null) {
                        return;
                    }
                }
                case "2" -> {
                    UserUtil.signUpForm();
                    if (currentUser != null) {
                        return;
                    }
                }
                case "3" -> chooseLang();
                default -> {
                    currentUser = null;
                    return;
                }
            }
        }
    }

    public static void chooseLang() {
        printMenu(resource.getString("choose_language") + ": \n" +
                """
                        1 - English
                        2 - Russian
                        3 - Uzbek
                        """);
        locale = switch (textScanner.nextLine()) {
            case "1" -> Locale.ENGLISH;
            case "2" -> Locale.forLanguageTag("RU");
            default -> Locale.forLanguageTag("UZ");
        };
        resource = ResourceBundle.getBundle("lang", locale);
    }

    public static <T extends Service> void editElement(String elementName, T service, boolean isDelete) {
        List<BaseEntity> elements = service.getAll();
        int index = chooseElement(elementName, service);
        if (index == -1) {
            return;
        }
        BaseEntity chosenElement = elements.get(index);
        if (isDelete) {
            Object id = chosenElement.getId();
            deleteElement(service, id);
        } else {
            BaseEntity editedElement;
            if (chosenElement instanceof Question chosenQuestion) {
                editedElement = QuestionUtil.changeQuestion(chosenQuestion);
            } else {
                editedElement = UserUtil.changeUser((User) chosenElement);
            }
            updateElement(service, editedElement);
        }
    }


    public static <T extends Service, ID> void deleteElement(Service service, ID id) {
        if (service.delete(id)) {
            System.out.println("Successfully deleted");
        } else {
            System.out.println("Wrong operation");
        }
    }

    public static <T extends Service, ID> void updateElement(Service service, BaseEntity editedElement) {
        if (service.update(editedElement)) {
            System.out.println("Successfully updated");
        } else {
            System.out.println("Wrong operation");
        }
    }


    public static int chooseElement(String message, Service service) {
        printMenu("Choose " + message + ": ");
        int index;
        int length;
        do {
            index = numberScanner.nextInt() - 1;
            length = service.numberOfElements();
            if (index < -1 || index >= length) {
                System.out.println("Wrong number");
            }
        } while (index < -1 && index >= length);
        return index;
    }

    public static <T> void printElements(List<T> questions) {
        printMenu("0=>Back");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + "=>" + questions.get(i));
        }
    }

    public static <T> T getRandomElements(List<T> randomizedElements, List<T> elements) {
        Random random = new Random();
        boolean isEqual;
        int randomNumber;
        T randomElement = null;
        do {
            isEqual = false;
            randomNumber = random.nextInt(elements.size());
            randomElement = elements.get(randomNumber);
            for (T element : randomizedElements) {
                if (randomElement.equals(element)) {
                    isEqual = true;
                    break;
                }
            }
        } while (isEqual);
        return randomElement;

    }

    public static void printMenu(String message) {
        System.out.println(QuestionUtil.TEXT_GREEN+message+QuestionUtil.TEXT_RESET);
    }
    public static void printFail(String message) {
        System.out.println(QuestionUtil.TEXT_RED+message+QuestionUtil.TEXT_RESET);
    }
}
