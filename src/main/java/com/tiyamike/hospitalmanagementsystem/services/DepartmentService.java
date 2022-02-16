package com.tiyamike.hospitalmanagementsystem.services;

import com.tiyamike.hospitalmanagementsystem.models.AppUser;
import com.tiyamike.hospitalmanagementsystem.models.Department;
import com.tiyamike.hospitalmanagementsystem.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void saveDepartment(Department department){
        departmentRepository.save(department);
    }

    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(long id){
        return departmentRepository.findById(id);
    }

    public void updateDepartment(Department department){
        departmentRepository.save(department);
    }

    public void deleteDepartment(long id){
        departmentRepository.deleteById(id);
    }
}
