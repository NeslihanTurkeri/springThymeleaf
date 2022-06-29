package com.example.thymeleaftutorial.controller;

import com.example.thymeleaftutorial.model.Employee;
import com.example.thymeleaftutorial.service.EmployeeService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //Build get all employees REST API
    @GetMapping
    @RequestMapping( "/listEmployees")
    public ModelAndView  getAllEmployees(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return new ModelAndView("index");
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee",  employee);
        return "new_employee";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployeeByGUI(@ModelAttribute("employee") Employee employee){
        //save to db
        employeeService.saveEmployee(employee);
        return "redirect:/listEmployees";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model){
        //get employee from the service
        Employee employee = employeeService.getEmployeeById(id);
        //set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployeeByGUI(@PathVariable (value = "id") long id){
        //call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/listEmployees";
    }
    //Build get employee by id REST AP
    //http://localhost:8090/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
    }
    //Build create employee REST API
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),
                HttpStatus.CREATED);
    }
    //Build update Employee REST API
    //http://localhost:8090/api/employees/1
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee){

        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }
    //Build delete employee REST API
    //http://localhost:8090/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){

        //delete employee from DB
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleted successfully!",  HttpStatus.OK);
    }
}
