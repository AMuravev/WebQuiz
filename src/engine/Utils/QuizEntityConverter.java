package engine.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.entiry.Quiz;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class QuizEntityConverter extends AbstractHttpMessageConverter<Quiz> {

    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    protected boolean supports(Class<?> clazz) {
        return (Quiz.class == clazz);
    }

    @Override
    protected Quiz readInternal(Class<? extends Quiz> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String, String> vals = formHttpMessageConverter.read(null, inputMessage).toSingleValueMap();

        //Object vals = marshallingHttpMessageConverter.read(Object.class, inputMessage);

        Quiz quiz = new Quiz();
        quiz.setAnswer(List.of(1,2,3));
        //quiz.setId(222L);
        quiz.setOptions(List.of("22","222","333"));
        quiz.setText("eee");
        quiz.setTitle("ggggg");

        return mapper.convertValue(vals, Quiz.class);
    }

    @Override
    protected void writeInternal(Quiz quiz, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
