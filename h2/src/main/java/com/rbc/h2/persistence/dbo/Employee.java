package com.rbc.h2.persistence.dbo;

import com.rbc.h2.persistence.IH2DBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"department","employeeProjectList"})
@EqualsAndHashCode(exclude = {"department","employeeProjectList"})
public class Employee  implements IH2DBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeProject> employeeProjectList;
}
