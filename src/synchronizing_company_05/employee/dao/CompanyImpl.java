package synchronizing_company_05.employee.dao;

import synchronizing_company_05.employee.model.Employee;
import synchronizing_company_05.employee.model.SalesManager;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;

public class CompanyImpl implements Company {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private final Set<Employee> employees;
    private final int capacity;

    public CompanyImpl(int capacity) {
        this.capacity = capacity;
        employees = new HashSet<>();
    }

    // O(1)
    // Добавление сотрудника — write lock
    @Override
    public boolean addEmployee(Employee employee) {
        writeLock.lock();
        try {
            if (employee == null) {
                throw new RuntimeException("null");
            }
            if (capacity == employees.size()) {
                return false;
            }
            return employees.add(employee);
        } finally {
            writeLock.unlock();
        }
    }

    // O(n)
    // Удаление сотрудника — write lock
    @Override
    public Employee removeEmployee(int id) {
        writeLock.lock();
        try {
            Employee victim = findEmployee(id);
            employees.remove(victim);
            return victim;
        } finally {
            writeLock.unlock();
        }
    }

    // O(n)
    // Поиск сотрудника — read lock
    @Override
    public Employee findEmployee(int id) {
        readLock.lock();
        try {
            for (Employee employee : employees) {
                if (employee.getId() == id) {
                    return employee;
                }
            }
            return null;
        } finally {
            readLock.unlock();
        }
    }

    // O(1)
    // Получение количества сотрудников — read lock
    @Override
    public int quantity() {
        readLock.lock();
        try {
            return employees.size();
        } finally {
            readLock.unlock();
        }
    }

    // O(n)
    // Сумма зарплат — read lock
    @Override
    public double totalSalary() {
        readLock.lock();
        try {
            double sum = 0;
            for (Employee employee : employees) {
                sum += employee.calcSalary();
            }
            return sum;
        } finally {
            readLock.unlock();
        }
    }

    // O(n)
    // Сумма продаж — read lock
    @Override
    public double totalSales() {
        readLock.lock();
        try {
            double sum = 0;
            for (Employee employee : employees) {
                if (employee instanceof SalesManager sm) {
                    sum += sm.getSalesValue();
                }
            }
            return sum;
        } finally {
            readLock.unlock();
        }
    }

    // O(n)
    // Печать сотрудников — read lock
    @Override
    public void printEmployees() {
        readLock.lock();
        try {
            System.out.println("=======================");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
            System.out.println("=======================");
        } finally {
            readLock.unlock();
        }
    }

    // O(n)
    // Поиск по часам — read lock
    @Override
    public Employee[] findEmployeesHoursGreaterThan(int hours) {
        readLock.lock();
        try {
            Predicate<Employee> predicate = e -> e.getHours() > hours;
            return findEmployeesByPredicate(predicate);
        } finally {
            readLock.unlock();
        }
    }

    // O(n)
    // Поиск по зарплате — read lock
    @Override
    public Employee[] findEmployeesSalaryRange(int minSalary, int maxSalary) {
        readLock.lock();
        try {
            return findEmployeesByPredicate(e -> e.calcSalary() >= minSalary && e.calcSalary() <= maxSalary);
        } finally {
            readLock.unlock();
        }
    }

    // O(n)
    // Общий метод для фильтрации — не блокирует сам, вызывается из read-блоков
    private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
        List<Employee> res = new ArrayList<>();
        for (Employee employee : employees) {
            if (predicate.test(employee)) {
                res.add(employee);
            }
        }
        return res.toArray(new Employee[0]);
    }
}
