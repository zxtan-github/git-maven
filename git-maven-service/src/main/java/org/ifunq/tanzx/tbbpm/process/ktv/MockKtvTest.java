package org.ifunq.tanzx.tbbpm.process.ktv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taobao.tbbpm.define.BpmDefineConfig;
import com.taobao.tbbpm.define.BpmDefineFactory;
import com.taobao.tbbpm.define.IBpmDefine;
import com.taobao.tbbpm.process.ProcessEngine;
import com.taobao.tbbpm.process.ProcessEngineFactory;

public class MockKtvTest {

	public static void main(String[] args) throws Exception {
		String code = "org.ifunq.tanzx.tbbpm.process.tanzx_statemachine";
		IBpmDefine define = BpmDefineFactory.loadBpmDefine(code, BpmDefineConfig.LOAD_FROM_FILE);
		ProcessEngine engine = ProcessEngineFactory.getProcessEngine();
		engine.preCompile(code);
		Map<String, Object> context = new HashMap<String, Object>();
		List<String> pList = new ArrayList<String>();
		pList.add("wuxiang");
		pList.add("junyu");
		pList.add("xuannan");
		pList.add("babo");
		pList.add("fasheng");
		pList.add("zuozhu");
		pList.add("dongkuang");
//		pList.add("kongxuan");
//		pList.add("zongwu");
//		pList.add("yunshu");
//		pList.add("jishao");
//		pList.add("tzx");
//		pList.add("tcy");
		context.put("pList", pList);
		engine.start(code, context);
//		System.out.println("process return value price： "+ engine.start(code, context).get("price"));
		//generate SVG file
//		String svg = GraphFactory.toSvg(define, null);
//		FileWriter svgFile = new FileWriter("d:/bpmsvg.svg");
//		svgFile.write(svg);
//		svgFile.flush();

	}
}
