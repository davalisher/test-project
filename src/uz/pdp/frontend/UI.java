package uz.pdp.frontend;

import uz.pdp.backend.dto.QuestionDTO;
import uz.pdp.backend.dto.UserDTO;
import uz.pdp.backend.entity.*;
import uz.pdp.backend.enums.UserType;
//import uz.pdp.backend.service.QuestionServiceImpl;
import uz.pdp.backend.service.QuestionService;
import uz.pdp.backend.service.Service;
import uz.pdp.backend.service.UserService;
//import uz.pdp.backend.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.*;


public class UI {
    public static void main(String[] args) {

        Util.chooseLang();
        while (true) {
            Util.mainMenu();
            if (Util.currentUser == null) {
                return;
            }
            if (Util.currentUser.getUserType() == UserType.ADMIN) {
                UserUtil.adminPanel();
            } else {
                UserUtil.userPage();
            }
        }
    }




   /* private static void signOut() {
        currentUser = null;
        mainMenu();
    }*/


}
