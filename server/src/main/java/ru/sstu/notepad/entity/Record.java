package ru.sstu.notepad.entity;

import lombok.Data;
import lombok.ToString;
import ru.sstu.notepad.model.record.PriorityType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private PriorityType priority;

    @Column(name = "status_active")
    private boolean statusActive;


}
