/*******************************************************************************
 * Copyright (c) 2000, 2002 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.websphere.csi;

/**
 * PoolDiscardStrategy is the abstract mechanism for hooking
 * application-specific processing into a Pool's eviction
 * mechanism. For each object evicted from the pool the
 * PoolDiscardStrategy is notified.
 * The notification occurs after the object has been removed from the
 * pool.
 * 
 * This hook may be used for clean up of other data structures or any
 * other purpose.
 * 
 * <b>Note: this interface is deprecated. Use the PooledObject interface instead.</b>
 * 
 * @deprecated
 * @see Pool
 * @see PoolManager
 * @see PooledObject
 * 
 */

public interface PoolDiscardStrategy
{
    /**
     * Called by the pool after it discards an object from the pool.
     * This gives the implementation an opportunity to perform any
     * required clean up.
     * 
     * @param object The object which was discarded.
     * 
     */

    public void discard(Object object);
}
