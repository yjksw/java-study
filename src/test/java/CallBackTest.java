import static org.assertj.core.api.Assertions.assertThat;

import callback.CallBack;
import callback.Callee;
import callback.CalleeWithFunctionalInterface;
import callback.PhoneCall;
import callback.VideoCall;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CallBackTest {

    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @DisplayName("Callback 함수형 인터페이스를 상속하는 구현체로 콜백수행")
    @Test
    void phoneCall_CallBack() {
        // given
        Callee callee = new Callee();
        callee.setCallBack(new PhoneCall()); //phoneCall 이라는 함수형 인터페이스가 콜백 함수로 주입된다.
        String expected = "Do this first before call Callback function\nPhone is ringing!!\n";

        // when
        callee.doService(); //callee

        // then
        assertThat(outputStream.toString()).isEqualTo(expected);
    }

    @DisplayName("Callback 함수형 인터페이스를 상속하는 구현체로 콜백수행")
    @Test
    void videoCall_CallBack() {
        // given
        Callee callee = new Callee();
        callee.setCallBack(new VideoCall()); //videoCall 이라는 함수형 인터페이스가 콜백 함수로 주입된다.
        String expected = "Do this first before call Callback function\nVideo is ringing!!\n";

        // when
        callee.doService(); //callee

        // then
        assertThat(outputStream.toString()).isEqualTo(expected);
    }

    @DisplayName("Callback 메서드 오버라이드로 콜백 수행")
    @Test
    void methodOverride_CallBack() {
        // given
        Callee callee = new Callee();
        callee.setCallBack(new CallBack() {
            @Override
            public void call() {
                System.out.println("New CallBack!!!");
            }
        });

        String expected = "Do this first before call Callback function\nNew CallBack!!!\n";

        // when
        callee.doService();

        // then
        assertThat(outputStream.toString()).isEqualTo(expected);
    }

    @DisplayName("Consumer Functional Interface로 callback 구현하기")
    @Test
    void consumerFunctionalInterface_Callback() {
        // given
        Consumer<String> callback = (String s) -> {
            System.out.println(s);
        };

        CalleeWithFunctionalInterface callee = new CalleeWithFunctionalInterface(callback);
        String callBackMessage = "This calls after callee by consumer.";
        String expected = "Do this first before call Callback function\nThis calls after callee by consumer.\n";

        // when
        callee.doService(callBackMessage);

        // then
        assertThat(outputStream.toString()).isEqualTo(expected);
    }

    @DisplayName("Consumer Functional Interface로 callback 구현하기 - 람다식 매변수형 생략")
    @Test
    void consumerFunctionalInterface_WithoutArgumentType_Callback() {
        // given
        CalleeWithFunctionalInterface callee = new CalleeWithFunctionalInterface(
            s -> System.out.println(s)
        );

        String callBackMessage = "This calls after callee by consumer.";
        String expected = "Do this first before call Callback function\nThis calls after callee by consumer.\n";

        // when
        callee.doService(callBackMessage);

        // then
        assertThat(outputStream.toString()).isEqualTo(expected);
    }

    @DisplayName("Consumer Functional Interface로 callback 구현하기 - 람다식 메서드 참 생략")
    @Test
    void consumerFunctionalInterface_MethodReference_Callback() {
        // given
        CalleeWithFunctionalInterface callee = new CalleeWithFunctionalInterface(System.out::println);

        String callBackMessage = "This calls after callee by consumer.";
        String expected = "Do this first before call Callback function\nThis calls after callee by consumer.\n";

        // when
        callee.doService(callBackMessage);

        // then
        assertThat(outputStream.toString()).isEqualTo(expected);
    }
}
