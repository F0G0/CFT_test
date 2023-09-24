package ru.cft.lab.cft_test.repository.entity;

import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "numbers")
public class Numbers {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID Id;
    @Column(name = "num_start")
    private int Start;
    @Column(name = "num_end")
    private int end;

    public Numbers() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public int getStart() {
        return Start;
    }

    public void setStart(int start) {
        Start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
