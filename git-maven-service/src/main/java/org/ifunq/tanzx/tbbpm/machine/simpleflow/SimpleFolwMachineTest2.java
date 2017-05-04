package org.ifunq.tanzx.tbbpm.machine.simpleflow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.tbbpm.statemachine.StateMachineEngineFactory;
import com.taobao.tbbpm.statemachine.persistence.IStateMachineStateService;

@SuppressWarnings("resource")
public class SimpleFolwMachineTest2 {

	public static void main(String[] args) throws Exception {
        
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("request", "tanzongxi");

		ApplicationContext ctx = new ClassPathXmlApplicationContext("tbbpm-config.xml"); 
        IStateMachineStateService stateMachineStateService = ctx.getBean("simpleFolwMachineService",IStateMachineStateService.class); 
        long stateId = StateMachineEngineFactory.getInstance(stateMachineStateService)
        		.createStateMachineInstance("org.ifunq.tanzx.tbbpm.statemachine.tanzx_machine03", null, null, paramsMap);
        StateMachineEngineFactory.getInstance(stateMachineStateService).start(stateId);
        StateMachineEngineFactory.getInstance(stateMachineStateService).triggerStateTransferWithTransaction(stateId, "SUBMITED_2_APPROVAL", null);
        StateMachineEngineFactory.getInstance(stateMachineStateService).triggerStateTransferWithTransaction(stateId, "APPROVAL_2_ISSUED", null);
        StateMachineEngineFactory.getInstance(stateMachineStateService).triggerStateTransferWithTransaction(stateId, "ISSUED_2_END", null);
	}

}
