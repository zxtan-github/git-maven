package org.ifunq.tanzx.tbbpm.process.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taobao.tbbpm.define.BpmDefineConfig;
import com.taobao.tbbpm.define.BpmDefineFactory;
import com.taobao.tbbpm.define.IBpmDefine;
import com.taobao.tbbpm.process.ProcessEngine;
import com.taobao.tbbpm.process.ProcessEngineFactory;

public class OrderMockTest {

	public static void main(String[] args) throws Exception {
//		String code = "org.ifunq.tanzx.tbbpm.process.tanzx_statemachine";
		String code = "org.ifunq.tanzx.tbbpm.process.tanzx_process01";
		IBpmDefine define = BpmDefineFactory.loadBpmDefine(code, BpmDefineConfig.LOAD_FROM_FILE);
		ProcessEngine engine = ProcessEngineFactory.getProcessEngine();
		engine.preCompile(code);
		Map<String, Object> context = new HashMap<String, Object>();
		List<String> pList = new ArrayList<String>();
		pList.add("xia1Dan");
		pList.add("chuKu");
		pList.add("peiSong");
		context.put("pList", pList);
		Map<String, Object> reslutMap = engine.start(code, context);
		System.out.println("result---->" + (Boolean) reslutMap.get("result"));
	}

}
