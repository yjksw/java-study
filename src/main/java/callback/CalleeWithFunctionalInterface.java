package callback;

import java.util.function.Consumer;

public class CalleeWithFunctionalInterface {

    private Consumer<String> callback;

    //어느 형태의 함수형 인터페이스가 들어가도 callback 함수로 작용할 수 있다.
    public CalleeWithFunctionalInterface(Consumer<String> callback) {
        this.callback = callback;
    }

    public void doService(String callBackMessage) {
        System.out.println("Do this first before call Callback function");

        callback.accept(callBackMessage);
    }
}
