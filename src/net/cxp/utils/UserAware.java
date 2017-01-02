package net.cxp.utils;

import net.cxp.entity.Users;



/*
 * Simple interface for actions that want have the user object injected.  
 */

public interface UserAware {
	
	public void setUser( Users user );
	
}