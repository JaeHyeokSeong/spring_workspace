package hello.jdbc.connection;

/**
 * h2 database 연결과정에서 필요한 상수 정의
 */
public abstract class ConnectionConst {
    public static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";
}
