package ru.cft.lab.cft_test.repository.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "letters")
public class Letters {
    @jakarta.persistence.Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID Id;
    @Column(name = "let_start")
    private String start;
    @Column(name = "let_end")
    private String end;

    public Letters() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getStart() {
        return start;
    }

    public String setStart(String start) {
        return this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Letters)) {
            return false;
        }
        Letters c = (Letters) o;
        return CharSequence.compare(start, c.start) == 0
                && CharSequence.compare(end, c.end) == 0;
    }
}
