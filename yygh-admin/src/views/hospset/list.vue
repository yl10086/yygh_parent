><template>
    <div class="app-container">
        医院设置列表
        <br>
        <br>
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item>
                <el-input  v-model="searchObj.hosname" placeholder="医院名称"/>
            </el-form-item>
            <el-form-item>
                <el-input v-model="searchObj.hoscode" placeholder="医院编号"/>
            </el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
        </el-form>

        <!-- 工具条 -->
        <div>
            <el-button type="danger" size="mini" @click="removeRows()">批量删除</el-button>
        </div>
        <!-- banner列表 -->
        <el-table
            :data="list" stripe style="width: 100%" @selection-change="handleSelectionChange">
            <!-- 批量删除复选框 -->
            <el-table-column type="selection" width="55"/>
            <!-- 显示表单 -->
            <el-table-column type="index" width="50" label="序号"/>
            <el-table-column prop="hosname" label="医院名称"/>
            <el-table-column prop="hoscode" label="医院编号"/>
            <el-table-column prop="apiUrl" label="api基础路径" width="200"/>
            <el-table-column prop="contactsName" label="联系人姓名"/>
            <el-table-column prop="contactsPhone" label="联系人手机"/>

            <el-table-column label="状态" width="80">
                <template slot-scope="scope">
                        {{ scope.row.status === 1 ? '可用' : '不可用' }}
                </template>
            </el-table-column>
            <!-- 删除按钮 -->
            <el-table-column label="操作" width="280" align="center">
                <template slot-scope="scope">
                    <el-button type="danger" size="mini" 
                        icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
                    <el-button v-if="scope.row.status==1" type="primary" size="mini" 
                        icon="el-icon-delete" @click="lockHospitalSet(scope.row.id,0)">锁定</el-button>
                    <el-button v-if="scope.row.status==0" type="danger" size="mini" 
                        icon="el-icon-delete" @click="lockHospitalSet(scope.row.id,1)">取消锁定</el-button>
                    <router-link :to="'/hospSet/edit/'+scope.row.id">
                        <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
                    </router-link>

                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <el-pagination
        :current-page="page"
        :page-size="limit"
        :total="total"
        style="padding: 30px 0; text-align: center;"
        layout="total, prev, pager, next, jumper"
        @current-change="getList"/>
    </div>
</template>

<script>
//引入接口定义的js文件
import hospset from '@/api/hospset'
export default {
    //定义变量和初始值
    data() {
        return {
            current:1,  //当前页
            limit:3,     //每页显示记录数
            searchObj:{}, //条件封装对象
            list:[], //每页数据集合
            total:0, //总记录条数
            multipleSelection: [] // 批量选择中选择的记录列表
        }
    },
    created() {  //在页面渲染之前执行
        //一般调用methods定义的方法，得到数据
        this.getList()

    },
    methods: {   //在方法调用后执行，定义方法，进行请求接口调用
        //锁定与取消锁定
        lockHospitalSet(id,status) {
            hospset.lockHospitalSet(id,status)
                .then(response => {
                    //刷新页面
                    this.getList(this.current)
                })
        },
        //当表格复选框选项发生变化的时候触发
        handleSelectionChange(selection) {
            this.multipleSelection = selection
        },
        //批量删除接口
        removeRows() {
            this.$confirm('此操作将永久删除医院是设置信息?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var idList = []
                //遍历数组得到每个id值，设置到isList里面
                for(var i = 0;i < this.multipleSelection.length;i++) {
                    var obj = this.multipleSelection[i]
                    var id = obj.id
                    idList.push(id)
                }
                //调用接口
                hospset.batchRemoveHospSetId(idList)
                    .then(response => {
                        //删除后提示信息
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        })
                        //刷新页面
                        this.getList(this.current)
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                })         
            });
        },
        //医院设置列表
        getList(page = 1) {
            this.current = page
            hospset.getHospSetList(this.current,this.limit,this.searchObj)
                .then(response => { //请求成功response是接口返回数据
                    //返回集合赋值list
                    this.list = response.data.records
                    //总记录条数
                    this.total = response.data.total
                })
                .catch(error => {
                    console.log(error)
                })
        },
        //删除医院设置的方法
        removeDataById(id) {
            this.$confirm('此操作将永久删除医院是设置信息?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //调用接口
                hospset.deleteHospSetId(id)
                    .then(response => {
                        //删除后提示信息
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        })
                        //刷新页面
                        this.getList(this.current)
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
            });
        }
    }
}

</script>