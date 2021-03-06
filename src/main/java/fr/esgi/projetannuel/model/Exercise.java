package fr.esgi.projetannuel.model;

import fr.esgi.projetannuel.enumeration.Language;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "exercise_id", updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(columnDefinition = "text", nullable = false)
    private String code;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(nullable = false)
    private long initialInstructionsCount;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    public Exercise() {
        this.createdAt = LocalDateTime.now();
    }

    public Exercise(String title, String code, Language language) {
        this.title = title;
        this.code = code;
        this.language = language;

        this.createdAt = LocalDateTime.now();
    }

    public Exercise(String id, String title, String code, String description, Language language) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.description = description;
        this.language = language;

        this.createdAt = LocalDateTime.now();
    }

    public Exercise(String title, String code, String description, Language language, long initialInstructionsCount) {
        this.title = title;
        this.code = code;
        this.description = description;
        this.language = language;
        this.initialInstructionsCount = initialInstructionsCount;

        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getInitialInstructionsCount() {
        return initialInstructionsCount;
    }

    public void setInitialInstructionsCount(long initialInstructionsCount) {
        this.initialInstructionsCount = initialInstructionsCount;
    }
}
