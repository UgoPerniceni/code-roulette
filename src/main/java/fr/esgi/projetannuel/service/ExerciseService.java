package fr.esgi.projetannuel.service;

import fr.esgi.projetannuel.enumeration.Language;
import fr.esgi.projetannuel.exception.ExerciseNotSplittedException;
import fr.esgi.projetannuel.exception.ResourceNotFoundException;
import fr.esgi.projetannuel.model.Constants;
import fr.esgi.projetannuel.model.Exercise;
import fr.esgi.projetannuel.repository.ExerciseRepository;
import fr.esgi.projetannuel.service.compiler.CodeAdapterServiceFactory;
import fr.esgi.projetannuel.service.compiler.AbstractCompilerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository repository;

    public List<Exercise> findAll() {
        return repository.findAll();
    }

    public Exercise getExercise(String id){
        return repository.findExerciseById(id).orElseThrow(() -> new ResourceNotFoundException(Constants.EXERCISE, id));
    }

    public Exercise getExerciseToDisplay(String id) { //codeService pour split
        var exercise = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Constants.EXERCISE, id));
        var compilerService = CodeAdapterServiceFactory.create(exercise.getLanguage());

        String codeToDisplay = compilerService.createExerciseToDisplay(exercise.getId(), exercise.getCode());
        if(codeToDisplay == null || codeToDisplay.isBlank()){
            throw new ExerciseNotSplittedException(Constants.EXERCISE, id);
        }
        return new Exercise(id, exercise.getTitle(), codeToDisplay, exercise.getDescription(), exercise.getLanguage());
    }

    public Exercise create(Exercise exercise) {
        return repository.save(exercise);
    }

    @Transactional
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public List<Exercise> findAllByLanguage(Language language) {
        return repository
                .findAll()
                .stream().filter(exercise -> exercise.getLanguage().equals(language))
                .collect(Collectors.toList());
    }
}
