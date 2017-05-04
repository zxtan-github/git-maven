package org.ifunq.tanzx.tbbpm.machine.simpleflow;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.tbbpm.statemachine.StateMachineEngineFactory;
import com.taobao.tbbpm.statemachine.persistence.IStateMachineStateService;

@SuppressWarnings("resource")
public class SimpleFolwMachineTest3 {

	public static void main(String[] args) throws Exception {
        
		ApplicationContext ctx = new ClassPathXmlApplicationContext("tbbpm-config.xml"); 
        IStateMachineStateService stateMachineStateService = ctx.getBean("simpleFolwMachineService",IStateMachineStateService.class); 
//        StateMachineEngineFactory.getInstance(stateMachineStateService).triggerStateTransferWithTransaction(15l, "SUBMITED_2_APPROVAL", null);
        StateMachineEngineFactory.getInstance(stateMachineStateService).triggerStateTransfer(15l, "SUBMITED_2_APPROVAL", null);
//        StateMachineEngineFactory.getInstance(stateMachineStateService).triggerStateTransfer(8l, "APPROVAL_2_ISSUED", null);
//        StateMachineEngineFactory.getInstance(stateMachineStateService).triggerStateTransfer(8l, "ISSUED_2_END", null);
	}

}
