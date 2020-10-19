/*     Copyright 2017 SWI Kommunikations- und Computer GmbH
*
*        Licensed under the Apache License, Version 2.0 (the "License");
*        you may not use this file except in compliance with the License.
*        You may obtain a copy of the License at
*
*        http://www.apache.org/licenses/LICENSE-2.0
*
*        Unless required by applicable law or agreed to in writing, software
*       distributed under the License is distributed on an "AS IS" BASIS,
*        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*        See the License for the specific language governing permissions and
*        limitations under the License.
*/

package com.coherentreceiver.analytics.helper.threading;

/**
 *
 */

import com.coherentreceiver.analytics.fetcher.service.TaskType;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.*;

//for the custom implementation without Spring
public class ThreadPoolManager
{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ThreadPoolManager.class);

    private int THREAD_POOL_SIZE = 10;

    private HashMap<ScheduledFuture<?>, TaskType> tasks = new HashMap<ScheduledFuture<?>,TaskType>();

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);

    public ThreadPoolManager()
    {
    }

    public ScheduledFuture<?> scheduleFixedRate( TaskType tasktype,
                                                 Runnable command,
                                                 long period,
                                                 TimeUnit unit )
            throws RejectedExecutionException
    {

        ScheduledFuture<?> task =
                executor.scheduleAtFixedRate(command, 0, period, unit);


        tasks.put(task, tasktype);

        return task;
    }

    public void scheduleOnce( Runnable command, long delay, TimeUnit unit )
            throws RejectedExecutionException
    {

        executor.schedule( command, delay, unit );
    }

    public boolean cancel ( ScheduledFuture<?> task )
    {
        boolean success = task.cancel( true );

        tasks.remove( task );

        return success;
    }

    public void shutdown (){
        executor.shutdown();
    }


    public int getTaskCount(TaskType type )
    {
        int count = 0;

        for( TaskType current: tasks.values() )
        {
            if( current == type )
            {
                count++;
            }
        }

        return count;
    }


}
