package engine.repository;

import engine.model.QuizRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class QuizRepository implements Repository<QuizRequestModel> {

    private final List<QuizRequestModel> collection = new ArrayList<>();
    final AtomicLong counter = new AtomicLong();

    @Override
    public List<QuizRequestModel> findAll() {
        return collection;
    }

    @Override
    public QuizRequestModel find(int id) {
        try {
            return collection.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public QuizRequestModel create(QuizRequestModel entity) {

        QuizRequestModel quiz = new QuizRequestModel(
                counter.incrementAndGet(),
                entity.getTitle(),
                entity.getText(),
                entity.getOptions(),
                entity.getAnswer()
        );

        collection.add(quiz);

        return quiz;
    }
}

interface Repository<T> {

    public List<T> findAll();

    public T find(int id);

    public T create(T entity);
}
