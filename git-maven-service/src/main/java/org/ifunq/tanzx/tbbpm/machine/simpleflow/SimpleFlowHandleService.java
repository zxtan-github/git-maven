package org.ifunq.tanzx.tbbpm.machine.simpleflow;

public interface SimpleFlowHandleService {

	public void submitedBefore(String request) throws Exception ;
	public void submitedAfter(String request) throws Exception ;

	public void approvalBefore(String request) throws Exception ;
	public void approvalBefore2() throws Exception ;
	public void approvalAfter() throws Exception ;

	public void issuedBefore() throws Exception ;
	public void issuedAfter() throws Exception ;

}
