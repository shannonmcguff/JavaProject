package model;

import java.util.Comparator;

public class SortByExpenseAmount implements Comparator<Expenses> {

    @Override
    public int compare(Expenses e1, Expenses e2) {
        if(e1.getExpense() < e2.getExpense()){
            return -1;
        } else if(e1.getExpense() > e2.getExpense()) {
            return 1;
        } else {
            return 0;
        } 
    }
    
    
 }