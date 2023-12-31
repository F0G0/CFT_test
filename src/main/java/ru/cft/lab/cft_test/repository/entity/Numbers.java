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
    private int start;
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
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Numbers)) {
            return false;
        }
        Numbers c = (Numbers) o;
        return Double.compare(start, c.start) == 0
                && Double.compare(end, c.end) == 0;
    }
}
