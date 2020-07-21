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

    @Override
    public Optional<Quiz> findById(long id) {
        return quizRepository.findById(id);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz save(Quiz quizCreateDTO) {

        //Quiz quiz = modelMapper.map(quizCreateDTO, Quiz.class);

        Quiz quiz = quizRepository.save(quizCreateDTO);

//        return modelMapper.map(quiz, QuizViewDTO.class);
        return quiz;
    }
}
