package uz.pdp.backend.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.pdp.backend.entity.Option;
import uz.pdp.backend.entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionRepository extends BaseRepository<Question, UUID> {
    private static final QuestionRepository QUESTION_REPOSITORY=new QuestionRepository();

   static  {
       List<Option> options=new ArrayList<>();
       Option o1 = new Option("1");
       Option o2 = new Option("2");
       Option o3 = new Option("3");


       List<Option> options1=List.of(o1,o2,o3,new Option("1,",true));
       List<Option> options2=List.of(o1,o2,o3,new Option("4,",true));
       List<Option> options3=List.of(o1,o2,o3,new Option("6,",true));
       List<Option> options4=List.of(o1,o2,o3,new Option("8,",true));
       List<Option> options5=List.of(o1,o2,o3,new Option("10,",true));
       List<Option> options6=List.of(o1,o2,o3,new Option("12,",true));
       List<Option> options7=List.of(o1,o2,o3,new Option("14,",true));
       List<Option> options8=List.of(o1,o2,o3,new Option("16,",true));
       List<Option> options9=List.of(o1,o2,o3,new Option("18,",true));
       List<Option> options10=List.of(o1,o2,o3,new Option("20,",true));

        QUESTION_REPOSITORY.add(new Question("1*2=?",options1));
        QUESTION_REPOSITORY.add(new Question("2*2=?",options2));
        QUESTION_REPOSITORY.add(new Question("3*2=?",options3));
        QUESTION_REPOSITORY.add(new Question("4*2=?",options4));
        QUESTION_REPOSITORY.add(new Question("5*2=?",options5));
        QUESTION_REPOSITORY.add(new Question("6*2=?",options6));
        QUESTION_REPOSITORY.add(new Question("7*2=?",options7));
        QUESTION_REPOSITORY.add(new Question("8*2=?",options8));
        QUESTION_REPOSITORY.add(new Question("9*2=?",options9));
        QUESTION_REPOSITORY.add(new Question("10*2=?",options10));
    }

    public static QuestionRepository getInstance() {
        return QUESTION_REPOSITORY;
    }

}
