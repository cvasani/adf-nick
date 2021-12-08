package com.packt.jdeveloper.cookbook.shared.view.beans;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.data.RichIterator;

public class CollectionPaginationBean {
    private RichIterator employeesIterator;
    private static int PAGE_SIZE = 3;

    public CollectionPaginationBean() {
    }

    public void setEmployeesIterator(RichIterator employeesIterator) {
        this.employeesIterator = employeesIterator;
    }

    public RichIterator getEmployeesIterator() {
        return employeesIterator;
    }
    
    public void onFirst(ActionEvent actionEvent) {
    this.employeesIterator.setFirst(0);
    }
    public void onPrevious(ActionEvent actionEvent) {
    this.employeesIterator.setFirst(
    this.employeesIterator.getFirst() - PAGE_SIZE);
    }
    public void onNext(ActionEvent actionEvent) {
    this.employeesIterator.setFirst(
    this.employeesIterator.getFirst() + PAGE_SIZE);
    }
    public void onLast(ActionEvent actionEvent) {
    this.employeesIterator.setFirst(
    employeesIterator.getRowCount() -
    employeesIterator.getRowCount() % PAGE_SIZE);
    }
    public boolean isPreviousRowAvailable() {
    return this.employeesIterator.getFirst() != 0;
    }
    
    public boolean isNextRowAvailable() {
    return (employeesIterator.getRowCount() >=
    employeesIterator.getFirst() + PAGE_SIZE);
    }
    public int getPageNumber() {
    return (this.employeesIterator.getFirst()/PAGE_SIZE) + 1;
    }
    
    
    
}
