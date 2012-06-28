/*******************************************************************************
 * Software Name : RCS IMS Stack
 * Version : 2.0.0
 * 
 * Copyright � 2010 France Telecom S.A.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.orangelabs.rcs.core.ims.protocol.sip;

import java.util.Vector;

import com.orangelabs.rcs.core.ims.service.SessionAuthenticationAgent;


/**
 * SIP dialog path. A dialog path corresponds to a SIP session, for
 * example from the INVITE to the BYE.
 * 
 * @author JM. Auffret
 */
public class SipDialogPath {
	
	/**
	 * SIP stack
	 */
	private SipStack stack = null;
	
	/**
	 * Call-Id
	 */
	private String callId = null;

	/**
	 * CSeq number
	 */
	private int cseq = 1;

	/**
	 * Local tag
	 */
	private String localTag = IdGenerator.getIdentifier();

	/**
	 * Remote tag
	 */
	private String remoteTag = null;

	/**
	 * Target
	 */
	private String target = null;

	/**
	 * Local party
	 */
	private String localParty = null;

	/**
	 * Remote party
	 */
	private String remoteParty = null;

	/**
	 * Initial INVITE request
	 */
	private SipRequest invite = null;

	/**
	 * Local SDP part
	 */
	private String localSdp = null;

	/**
	 * Remote SDP part
	 */
	private String remoteSdp = null;
	
	/**
	 * Route path
	 */
	private Vector<String> route = null;

	/**
	 * Authentication agent
	 */
	private SessionAuthenticationAgent authenticationAgent = null;

	/**
	 * Flag that indicates if the signalisation is established or not
	 */
	private boolean sigEstablished = false;

	/**
	 * Flag that indicates if the session (sig + media) is established or not
	 */
	private boolean sessionEstablished = false;

	/**
	 * Flag that indicates if the session has been cancelled by the end-user
	 */
	private boolean sessionCancelled = false;

	/**
	 * Flag that indicates if the session has been terminated by the server
	 */
	private boolean sessionTerminated = false;

	/**
	 * Constructor
	 * 
	 * @param stack SIP stack
	 * @param callId Call-Id
	 * @param cseq CSeq
	 * @param target Target
	 * @param localParty Local party
	 * @param remoteParty Remote party
	 * @param route Route path
	 */
	public SipDialogPath(SipStack stack,
			String callId,
			int cseq,
			String target,			
			String localParty,
			String remoteParty,
			Vector<String> route) {
		this.stack = stack;
		this.callId = callId;
		this.cseq = cseq;
		this.target = target;
		this.localParty = localParty;
		this.remoteParty = remoteParty;
		this.route = route;
	}

	/**
	 * Get the current SIP stack
	 * 
	 * @return SIP stack
	 */
	public SipStack getSipStack() {
		return stack;
	}
	
	/**
	 * Get the target of the dialog path
	 * 
	 * @return String
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * Set the target of the dialog path
	 * 
	 * @param tg Target address
	 */
	public void setTarget(String tg) {
		target = tg;
	}

	/**
	 * Get the local party of the dialog path
	 * 
	 * @return String
	 */
	public String getLocalParty() {
		return localParty;
	}

	/**
	 * Get the remote party of the dialog path
	 * 
	 * @return String
	 */
	public String getRemoteParty() {
		return remoteParty;
	}

	/**
	 * Get the local tag of the dialog path
	 * 
	 * @return String
	 */
	public String getLocalTag() {
		return localTag;
	}

	/**
	 * Get the remote tag of the dialog path
	 * 
	 * @return String
	 */
	public String getRemoteTag() {
		return remoteTag;
	}

	/**
	 * Set the remote tag of the dialog path
	 * 
	 * @param tag Remote tag
	 */
	public void setRemoteTag(String tag) {
		remoteTag = tag;
	}

	/**
	 * Get the call-id of the dialog path
	 * 
	 * @return String
	 */
	public String getCallId() {
		return callId;
	}

	/**
	 * Return the Cseq number of the dialog path
	 * 
	 * @return Cseq number
	 */
	public int getCseq() {
		return cseq;
	}

	/**
	 * Increment the Cseq number of the dialog path
	 */
	public void incrementCseq() {
		cseq++;
	}

	/**
	 * Get the initial INVITE request of the dialog path
	 * 
	 * @return String
	 */
	public SipRequest getInvite() {
		return invite;
	}

	/**
	 * Set the initial INVITE request of the dialog path
	 * 
	 * @param invite INVITE request
	 */
	public void setInvite(SipRequest invite) {
		this.invite = invite;
	}
		
	/**
	 * Returns the local SDP
	 * 
	 * @return String
	 */
	public String getLocalSdp() {
		return localSdp;
	}

	/**
	 * Returns the remote SDP
	 * 
	 * @return String
	 */
	public String getRemoteSdp() {
		return remoteSdp;
	}

	/**
	 * Sets the local SDP
	 * 
	 * @param localSdp The localSdp to set
	 */
	public void setLocalSdp(String localSdp) {
		this.localSdp = localSdp;
	}

	/**
	 * Sets the remote SDP
	 * 
	 * @param remoteSdp The remoteSdp to set
	 */
	public void setRemoteSdp(String remoteSdp) {
		this.remoteSdp = remoteSdp;
	}

	/**
	 * Returns the route path
	 * 
	 * @return Vector of string
	 */
	public Vector<String> getRoute() {
		return route;
	}

	/**
	 * Set the route path
	 * 
	 * @param route New route path
	 */
	public void setRoute(Vector<String> route) {
		this.route = route;
	}
	
	/**
	 * Is session cancelled
	 * 
	 * @return Boolean
	 */
	public boolean isSessionCancelled() {
		return sessionCancelled;
	}
	
	/**
	 * The session has been cancelled
	 */
	public synchronized void sessionCancelled() {
		this.sessionCancelled = true;
	}
	
	/**
	 * Is session established
	 * 
	 * @return Boolean
	 */
	public boolean isSessionEstablished() {
		return sessionEstablished;
	}
	
	/**
	 * Session is established
	 */
	public synchronized void sessionEstablished() {
		this.sessionEstablished = true;
	}
	
	/**
	 * Is session terminated
	 * 
	 * @return Boolean
	 */
	public boolean isSessionTerminated() {
		return sessionTerminated;
	}
	
	/**
	 * Session is terminated
	 */
	public synchronized void sessionTerminated() {
		this.sessionTerminated = true;
	}
	
	/**
	 * Is signalisation established with success
	 * 
	 * @return Boolean
	 */
	public boolean isSigEstablished() {
		return sigEstablished;
	}
	
	/**
	 * Signalisation is established with success
	 */
	public synchronized void sigEstablished() {
		this.sigEstablished = true;
	}

	/**
	 * Set the session authentication agent
	 * 
	 * @param agent Authentication agent
	 */
	public void setAuthenticationAgent(SessionAuthenticationAgent agent) {
		this.authenticationAgent = agent;
	}
	
	/**
	 * Returns the session authentication agent
	 * 
	 * @return Authentication agent
	 */
	public SessionAuthenticationAgent getAuthenticationAgent() {
		return authenticationAgent;
	}
}