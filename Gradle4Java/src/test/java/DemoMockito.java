import org.junit.Test;

import java.util.LinkedList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * 使用mockito 框架进行测试，参见: 8 unit test.ppt
 */
public class DemoMockito {
    @Test
    public void mockObject() {
        // 模拟LinkedList 的一个对象
        LinkedList mockedList = mock(LinkedList.class);

        // 此时调用get方法，会返回null，因为还没有对方法调用的返回值做模拟
        System.out.println(mockedList.get(0));
    }

    @Test
    public void mockReturnValue() {
        LinkedList mockedList = mock(LinkedList.class);
        // 模拟获取第一个元素时，返回字符串first。  给特定的方法调用返回固定值在官方说法中称为stub。
        when(mockedList.get(0)).thenReturn("first");
        // 此时打印输出first
        System.out.println(mockedList.get(0));
    }

    @Test(expected = RuntimeException.class)
    public void mockThrowException() {
        LinkedList mockedList = mock(LinkedList.class);

        // 模拟获取第二个元素时，抛出RuntimeException
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        // 此时将会抛出RuntimeException
        System.out.println(mockedList.get(1));
    }

    @Test
    public void mockMethodArgs() {
        LinkedList mockedList = mock(LinkedList.class);
        // anyInt()匹配任何int参数，这意味着参数为任意值，其返回值均是element
        when(mockedList.get(anyInt())).thenReturn("element");
        // 此时打印是element
        System.out.println(mockedList.get(999));
    }

    @Test
    public void mockCallMethod() {
        LinkedList mockedList = mock(LinkedList.class);
        // 调用add一次
        mockedList.add("once");
        // 下面两个写法验证效果一样，均验证add方法是否被调用了一次
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");
    }
}
