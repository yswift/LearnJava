import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
// 无参数构造函数
@NoArgsConstructor
// 所有属性构造函数
@AllArgsConstructor
// 只用学号比较相等
@EqualsAndHashCode(exclude = {"name", "age"})
public class Student {
    private String no;
    private String name;
    private int age;
}
