package com.example.thymeleaftutorial.serviceImpl;

import com.example.thymeleaftutorial.exception.ResourceNotFoundException;
import com.example.thymeleaftutorial.model.Employee;
import com.example.thymeleaftutorial.repository.EmployeeRepository;
import com.example.thymeleaftutorial.service.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
//@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

//    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
       /* Lambda expresion version*/
        return employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        }else{
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        //we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        //save existing employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        //check whether a employee exist in a DB or not
        employeeRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.deleteById(id);
    }

    @Override
    public void saveEmployeeByGUI(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }
}
