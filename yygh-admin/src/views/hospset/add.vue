><template>
    <div class="app-container">
        医院设置添加
        <el-form label-width="120px">
         <el-form-item label="医院名称">
            <el-input v-model="hospitalSet.hosname"/>
         </el-form-item>
         <el-form-item label="医院编号">
            <el-input v-model="hospitalSet.hoscode"/>
         </el-form-item>
         <el-form-item label="api基础路径">
            <el-input v-model="hospitalSet.apiUrl"/>
         </el-form-item>
         <el-form-item label="联系人姓名">
            <el-input v-model="hospitalSet.contactsName"/>
         </el-form-item>
         <el-form-item label="联系人手机">
            <el-input v-model="hospitalSet.contactsPhone"/>
         </el-form-item>
         <el-form-item>
            <el-button type="primary" @click="saveOrUpdate">保存</el-button>
         </el-form-item>
      </el-form>
    </div>
</template>

<script>
//引入接口定义的js文件
import hospset from '@/api/hospset'
export default {
    data(){
        return{
            hospitalSet:{}
        }
   },
   created() {
        //获取路由id值
        //调用接口得到医院设置信息
        if(this.$route.params && this.$route.params.id){    //修改bug,先修改再添加，添加页面存在数据回显
            const id = this.$route.params.id
            this.getHospitalSet(id)
        }else{  //解决办法：置空
            this.hospitalSet = {}
        }
   },
   methods: {
        //根据id查询
        getHospitalSet(id){
            hospset.getHospitalSet(id)
                .then(response => {
                    this.hospitalSet = response.data   
                })
        },
        //添加
        add(){
            hospset.addHospitalSet(this.hospitalSet)
                .then(response => {
                    this.$message({
                        type: 'success',
                        message: '添加成功!'
                    })
                    //跳转列表页面，使用路由跳转方式实现
                    this.$router.push({path:'/hospSet/list'})
                }).catch(() => {

                })
        },
        //修改
        update(){
            hospset.updateHospitalSet(this.hospitalSet)
                .then(response => {
                    this.$message({
                        type: 'success',
                        message: '修改成功!'
                    })
                    //跳转列表页面，使用路由跳转方式实现
                    this.$router.push({path:'/hospSet/list'})
                }).catch(() => {

                })
        },
        //添加or修改
        saveOrUpdate() {
            //判断到底是添加还是修改
            if(!this.hospitalSet.id){   //没有id就是添加
               this.add()
            }else{  //否则就是修改操作
                this.update()
            }
        }
   }

}

</script>