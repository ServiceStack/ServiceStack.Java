//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

public abstract class AsyncResultVoid {
    boolean completed = false;
    Exception ex;

    public final Exception getError() { return ex; }
    public final void setError(Exception value) {
        completed = true;
        ex = value;
    }

    public final void completeResult(){
        try {
            if (ex == null){
                success();
            }
            else {
                error(ex);
            }
        } finally {
            complete();
        }
    }

    public void success(){}
    public void error(Exception ex){}
    public void complete(){}
}
