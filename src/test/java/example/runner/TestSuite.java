package example.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Runner.class,
        Runner2.class
}) // Thêm tất cả các class runner bạn muốn chạy vào đây
public class TestSuite {
    // Không cần thêm bất kỳ phương thức nào ở đây
}
