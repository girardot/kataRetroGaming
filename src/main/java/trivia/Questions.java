package trivia;

import com.google.common.collect.Lists;

import java.util.LinkedList;

public class Questions {

    private LinkedList<String> questions = Lists.newLinkedList();

    private Category category;

    public Questions(Category category) {
        this.category = category;

        for (int i = 0; i < 50; i++) {
            questions.addLast(category + " Question " + i);
        }
    }

    public String nextQuestion() {
        return questions.removeFirst();
    }

}
