package com.rbc.h2.persistence.dbo;

import com.rbc.h2.persistence.IH2DBO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"projectEmployeeList"})
public class Project  implements IH2DBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<EmployeeProject> projectEmployeeList;
}
