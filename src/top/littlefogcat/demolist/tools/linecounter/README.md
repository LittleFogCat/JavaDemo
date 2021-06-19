想看看这段时间写了多少行代码，写了一个小工具`LineCounter`，可以计算出文件夹中所有指定格式文件的行数。

**用法：**

使用Builder创建对象，所有选项都是非必需项。最后调用`count()`进行统计并返回结果。
```java
LineCounter.CountResult result = new LineCounter.Builder("C:\\Repositories") // 可以有多个
        .suffix(".java", ".kt", ".xml") // 要统计的后缀，留空则统计所有
        .exclude(".idea", "build", "generated") // 忽略的文件/文件夹
        .granularity(LineCounter.Granularity.TYPE) // 统计的粒度，统计结果以文件/类型/总计为单位，默认TYPE
        .strict(true) // 是否过滤无效行，默认否
        .filter(null) // 过滤无效行规则，留空则使用默认规则
        .name("default name") // 当前统计器名称，在打印时使用，留空则为统计的文件夹路径
        .printDetail(false) // 打印时是否打印具体统计结果HashMap，默认否
        .printAfterCount(true) // 是否在统计之后打印，默认是
        .countUseTime(true) // 是否打印统计用时，默认否
        .count(); // 创建对象并进行统计
```
打印结果示例：
> ------ 常规统计 ------
> 总文件数：1863，有效代码行数：57182，空白行数：10069，注释行数：10015，用时：1150ms
