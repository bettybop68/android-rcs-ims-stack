/*******************************************************************************
 * Software Name : RCS IMS Stack
 *
 * Copyright © 2010 France Telecom S.A.
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

package com.orangelabs.rcs.service.api.client.messaging;

import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.orangelabs.rcs.service.api.client.ClientApi;
import com.orangelabs.rcs.service.api.client.ClientApiException;
import com.orangelabs.rcs.service.api.client.CoreServiceNotAvailableException;

/**
 * Messaging API
 * 
 * @author jexa7410
 */
public class MessagingApi extends ClientApi {

	/**
	 * Core service API
	 */
	private IMessagingApi coreApi = null;
	
	/**
     * Constructor
     * 
     * @param ctx Application context
     */
    public MessagingApi(Context ctx) {
    	super(ctx);
    }
    
    /**
     * Connect API
     */
    public void connectApi() {
    	super.connectApi();

    	ctx.bindService(new Intent(IMessagingApi.class.getName()), apiConnection, 0);
    }
    
    /**
     * Disconnect API
     */
    public void disconnectApi() {
    	super.disconnectApi();
    	
    	ctx.unbindService(apiConnection);
    }

    /**
     * Returns the core service API
     * 
     * @return API
     */
	public IMessagingApi getCoreServiceApi() {
		return coreApi;
	}
    
	/**
	 * Core service API connection
	 */
	private ServiceConnection apiConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            coreApi = IMessagingApi.Stub.asInterface(service);

            // Notify event listener
            notifyEventApiConnected();
        }

        public void onServiceDisconnected(ComponentName className) {
            // Notify event listener
        	notifyEventApiDisconnected();

        	coreApi = null;
        }
    };
    
	/**
     * Transfer a file
     *
     * @param contact Contact
     * @param file File to be transfered
	 * @return File transfer session
     * @throws ClientApiException
     */
    public IFileTransferSession transferFile(String contact, String file) throws ClientApiException {	
    	if (coreApi != null) {
			try {
				IFileTransferSession session = coreApi.transferFile(contact, file);
		    	return session;
			} catch(Exception e) {
				throw new ClientApiException(e.getMessage());
			}
		} else {
			throw new CoreServiceNotAvailableException();
		}
    }

	/**
	 * Get the file transfer session from its session ID
	 * 
	 * @param id Session ID
	 * @return Session
	 * @throws ClientApiException
	 */
	public IFileTransferSession getFileTransferSession(String id) throws ClientApiException {
    	if (coreApi != null) {
			try {
		    	return coreApi.getFileTransferSession(id);
			} catch(Exception e) {
				throw new ClientApiException(e.getMessage());
			}
		} else {
			throw new CoreServiceNotAvailableException();
		}
	}
	
	/**
	 * Get list of file transfer sessions with a contact
	 * 
	 * @param contact Contact
	 * @return List of sessions
	 * @throws ClientApiException
	 */
	public List<IBinder> getFileTransferSessionsWith(String contact) throws ClientApiException {
    	if (coreApi != null) {
			try {
		    	return coreApi.getFileTransferSessionsWith(contact);
			} catch(Exception e) {
				throw new ClientApiException(e.getMessage());
			}
		} else {
			throw new CoreServiceNotAvailableException();
		}
    }
	
	/**
	 * Get list of current established file transfer sessions
	 * 
	 * @return List of sessions
	 * @throws ClientApiException
	 */
	public List<IBinder> getFileTransferSessions() throws ClientApiException {
    	if (coreApi != null) {
			try {
		    	return coreApi.getFileTransferSessions();
			} catch(Exception e) {
				throw new ClientApiException(e.getMessage());
			}
		} else {
			throw new CoreServiceNotAvailableException();
		}
	}	

	/**
	 * Initiate a one-to-one chat session
	 * 
     * @param contact Contact
     * @param subject Subject of the conference
	 * @return Chat session
	 * @throws ClientApiException
	 */
	public IChatSession initiateOne2OneChatSession(String contact, String subject) throws ClientApiException {
    	if (coreApi != null) {
			try {
		    	return coreApi.initiateOne2OneChatSession(contact, subject);
			} catch(Exception e) {
				throw new ClientApiException(e.getMessage());
			}
		} else {
			throw new CoreServiceNotAvailableException();
		}
	}

	/**
	 * Initiate an ad-hoc group chat session
	 * 
     * @param participants List of participants
     * @param subject Subject of the conference
	 * @return Chat session
	 * @throws ClientApiException
	 */
	public IChatSession initiateAdhocGroupChatSession(List<String> participants, String subject) throws ClientApiException {
    	if (coreApi != null) {
			try {
		    	return coreApi.initiateAdhocGroupChatSession(participants, subject);
			} catch(Exception e) {
				throw new ClientApiException(e.getMessage());
			}
		} else {
			throw new CoreServiceNotAvailableException();
		}
	}
	
	/**
	 * Get a chat session from its session ID
	 * 
	 * @param id Session ID
	 * @return Session
	 * @throws ClientApiException
	 */
	public IChatSession getChatSession(String id) throws ClientApiException {
    	if (coreApi != null) {
			try {
		    	return coreApi.getChatSession(id);
			} catch(Exception e) {
				throw new ClientApiException(e.getMessage());
			}
		} else {
			throw new CoreServiceNotAvailableException();
		}
	}	
	
	/**
	 * Get list of chat sessions with a contact
	 * 
	 * @param contact Contact
	 * @return Session
	 * @throws ClientApiException
	 */
	public List<IBinder> getChatSessionsWith(String contact) throws ClientApiException {
    	if (coreApi != null) {
			try {
		    	return coreApi.getChatSessionsWith(contact);
			} catch(Exception e) {
				throw new ClientApiException(e.getMessage());
			}
		} else {
			throw new CoreServiceNotAvailableException();
		}
	}

	/**
	 * Get list of current established chat sessions
	 * 
	 * @return List of sessions
	 * @throws ClientApiException
	 */
	public List<IBinder> getChatSessions() throws ClientApiException {
		if (coreApi != null) {
			try {
		    	return coreApi.getChatSessions();
			} catch(Exception e) {
				throw new ClientApiException(e.getMessage());
			}
		} else {
			throw new CoreServiceNotAvailableException();
		}
	}
}