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

package com.orangelabs.rcs.service.api.client.contacts;

import android.os.Parcel;
import android.os.Parcelable;

import com.orangelabs.rcs.service.api.client.capability.Capabilities;
import com.orangelabs.rcs.service.api.client.presence.PresenceInfo;

/**
 * Contact info
 */
public class ContactInfo implements Parcelable {
    /**
     * The contact is RCS capable but there is no special presence relationship with the user
     */
    public final static String RCS_CAPABLE = "rcs_capable";
    
    /**
     * The contact is not RCS
     */
    public final static String NOT_RCS = "not_rcs";

	/**
	 * Capabilities
	 */
	private Capabilities capabilities = null;
	
	/**
	 * Presence info, relevant only if social info is activated for this contact
	 */
	private PresenceInfo presenceInfo = null;
	
	/**
	 * Contact
	 */
	private String contact = null;
	
	/**
	 * Registration state
	 */
	private boolean isRegistered = false;
	
	/**
	 * RCS status
	 */
	private String rcsStatus = ContactInfo.NOT_RCS;
	
	/**
	 * RCS status timestamp
	 */
	private long rcsStatusTimestamp = 0L;
	
    /**
	 * Constructor
	 */
	public ContactInfo() {
	}

	/**
	 * Constructor
	 * 
	 * @param source Parcelable source
	 */
	public ContactInfo(Parcel source) {
		byte flag = source.readByte();
		if (flag > 0) {
			this.capabilities = Capabilities.CREATOR.createFromParcel(source);
		} else {
			this.capabilities = null;
		}
		
		flag = source.readByte();
		if (flag > 0) {
			this.presenceInfo = PresenceInfo.CREATOR.createFromParcel(source);
		} else {
			this.presenceInfo = null;
		}
		
		contact = source.readString();
		
		rcsStatus = source.readString();
		
		rcsStatusTimestamp = source.readLong();
		
		isRegistered = source.readInt() != 0;
    }
	
	/**
	 * Describe the kinds of special objects contained in this Parcelable's
	 * marshalled representation
	 * 
	 * @return Integer
	 */
	public int describeContents() {
        return 0;
    }

	/**
	 * Write parcelable object
	 * 
	 * @param dest The Parcel in which the object should be written
	 * @param flags Additional flags about how the object should be written
	 */
    public void writeToParcel(Parcel dest, int flags) {
    	dest.writeParcelable(capabilities, flags);
    	dest.writeParcelable(presenceInfo, flags);
    	dest.writeString(contact);
    	dest.writeString(rcsStatus);
    	dest.writeLong(rcsStatusTimestamp);
    	dest.writeInt(isRegistered ? 1 : 0);
    }

    /**
     * Parcelable creator
     */
    public static final Parcelable.Creator<ContactInfo> CREATOR
            = new Parcelable.Creator<ContactInfo>() {
        public ContactInfo createFromParcel(Parcel source) {
            return new ContactInfo(source);
        }

        public ContactInfo[] newArray(int size) {
            return new ContactInfo[size];
        }
    };	

    /**
	 * Set the capabilities
	 * 
	 * @param capabilities Capabilities
	 */
	public void setCapabilities(Capabilities capabilities) {
		this.capabilities = capabilities;
	}
	
	/**
	 * Returns the capabilities
	 * 
	 * @return Capabilities
	 */
	public Capabilities getCapabilities(){
		return capabilities;
	}
	
    /**
	 * Set the presence info
	 * 
	 * @param info Presence info
	 */
	public void setPresenceInfo(PresenceInfo info) {
		this.presenceInfo = info;
	}
	
	/**
	 * Returns the presence info
	 * 
	 * @return PresenceInfo
	 */
	public PresenceInfo getPresenceInfo(){
		return presenceInfo;
	}

    /**
	 * Set the contact
	 * 
	 * @param contact Contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * Returns the contact
	 * 
	 * @return contact
	 */
	public String getContact(){
		return contact;
	}
	
    /**
	 * Set the RCS status
	 * 
	 * @param rcsStatus RCS status
	 */
	public void setRcsStatus(String rcsStatus) {
		this.rcsStatus = rcsStatus;
	}
	
	/**
	 * Returns the RCS status
	 * 
	 * @return rcsStatus
	 */
	public String getRcsStatus(){
		return rcsStatus;
	}
	
    /**
	 * Set the registration state
	 * 
	 * @param boolean registrationState
	 */
	public void setRegistrationState(boolean registrationState) {
		this.isRegistered = registrationState;
	}
	
	/**
	 * Returns the registration state
	 * 
	 * @return isRegistered
	 */
	public boolean isRegistered(){
		return isRegistered;
	}
	
    /**
	 * Set the RCS status timestamp
	 * 
	 * @param timestamp Last RCS status date of change
	 */
	public void setRcsStatusTimestamp(long timestamp) {
		this.rcsStatusTimestamp = timestamp;
	}
	
	/**
	 * Returns the RCS status timestamp
	 * 
	 * @return timestamp
	 */
	public long getRcsStatusTimestamp(){
		return rcsStatusTimestamp;
	}
	
	/**
	 * Returns a string representation of the object
	 * 
	 * @return String
	 */
	public String toString() {
		String result =  "- Contact: " + contact + "\n" +
			"- RCS Status: " + rcsStatus + "\n" +
			"- RCS status timestamp: " + rcsStatusTimestamp + "\n";
		if (capabilities != null) {
			result += "- Capabilities: " + capabilities.toString() + "\n";
		}
		if (presenceInfo != null) {
			result += "- Presence info: " + presenceInfo.toString() + "\n";
		}
		return result;
	}
}