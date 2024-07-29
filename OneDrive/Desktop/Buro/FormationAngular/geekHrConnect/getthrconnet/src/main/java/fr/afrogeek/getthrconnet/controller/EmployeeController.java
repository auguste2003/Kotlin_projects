package fr.afrogeek.getthrconnet.controller;

import fr.afrogeek.getthrconnet.entity.Employee;
import fr.afrogeek.getthrconnet.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
/**
Avec ceci , il y'aura toujours certaines requettes existantes chez swagger
 */
@RestController  // Qui va traiter des réquettes http
@RequestMapping("/employees") // Toutes réquettes seront dirigées ici
@RequiredArgsConstructor // lambock génere un constructeur
// Documentation
@Tag(name = "Employee Management", description = "The Employee Management API provides operations to manage employees, including creation, retrieval, updating, and deletion of employee records.")

public class EmployeeController {
    private final EmployeeService employeeService;


    @PostMapping
    // Commenter le post
    @Operation(summary = "Create a new employee", description = "Creates a new employee and returns the created object.")
    @ApiResponse(responseCode = "200", description = "Employee created successfully",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))})
    public Employee createEmployee(@RequestBody @Parameter(description = "Employee object that needs to be added to the store") Employee employee) {
        return employeeService.createEmployee(employee); // Crée et retourne un employé
    }

    @GetMapping
    /**
     * Commenter le get pour faciliter l'utilisation de l'api
     */
    @Operation(summary = "Get all employees", description = "Returns a list of all employees in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))})
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees(); //
    }

    /**
     *  Commenter la méthode d'obtention des employés
     * @param id de l'employée
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get an employee by ID", description = "Returns a single employee by ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the employee",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))})
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public Employee getEmployeeById(@PathVariable @Parameter(description = "ID of the employee to be obtained") UUID id) {
        return employeeService.getEmployeeById(id); // returner un employée
    }

    /**
     *  Commenter comment ce passe le changement des informations de l'employé
     * @param id de l'employee
     * @param employee les informations sur l'employé
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an employee", description = "Updates an existing employee identified by the ID and returns the updated object.")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))})
    public Employee updateEmployee(@PathVariable @Parameter(description = "ID of the employee to be updated") UUID id,
                                   @RequestBody @Parameter(description = "Updated employee object") Employee employee) {
        return employeeService.updateEmployee(id, employee); // On fait un update des employees
    }

    /**
     *  Commenter la methode de suppression de l'employé
     * @param id de l'employé
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee", description = "Deletes an employee identified by the ID.")
    @ApiResponse(responseCode = "200", description = "Employee deleted successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public void deleteEmployee(@PathVariable @Parameter(description = "ID of the employee to be deleted") UUID id) {
        employeeService.deleteEmployee(id); // Supprimer un employé
    }
}
