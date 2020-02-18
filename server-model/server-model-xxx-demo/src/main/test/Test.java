import com.vma.assist.tools.ModelTool;
import com.vma.assist.tools.MysqlTool;
import com.vma.assist.wraps.SystemWrap;

import java.io.IOException;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/4/15.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        generalTableField();
    }

    /**
     * 生成table的字段
     *
     * @throws IOException
     */
    private static void generalTableField() throws IOException {
        List<MysqlTool.TableInfo> tableInfoList = MysqlTool.getTableList(
                "jdbc:mysql://114.118.97.41:3307/scrm_business?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true",
                "chenz",
                "1hM9zxOsrpb#"
        );
        String path = SystemWrap.getProperty("user.dir") + "/app-model/app-model-xxx-demo/src/main/java/com/vma/test";
        for (MysqlTool.TableInfo tableInfo : tableInfoList) {
            ModelTool.generalModelTable(tableInfo,
                    path + "/model/${lowerTableName}/table/${beanName}Table.java",
                    "https://gitee.com/810422646/vma-resources/raw/master/ModelTemplate.txt");
        }
    }
}
