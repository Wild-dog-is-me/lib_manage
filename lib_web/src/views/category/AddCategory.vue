<template>
    <div style="width: 80%">
      <div style="margin-bottom: 30px">新增分类</div>
      <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入分类备注"></el-input>
        </el-form-item>
      </el-form>
  
      <div style="text-align: center; margin-top: 30px">
        <el-button type="primary" @click="save" size="medium">提交</el-button>
      </div>
    </div>
  </template>
  
  <script>
  import request from "@/api/request";
  
  export default {
    name: 'AddUser',
    data() {
      return {
        form: {},
        rules: {
          name: [
            { required: true, message: '请输入分类名称', trigger: 'blur'}
          ],
        }
      }
    },
    methods: {
      save() {
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            request.post('/category/save', this.form).then(res => {
              if (res.code === '200') {
                this.$notify.success('新增成功')
                this.$refs['ruleForm'].resetFields()
                this.$router.push("/categoryList")
              } else {
                this.$notify.error(res.msg)
              }
            })
          }
        })
      },
      resetFields(){
        this.form = {}
      }
    }
  }
</script>