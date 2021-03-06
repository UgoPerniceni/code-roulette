package fr.esgi.projetannuel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.esgi.projetannuel.enumeration.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Compilation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private String id;

    @Column(columnDefinition = "text", nullable = false)
    private String input;

    @Column(columnDefinition = "text", nullable = true)
    private String output;

    @OneToOne()
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    private User user;

    @OneToOne()
    @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id")
    private Exercise exercise;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Status status = Status.UNCOMPILED;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @Column(nullable = true)
    // @JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
    private LocalDateTime compiledAt;

    @Column(nullable = true)
    private long score;

    @Column(nullable = true)
    private long instructionsCount;


    public Compilation() {
        this.createdAt = LocalDateTime.now();
    }

    public Compilation(String input) {
        this.input = input;
        this.createdAt = LocalDateTime.now();
    }

    public Compilation(String input, String output, Status status) {
        this.input = input;
        this.output = output;
        this.status = status;

        this.createdAt = LocalDateTime.now();
        this.compiledAt = LocalDateTime.now();
    }

    public Compilation(String input, String output, Status status, User user, Exercise exercise, long score, long instructionsCount) {
        this.input = input;
        this.output = output;
        this.status = status;
        this.user = user;
        this.exercise = exercise;
        this.score = score;
        this.instructionsCount = instructionsCount;

        this.createdAt = LocalDateTime.now();
        this.compiledAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCompiledAt() {
        return compiledAt;
    }

    public void setCompiledAt(LocalDateTime compiledAt) {
        this.compiledAt = compiledAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public long getInstructionsCount() {
        return instructionsCount;
    }

    public void setInstructionsCount(long instructionsCount) {
        this.instructionsCount = instructionsCount;
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
