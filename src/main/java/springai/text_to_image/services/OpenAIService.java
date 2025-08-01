package springai.text_to_image.services;

import springai.text_to_image.records.Answer;
import springai.text_to_image.records.Question;

public interface OpenAIService {

    public Answer getAnswer(Question question);

}
