package callback;

@FunctionalInterface //있으면 compile 시점에 함수형 인터페이스 조건을 충족하는지 확인할 수 있다.
public interface CallBack {
    void call();
}
