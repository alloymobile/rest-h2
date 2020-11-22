package com.rbc.h2.persistence.dbo;

import com.rbc.h2.persistence.IH2DBO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = {"employee","project"})
public class EmployeeProject  implements IH2DBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

}
