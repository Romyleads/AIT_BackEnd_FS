package synchronizing_company_05.employee.dao;

import synchronizing_company_05.employee.model.Employee;

public interface Company {
    boolean addEmployee(Employee employee);

    Employee removeEmployee(int id);

    Employee findEmployee(int id);

    int quantity();

    double totalSalary();

    default double avgSalary() {
        return totalSalary() / quantity();
    }

    double totalSales();

    void printEmployees();

    Employee[] findEmployeesHoursGreaterThan(int hours);

    // minSalary - include, maxSalary - include
    Employee[] findEmployeesSalaryRange(int minSalary, int maxSalary);
}
