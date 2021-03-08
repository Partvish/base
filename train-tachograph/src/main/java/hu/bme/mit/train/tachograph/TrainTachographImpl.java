package hu.bme.mit.train.tachograph;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainTachograph;
import hu.bme.mit.train.interfaces.TrainUser;

import java.util.Date;

public class TrainTachographImpl implements TrainTachograph {

    private TrainController controller;
    private TrainUser user;
    private Date date;
    private Table<Date, Integer, Integer> table;

    public TrainTachographImpl(TrainController controller, TrainUser user){
        this.controller = controller;
        this.user = user;
        this.date =  new Date();
        this.table = TreeBasedTable.create();
    }
    @Override
    public void saveData(){
        table.put(date, user.getJoystickPosition(), controller.getReferenceSpeed());
    }
    @Override
    public boolean isEmpty(){
        return table.isEmpty();
    }
}
