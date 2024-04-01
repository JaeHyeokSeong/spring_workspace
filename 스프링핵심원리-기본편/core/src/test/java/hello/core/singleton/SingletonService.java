package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    // 외부에서 SingletonService 객체 만들지 X
    private SingletonService() {}

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
