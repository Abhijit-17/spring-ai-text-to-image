package springai.text_to_image.services;

import org.springframework.stereotype.Service;
import springai.text_to_image.records.Answer;
import springai.text_to_image.records.Question;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    @Override
    public Answer getAnswer(Question question) {
        return new Answer("This is a dummy answer for the question: " + question.question());
    }

}
