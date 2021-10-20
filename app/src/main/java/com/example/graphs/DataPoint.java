package com.example.graphs;

public class DataPoint {

        int salary,expenses;

        public DataPoint(int salary, int expenses) {
            this.salary = salary;
            this.expenses = expenses;
        }

        public DataPoint() {

        }

        public int getSalary() {
            return salary;
        }

        public int getExpenses() {
            return expenses;
        }

}
