package callback;

import java.util.function.Consumer;

public class Callee {

    private CallBack callBack;

    public Callee() {
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void doService() {
        System.out.println("Do this first before call Callback function");

        callBack.call();
    }
}
