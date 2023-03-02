package project.healthcommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trainer extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "trainer_id")
    private Long id;

    private String trainerName;
    private int age;
    private int career;

    @OneToMany(mappedBy = "trainer")
    private List<Certificate> certificates = new ArrayList<>();

    private int ranking;

    @OneToMany(mappedBy = "trainer")
    private List<Post> postList = new ArrayList<>();




}
