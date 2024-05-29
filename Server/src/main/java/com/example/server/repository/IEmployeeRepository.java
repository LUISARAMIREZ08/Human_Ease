package com.example.server.repository;

import com.example.server.controller.response.employee.EmployeeJoin;
import com.example.server.controller.response.employee.EmployeeJoinCostCenter;
import com.example.server.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    //Trabajadores
    @Query("SELECT new com.example.server.controller.response.employee.EmployeeJoin(" +
            "u.cardId, u.name, u.lastName, p.namePosition, e.salaryBase,u.phone) " +
            "FROM Employee e " +
            "JOIN e.userEntity u " +
            "JOIN e.position p")
    List<EmployeeJoin> getEmployeeAndUserAndPosition();

    //Nomina
    @Query("SELECT new com.example.server.controller.response.employee.EmployeeJoinCostCenter("+
            "u.name, u.lastName, e.employeeId, u.cardId, c.costCenterId) "+
            "FROM Employee e "+
            "JOIN e.userEntity u "+
            "JOIN e.costCenter c")
    List<EmployeeJoinCostCenter> getEmployeeAndUserAndCostCenter();
}
