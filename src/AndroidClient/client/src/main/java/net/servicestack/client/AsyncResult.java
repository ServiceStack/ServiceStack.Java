//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

public abstract class AsyncResult<T> implements AsyncSuccess<T>, AsyncError, AsyncComplete {
    T result;
    boolean completed = false;
    Exception ex;

    public final T getResult(){ return result; }
    public final void setResult(T value) {
        completed = true;
        result = value;
    }

    public final Exception getError() { return ex; }
    public final void setError(Exception value) {
        completed = true;
        ex = value;
    }

    public final void completeResult(T value){
        try {
            if (ex == null){
                setResult(value);
                success(value);
            }
            else {
                error(ex);
            }
        } finally {
            complete();
        }
    }

    public void success(T response){}
    public void error(Exception ex){}
    public void complete(){}
}
