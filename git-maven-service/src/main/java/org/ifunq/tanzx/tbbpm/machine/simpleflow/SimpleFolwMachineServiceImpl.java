package org.ifunq.tanzx.tbbpm.machine.simpleflow;

import com.taobao.tbbpm.statemachine.persistence.impl.StateMachineStateServiceImpl;

public class SimpleFolwMachineServiceImpl extends StateMachineStateServiceImpl {

    private static final String TABLE_NAME = "SC_SIMPLE_FLOW";

    private static final String STATE_COLUMN_NAME = "STATE";
    
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getStateColumn() {
        return STATE_COLUMN_NAME;
    }

    @Override
    public String getName() {
        return "simpleFolwMachineService";
    }

}
