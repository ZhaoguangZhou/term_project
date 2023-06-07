package Code5;

import java.sql.*;
import java.util.Arrays;
//排序见后面的方法
public class Work5 {
    public static void main(String[] args) throws Exception {
        String url="jdbc:mysql://localhost:3306/db1?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String user="root";
        String password="root";


        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();

        //-------第1题-------
        System.out.println("-------第1题-------");
        stmt.execute("insert into student values('s001','老大',20,'计算机学院')");
        stmt.execute("insert into student values('s002','老二',19,'计算机学院')");
        stmt.execute("insert into student values('s003','老三',18,'计算机学院')");
        stmt.execute("insert into student values('s004','老四',17,'计算机学院')");
        selectAll(stmt);

        //-------第2题-------
        System.out.println("-------第2题-------");
        selectAll(stmt);

        //-------第3题-------
        System.out.println("-------第3题-------");
        stmt.execute("delete from student where SNO='s004'");
        selectAll(stmt);


        //-------第4题-------
        System.out.println("-------第4题-------");
        ResultSet rs = stmt.executeQuery("select * from student where SNO='s003'");
        PrintResultSet(rs);
        //-------第5题-------
        System.out.println("-------第5题-------");
        stmt.executeUpdate("update student set College='通信学院' where SNO='s001'");
        selectAll(stmt);
    }
    public static void selectAll(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("select * from student");
        PrintResultSet(rs);
    }
    public static void PrintResultSet(ResultSet rs) throws SQLException {
        while (rs.next()){
            String SNO = rs.getString(1);
            String Name = rs.getString(2);
            int Age = rs.getInt(3);
            String College = rs.getString(4);
            System.out.println(SNO + ',' + Name + ',' + Age + ',' + College);
        }
    }

    //冒泡排序
    public static void BubbleSort(int[] intArray) {
        int temp = 0;
        Boolean swapped;
        for (int i = 0; i < intArray.length; i++) {
            swapped = false;
            for (int j = 0; j < intArray.length - 1 - i; j++)
                if (intArray[j] > intArray[j + 1]) {
                    temp = intArray[j];
                    intArray[j] = intArray[j + 1];
                    intArray[j + 1] = temp;
                    if (!swapped)
                        swapped = true;
                }
            if (!swapped)
                return;
        }
    }

    //插入排序
    public static void InsertSort(int[] array) {
        for(int i = 1;i < array.length;i++) {
            int temp = array[i];
            for(int j = i - 1;j >= 0;j--) {
                if(array[j] > temp) {
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
                else
                    break;
            }
        }
    }

    //快速排序
    public static int partition(int[] array, int low, int high) {
        // 取最后一个元素作为中心元素
        int pivot = array[high];
        // 定义指向比中心元素大的指针，首先指向第一个元素
        int pointer = low;
        // 遍历数组中的所有元素，将比中心元素大的放在右边，比中心元素小的放在左边
        for (int i = low; i < high; i++) {
            if (array[i] <= pivot) {
                // 将比中心元素小的元素和指针指向的元素交换位置
                // 如果第一个元素比中心元素小，这里就是自己和自己交换位置，指针和索引都向下一位移动
                // 如果元素比中心元素大，索引向下移动，指针指向这个较大的元素，直到找到比中心元素小的元素，并交换位置，指针向下移动
                int temp = array[i];
                array[i] = array[pointer];
                array[pointer] = temp;
                pointer++;
            }
            System.out.println(Arrays.toString(array));
        }
        // 将中心元素和指针指向的元素交换位置
        int temp = array[pointer ];
        array[pointer] = array[high];
        array[high] = temp;
        return pointer;
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // 获取划分子数组的位置
            int position = partition(array, low, high);
            // 左子数组递归调用
            quickSort(array, low, position -1);
            // 右子数组递归调用
            quickSort(array, position + 1, high);
        }
    }

    //合并排序
    public int[] sort(int[] sourceArray){
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }

    public int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

    //堆排序
    public static void HeapSort(int[] arr) {
        int vCount = arr.length;
        int[] tempKey = new int[vCount + 1];
        // 元素索引从1开始
        for (int i = 0; i < vCount; i++) {
            tempKey[i + 1] = arr[i];
        }
        // 初始数据建堆（从含最后一个结点的子树开始构建，依次向前，形成整个二叉堆）
        for (int i = vCount / 2; i >= 1; i--) {
            Restore(tempKey, i, vCount);
        }
        // 不断输出堆顶元素、重构堆，进行排序
        for (int i = vCount; i > 1; i--) {
            int temp = tempKey[i];
            tempKey[i] = tempKey[1];
            tempKey[1] = temp;
            Restore(tempKey, 1, i - 1);
        }
        //排序结果
        for (int i = 0; i < vCount; i++) {
            arr[i] = tempKey[i + 1];
        }
    }
    public static void Restore(int[] arr, int rootNode, int nodeCount) {
        while (rootNode <= nodeCount / 2) // 保证根结点有子树
        {
            //找出左右儿子的最大值
            int m = (2 * rootNode + 1 <= nodeCount && arr[2 * rootNode + 1] > arr[2 * rootNode]) ? 2 * rootNode + 1 : 2 * rootNode;
            if (arr[m] > arr[rootNode]) {
                int temp = arr[m];
                arr[m] = arr[rootNode];
                arr[rootNode] = temp;
                rootNode = m;
            } else {
                break;
            }
        }
    }
}
