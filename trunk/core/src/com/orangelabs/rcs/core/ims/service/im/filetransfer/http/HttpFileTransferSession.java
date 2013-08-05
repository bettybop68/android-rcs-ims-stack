/*******************************************************************************
 * Software Name : RCS IMS Stack
 *
 * Copyright (C) 2010 France Telecom S.A.
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

package com.orangelabs.rcs.core.ims.service.im.filetransfer.http;

import com.orangelabs.rcs.core.content.MmContent;
import com.orangelabs.rcs.core.ims.protocol.sip.SipException;
import com.orangelabs.rcs.core.ims.protocol.sip.SipRequest;
import com.orangelabs.rcs.core.ims.service.ImsService;
import com.orangelabs.rcs.core.ims.service.ImsServiceError;
import com.orangelabs.rcs.core.ims.service.im.filetransfer.FileSharingError;
import com.orangelabs.rcs.core.ims.service.im.filetransfer.FileSharingSession;
import com.orangelabs.rcs.core.ims.service.im.filetransfer.FileSharingSessionListener;
import com.orangelabs.rcs.utils.logger.Logger;

/**
 * Abstract file transfer HTTP session
 *
 * @author jexa7410
 */
public abstract class HttpFileTransferSession extends FileSharingSession {

    /**
     * Chat session ID
     */
    private String chatSessionId = null;

    /**
     * The logger
     */
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    /**
	 * Constructor
	 *
	 * @param parent IMS service
	 * @param content Content to be shared
	 * @param contact Remote contact
	 * @param thumbnail Thumbnail
	 * @param chatSessionId Chat session ID
	 */
	public HttpFileTransferSession(ImsService parent, MmContent content, String contact, byte[] thumbnail, String chatSessionID) {
		super(parent, content, contact, thumbnail);
		
		this.chatSessionId = chatSessionID;
	}
	

	/**
	 * Returns the chat session ID associated to the transfer
	 * 
	 * @return the chatSessionID
	 */
	public String getChatSessionID() {
		return chatSessionId;
	}

    /**
     * Create an INVITE request
     *
     * @return the INVITE request
     * @throws SipException 
     */
    public SipRequest createInvite() throws SipException {
    	// Not used here
    	return null;
    }

    /**
     * Handle error 
     * 
     * @param error Error
     */
    public void handleError(ImsServiceError error) {
        if (isInterrupted()) {
            return;
        }

        // Error
        if (logger.isActivated()) {
            logger.info("Transfer error: " + error.getErrorCode() + ", reason=" + error.getMessage());
        }

        // Remove the current session
        getImsService().removeSession(this);

        // Notify listeners
        for(int j=0; j < getListeners().size(); j++) {
            ((FileSharingSessionListener)getListeners().get(j)).handleTransferError(new FileSharingError(error));
        }
    }
	
    /**
     * Prepare media session
     * 
     * @throws Exception 
     */
    public void prepareMediaSession() throws Exception {
    	// Not used here
    }

    /**
     * Start media session
     * 
     * @throws Exception 
     */
    public void startMediaSession() throws Exception {
    	// Not used here
    }

    /**
     * Close media session
     */
    public void closeMediaSession() {
    	// Not used here
    }

    /**
     * Handle file transfered
     */
    public void handleFileTransfered() {
        // File has been transfered
        fileTransfered();

        // Remove the current session
        getImsService().removeSession(this);

        // Notify listeners
        for (int j = 0; j < getListeners().size(); j++) {
            ((FileSharingSessionListener) getListeners().get(j))
                    .handleFileTransfered(getContent().getUrl());
        }
    }

    /**
     * HTTP transfer progress
     *
     * @param currentSize Current transfered size in bytes
     * @param totalSize Total size in bytes
     */
    public void httpTransferProgress(long currentSize, long totalSize) {
        // Notify listeners
        for(int j=0; j < getListeners().size(); j++) {
            ((FileSharingSessionListener)getListeners().get(j)).handleTransferProgress(currentSize, totalSize);
        }
    }
}