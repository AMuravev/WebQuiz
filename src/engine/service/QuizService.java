package engine.service;

import engine.entiry.Quiz;
import engine.model.QuizCreateDTO;
import engine.model.QuizViewDTO;
import engine.repository.QuizRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService implements IQuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<Quiz> findById(long id) {
        return quizRepository.findById(id);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public QuizViewDTO save(QuizCreateDTO quizCreateDTO) {

        Quiz quiz = modelMapper.map(quizCreateDTO, Quiz.class);

        quiz = quizRepository.save(quiz);

        return modelMapper.map(quiz, QuizViewDTO.class);
    }
}
