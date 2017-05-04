package org.ifunq.tanzx.tbbpm.machine.simpleflow;

public class SimpleFlowHandleServiceImpl implements SimpleFlowHandleService {

	@Override
	public void submitedBefore(String request) throws Exception {
		System.out.println("submitedBefore");
		System.out.println(request);
		
	}

	@Override
	public void submitedAfter(String request) throws Exception {
		System.out.println("submitedAfter");
		System.out.println(request);
//		throw new Exception();
		
	}

	@Override
	public void approvalBefore(String request) throws Exception {
		System.out.println("approvalBefore");
//		throw new Exception();
	}
	
	@Override
	public void approvalBefore2() throws Exception {
		System.out.println("approvalBefore2");
		throw new Exception();
	}

	@Override
	public void approvalAfter() throws Exception  {
		System.out.println("approvalAfter");
		
	}

	@Override
	public void issuedBefore() throws Exception  {
		System.out.println("issuedBefore");
		
	}

	@Override
	public void issuedAfter() throws Exception  {
		System.out.println("issuedAfter");
		
	}

}
